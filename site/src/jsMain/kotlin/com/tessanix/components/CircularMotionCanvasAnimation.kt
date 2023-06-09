package com.tessanix.components

import androidx.compose.runtime.*
import com.tessanix.lang
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Canvas2d
import com.varabyte.kobweb.silk.components.graphics.ONE_FRAME_MS_60_FPS
import com.varabyte.kobweb.silk.components.graphics.RenderScope
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import kotlinx.browser.window
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.random.Random

fun CanvasState.save(block: () -> Unit) {
    save()
    block()
    restore()
}

fun drawText(
    text: String,
    fontSize: Int,
    color: String,
    canvasWidth: Double,
    canvasHeight: Double,
    renderScope: RenderScope<CanvasRenderingContext2D>
){
    renderScope.ctx.fillStyle = color
    renderScope.ctx.font = "Bold ${fontSize}px sans-serif"
    renderScope.ctx.textAlign = CanvasTextAlign.CENTER

    val lines = text.split("\n")

    val xPos = canvasWidth/2
    val yStartingPos = canvasHeight/2 - (lines.size - 1) * fontSize/2

    lines.forEachIndexed { index, line ->
        val yPos = yStartingPos + index * fontSize
        renderScope.ctx.fillText(line, xPos, yPos)
    }
}
class Particle(
    private var x: Double,
    private var y: Double,
    private var initialX: Double = x,
    private var initialY: Double = y,
    private val radius: Double,
    private var radians: Double = Random.nextDouble()*PI*2,
    private val velocity: Double = 0.004,
    private val distanceFromCenter: Double,
    private val color: String
) {
    fun update(
        canvasWidth: Double,
        canvasHeightOnViewPort: Double,
        renderScope: RenderScope<CanvasRenderingContext2D>
    ) {
        if(initialX != canvasWidth/2.0) initialX = canvasWidth/2.0
        if(initialY != canvasHeightOnViewPort/2.0) initialY = canvasHeightOnViewPort/2.0

        val lastX = x; val lastY = y
        radians += velocity
        x = initialX + cos(radians)*distanceFromCenter
        y = initialY + sin(radians)*distanceFromCenter
        draw(lastX, lastY, renderScope)
    }
    private fun draw(
        lastX: Double, lastY: Double,
        renderScope: RenderScope<CanvasRenderingContext2D>
    ) {
        renderScope.ctx.beginPath()
        renderScope.ctx.strokeStyle = color
        renderScope.ctx.lineWidth = radius
        renderScope.ctx.moveTo(lastX, lastY)
        renderScope.ctx.lineTo(x,y)
//        renderScope.ctx.shadowColor = color
//        renderScope.ctx.shadowBlur = 15.0
        renderScope.ctx.stroke()
        renderScope.ctx.closePath()
    }
}
class CentralButton(
    private var x: Double,
    private var y: Double,
    private var radius: Double,
) {
    fun update(
        newX: Double,
        newY: Double,
        newRadius: Double,
        alpha: Double,
        renderScope: RenderScope<CanvasRenderingContext2D>
    ){
        x = newX
        y = newY
        radius = newRadius
        draw(alpha, renderScope)
    }
    private fun draw(
        alpha: Double,
        renderScope: RenderScope<CanvasRenderingContext2D>
    ){
        val grad = renderScope.ctx.createRadialGradient(
            x, y, 0.0,
            x, y, radius
        )
        grad.addColorStop(0.0, "rgba(88, 92, 150, $alpha)")
        grad.addColorStop(1.0,"rgba(10, 10, 10, ${alpha-0.5})")
        renderScope.ctx.fillStyle = grad

        renderScope.ctx.beginPath()
        renderScope.ctx.arc(x, y, radius, 0.0, PI*2, false )
        renderScope.ctx.fillStyle = grad
        renderScope.ctx.fill()
        //Text
        renderScope.ctx.fillStyle = "rgba(88, 92, 150, $alpha)"

        renderScope.ctx.font = "Bold 40px sans-serif"
        renderScope.ctx.textAlign = CanvasTextAlign.CENTER
        renderScope.ctx.fillText(if(lang =="french") "Découvrir" else "Discover", x, y+10)
        renderScope.ctx.closePath()
    }

    fun isMouseOverEllipse(
        mouseX: Double,
        mouseY: Double,
        canvasWidth: Double,
        canvasHeightOnViewPort: Double
    ): Boolean{
        val rescaleFactorX = window.innerWidth/canvasWidth
        val rescaleFactorY = window.innerHeight/canvasHeightOnViewPort

        val a = radius*rescaleFactorX
        val b = radius*rescaleFactorY
        val centerX = x*rescaleFactorX
        val centerY = y*rescaleFactorY
        return (
            ((mouseX-centerX)/a).pow(2) + ((mouseY-centerY)/b).pow(2) < 1.0
        )
    }
}


