package com.tessanix.components

import androidx.compose.runtime.Composable
import com.tessanix.lang
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.onClick
import com.varabyte.kobweb.compose.ui.modifiers.onMouseMove
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.silk.components.graphics.Canvas2d
import com.varabyte.kobweb.silk.components.graphics.ONE_FRAME_MS_60_FPS
import com.varabyte.kobweb.silk.components.graphics.RenderScope
import kotlinx.browser.window
import org.jetbrains.compose.web.css.percent
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
    private val initialX: Double = x,
    private val initialY: Double = y,
    private val radius: Double,
    private var radians: Double = Random.nextDouble()*PI*2,
    private val velocity: Double = 0.006,
    private val distanceFromCenter: Double,
    private val color: String
) {
    fun update( renderScope: RenderScope<CanvasRenderingContext2D> ) {
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
    private val x: Double,
    private val y: Double,
    private val radius: Double,
) {
    fun draw(
        gradColorStart : String,
        gradColorEnd : String,
        textColor: String,
        renderScope: RenderScope<CanvasRenderingContext2D>
    ){
        val grad = renderScope.ctx.createRadialGradient(
            x, y, 0.0,
            x, y, radius
        )
        grad.addColorStop(0.0, gradColorStart)
        grad.addColorStop(1.0, gradColorEnd)
        renderScope.ctx.fillStyle = grad

        renderScope.ctx.beginPath()
        renderScope.ctx.arc(x, y, radius, 0.0, PI*2, false )
        renderScope.ctx.fillStyle = grad
        renderScope.ctx.fill()

        //Text
        renderScope.ctx.fillStyle = textColor
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

//        console.log("mouseX: ", mouseX,  "mouseY: ", mouseY)
//        console.log("h: ", centerX , " k: ", centerY)
//        console.log("ellipsis equ:", ((mouseX-centerX)/a).pow(2) + ((mouseY-centerY)/b).pow(2))
        return (
            ((mouseX-centerX)/a).pow(2) + ((mouseY-centerY)/b).pow(2) < 1.0
        )
    }
}


@Composable
fun CircularMotionCanvasAnimation(
    n: Int = 1000,
    vhOffset: Int = 50,
    backgroundColor: String
) {
    val canvasWidth = 2000.0
    val canvasHeight = 1500.0
    val canvasHeightOnViewPort = canvasHeight - (canvasHeight/(100+vhOffset))*vhOffset

    val yScaleFactorRect = 3.5
    val xScaleFactorRect = 2.0

    val yScaleFactorArc = 3.5
    val xScaleFactorArc = 2.0

    var mouseDown = false
    var buttonAlpha = 0.6
    var textAlpha = 0.0
    var particlesAlpha = 1.0

    val particlesTheme = listOf("#2185C5", "#7ECEFD", "#FFF6E5", "#FF7F66")

    val particles = buildList {
        for(i in 0 until n){
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
        150.0,
    )

    Canvas2d(
        // canvas buffer dimensions
        width = canvasWidth.toInt(),
        height = canvasHeight.toInt(),
        modifier = Modifier
            .onClick { mouseDown = !mouseDown }
            .onMouseMove {
                if(centralButton.isMouseOverEllipse(it.offsetX, it.offsetY, canvasWidth, canvasHeightOnViewPort))
                    (it.target as HTMLCanvasElement).style.cursor = "pointer"
                else
                    (it.target as HTMLCanvasElement).style.cursor = "default"
            }
            .width(100.percent)
            .height((100+vhOffset).vh),
        minDeltaMs = ONE_FRAME_MS_60_FPS,
    ){

        ctx.fillStyle = "rgba(10, 10, 10, $particlesAlpha)"
        ctx.fillRect(0.0, 0.0, canvasWidth, canvasHeight)

        ctx.save { particles.forEach { it.update(this) } }

        ctx.save {
            centralButton.draw(
            "rgba(88, 92, 150, $buttonAlpha)",
            "rgba(10, 10, 10, ${buttonAlpha-0.5})",
            "rgba(88, 92, 150, $buttonAlpha)",
            this
            )

            drawText(
                if(lang=="french") "Bienvenue!" else "Welcome\nto my place!",
                48,
                "rgba(255, 255, 255, $textAlpha)",
                canvasWidth,
                canvasHeight,
                this
            )
        }

        ctx.save {
            ctx.beginPath()
            val grad = ctx.createRadialGradient(
                (canvasWidth/2)/xScaleFactorRect, yScaleFactorRect*canvasHeight, 0.0,
                (canvasWidth/2)/xScaleFactorRect, yScaleFactorRect*canvasHeight, canvasWidth/2
            )
            grad.addColorStop(1.0, "rgba(12,12,12, 0.2)")
            grad.addColorStop(0.0, backgroundColor)
            ctx.fillStyle = grad
            ctx.setTransform(xScaleFactorRect, 0.0,0.0,1/yScaleFactorRect, 0.0, 0.0)
            ctx.fillRect(0.0, canvasHeightOnViewPort*yScaleFactorRect, canvasWidth, yScaleFactorRect*(canvasHeight-canvasHeightOnViewPort))
            ctx.closePath()
        }

        ctx.save {
            ctx.beginPath()
            val grad = ctx.createLinearGradient(
                0.0, canvasHeight*0.9*yScaleFactorArc,
                0.0, canvasHeight*yScaleFactorArc
            )
            grad.addColorStop(0.0, "rgba(12,20,30, 0.2)")
            grad.addColorStop(1.0, backgroundColor)
            ctx.fillStyle = grad
            ctx.setTransform(xScaleFactorArc, 0.0,0.0,1/yScaleFactorArc, 0.0, 0.0)
            ctx.arc(canvasWidth/2/xScaleFactorArc, canvasHeight*yScaleFactorArc, canvasWidth*1.2/yScaleFactorArc, 0.0,2*PI)
            ctx.fill()
            ctx.closePath()
        }

        if (mouseDown) {
            if (0.1 <= particlesAlpha ) particlesAlpha -= 0.01
            if (0 < buttonAlpha) buttonAlpha -= 0.01
            if (textAlpha < 1) textAlpha += 0.005
        } else {
            if (particlesAlpha < 1) particlesAlpha += 0.01
            if (buttonAlpha < 0.6) buttonAlpha += 0.01
            if (0 < textAlpha) textAlpha -= 0.01
        }
    }
}
