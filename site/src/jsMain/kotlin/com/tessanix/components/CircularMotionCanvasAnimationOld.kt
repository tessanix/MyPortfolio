//package com.tessanix.components

import androidx.compose.runtime.Composable
import com.tessanix.lang
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Canvas2d
import com.varabyte.kobweb.silk.components.graphics.ONE_FRAME_MS_60_FPS
import com.varabyte.kobweb.silk.components.graphics.RenderScope
import kotlinx.browser.window
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.vh
import org.w3c.dom.CENTER
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.CanvasState
import org.w3c.dom.CanvasTextAlign
import kotlin.math.PI
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
    private val radius: Double,
    private val color: String
) {
    fun update( renderScope: RenderScope<CanvasRenderingContext2D> ) {
        draw(renderScope)
    }
    private fun draw(renderScope: RenderScope<CanvasRenderingContext2D> ) {
        renderScope.ctx.beginPath()
        renderScope.ctx.arc(x, y, radius, 0.0, PI*2, false )
        renderScope.ctx.shadowColor = color
        renderScope.ctx.shadowBlur = 15.0
        renderScope.ctx.fillStyle = color
        renderScope.ctx.fill()
        renderScope.ctx.closePath()
    }
}

fun drawCentralButton(
    x: Double,
    y: Double,
    radius: Double,
    color : String,
    textColor: String,
    renderScope: RenderScope<CanvasRenderingContext2D>
) {
    renderScope.ctx.beginPath()
    renderScope.ctx.arc(x, y, radius, 0.0, PI*2, false )
    renderScope.ctx.shadowColor = color
    renderScope.ctx.shadowBlur = 10.0
    renderScope.ctx.fillStyle = color
    renderScope.ctx.fill()
    //Text

    renderScope.ctx.fillStyle = textColor
    renderScope.ctx.font = "Bold 40px sans-serif"
    renderScope.ctx.textAlign = CanvasTextAlign.CENTER
    renderScope.ctx.fillText(if(lang =="french") "Découvrir" else "Discover", x, y+10)
    renderScope.ctx.closePath()
}


@Composable
fun CircularMotionCanvasAnimation(
    n: Int = 500,
    vhOffset: Int = 50,
    backgroundColor: String
) {
    val yScaleFactorRect = 3.0
    val xScaleFactorRect = 2.0

    val yScaleFactorArc = 3.0
    val xScaleFactorArc = 2.0

    var mouseDown = false
    var buttonAlpha = 0.6
    var textAlpha = 0.0
    var particlesAlpha = 1.0
    var radians = 0.0

    val particlesTheme = listOf("#2185C5", "#7ECEFD", "#FFF6E5", "#FF7F66")

    val particles = buildList {
        val increasedWith = window.innerWidth + 700
        val increasedHeight = window.innerHeight + 700
        for(i in 0 until n){
             this.add(
                 Particle(
                     x = Random.nextDouble() * increasedWith - increasedWith/2,
                     y = Random.nextDouble() * increasedHeight - increasedHeight/2,
                     radius = Random.nextDouble() * 2,
                     color = particlesTheme.random()
                 )
             )
         }
    }

    Canvas2d(
        // canvas buffer dimensions
        width = 2000,
        height = 1500,
        modifier = Modifier
            .onClick { mouseDown = !mouseDown }
//            .onMouseDown {  mouseDown = true }
//            .onMouseUp { mouseDown = false }
            .width(100.percent)
            .height((100+vhOffset).vh),
        minDeltaMs = ONE_FRAME_MS_60_FPS,
    ){
        val canvasWith = ctx.canvas.width.toDouble() // always == 1800
        val canvasHeight = ctx.canvas.height.toDouble() // always == 960
        val canvasHeightOnViewPort = canvasHeight - (canvasHeight/(100+vhOffset))*vhOffset

        ctx.fillStyle = "rgba(10, 10, 10, $particlesAlpha)"
        ctx.fillRect(0.0, 0.0, canvasWith, canvasHeight)

        ctx.save {
            ctx.translate(canvasWith/2, canvasHeightOnViewPort/2 )
            ctx.rotate(radians)
            particles.forEach { it.update(this) }
        }

        ctx.save {
            drawCentralButton(
                canvasWith/2,
                canvasHeightOnViewPort/2,
                100.0,
                "rgba(88, 92, 150, $buttonAlpha)",
                "rgba(200, 200, 200, $buttonAlpha)",
                this
            )

            drawText(
                if(lang=="french") "Bienvenue!" else "Welcome\nto my place!",
                48,
                "rgba(255, 255, 255, $textAlpha)",
                canvasWith,
                canvasHeight,
                this
            )
        }

        ctx.save {
            ctx.beginPath()
            val grad = ctx.createRadialGradient(
                (canvasWith/2)/xScaleFactorRect, yScaleFactorRect*canvasHeight, 0.0,
                (canvasWith/2)/xScaleFactorRect, yScaleFactorRect*canvasHeight, canvasWith/2
            )

            grad.addColorStop(1.0, "rgba(12,12,12, 0.2)")

//            grad.addColorStop(0.90, "rgba(70, 51, 83, 0.5)")
//            grad.addColorStop(0.6, "rgba(134, 109, 152, 0.6)")
//            grad.addColorStop(0.5, "rgba(250,250,250, 0.7)")
            grad.addColorStop(0.0, backgroundColor) //19 34 80
            ctx.fillStyle = grad
            ctx.setTransform(xScaleFactorRect, 0.0,0.0,1/yScaleFactorRect, 0.0, 0.0)
            ctx.fillRect(0.0, canvasHeightOnViewPort*yScaleFactorRect, canvasWith, yScaleFactorRect*(canvasHeight-canvasHeightOnViewPort))
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
            ctx.arc(canvasWith/2/xScaleFactorArc, canvasHeight*yScaleFactorArc, canvasWith/yScaleFactorArc, 0.0,2*PI)
            ctx.fill()
            ctx.closePath()
        }

        radians += 0.005

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