@Composable
fun CircularMotionCanvasAnimation(
    bp: Breakpoint,
    nParticles: Int = 1000,
    vhOffset: Int = 50,
) {

    var canvasWidth by remember{ mutableStateOf(window.innerWidth.toDouble()) }
    var canvasHeight by remember{ mutableStateOf(window.innerHeight*1.5) }
    var mouseDown by remember{ mutableStateOf(false) }

    if(bp < Breakpoint.SM || bp < Breakpoint.MD || bp < Breakpoint.LG || bp < Breakpoint.XL || Breakpoint.XL <= bp){
        canvasWidth = window.innerWidth.toDouble()
        canvasHeight = window.innerHeight*1.5
    }

//    if(canvasWidth !in window.innerWidth*0.95..window.innerWidth*1.05)
//        canvasWidth = window.innerWidth.toDouble()
//
//    if(canvasHeight !in window.innerHeight*0.95..window.innerHeight*1.05)
//        canvasHeight = window.innerHeight*1.5

    val canvasHeightOnViewPort = canvasHeight - (canvasHeight/(100+vhOffset))*vhOffset

    var buttonAlpha = 0.6
    var textAlpha = 0.0
    var particlesAlpha = 1.0

    val particlesTheme = listOf("#2185C5", "#7ECEFD", "#FFF6E5", "#FF7F66")

    val particles = buildList {
        for(i in 0 until nParticles){
             this.add(
                 Particle(
                     x = canvasWidth/2.0,
                     y =  canvasHeightOnViewPort/2.0,
                     radius = Random.nextDouble() * 2,
                     distanceFromCenter = Random.nextDouble(30.0,canvasWidth*1.2),
                     color = particlesTheme.random()
                 )
             )
         }
    }

    val centralButton = CentralButton(
        canvasWidth/2,
        canvasHeightOnViewPort/2,
        canvasWidth*0.075,
    )

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.position(Position.Relative)
    ) {

        Canvas2d(
            // canvas buffer dimensions
            width = canvasWidth.toInt(),
            height = canvasHeight.toInt(),
            modifier = Modifier
                .onClick { mouseDown = !mouseDown }
                .onMouseMove {
                    if (centralButton.isMouseOverEllipse(it.offsetX, it.offsetY, canvasWidth, canvasHeightOnViewPort))
                        (it.target as HTMLCanvasElement).style.cursor = "pointer"
                    else
                        (it.target as HTMLCanvasElement).style.cursor = "default"
                }
                .width(100.percent)
                .height((100 + vhOffset).vh),
            minDeltaMs = ONE_FRAME_MS_60_FPS,
        ) {

            ctx.fillStyle = "rgba(10, 10, 10, $particlesAlpha)"
            ctx.fillRect(0.0, 0.0, canvasWidth, canvasHeight)

            ctx.save { particles.forEach { it.update(canvasWidth, canvasHeightOnViewPort, this) } }

            ctx.save {
                centralButton.update(
                    canvasWidth / 2,
                    canvasHeightOnViewPort / 2,
                    canvasWidth * 0.075,
                    alpha = buttonAlpha,
                    this
                )

                drawText(
                    if (lang == "french") "Bienvenue!" else "Welcome\nto my place!",
                    48,
                    "rgba(255, 255, 255, $textAlpha)",
                    canvasWidth,
                    canvasHeight,
                    this
                )
            }

            if (mouseDown) {
                if (0.1 <= particlesAlpha) particlesAlpha -= 0.01
                if (0 < buttonAlpha) buttonAlpha -= 0.01
                if (textAlpha < 1) textAlpha += 0.005
            } else {
                if (particlesAlpha < 1) particlesAlpha += 0.01
                if (buttonAlpha < 0.6) buttonAlpha += 0.01
                if (0 < textAlpha) textAlpha -= 0.01
            }
        }

        ScrollDownAnimation(Modifier.translateY(50.px), mouseDown)
    }
}
