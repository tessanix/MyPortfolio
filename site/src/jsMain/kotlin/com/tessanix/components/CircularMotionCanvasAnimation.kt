package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.silk.components.graphics.Canvas2d
import com.varabyte.kobweb.silk.components.graphics.ONE_FRAME_MS_60_FPS
import com.varabyte.kobweb.silk.components.graphics.RenderScope
import org.jetbrains.compose.web.css.percent
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
        renderScope.ctx.fillText(line, xPos, yPos);
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
    renderScope.ctx.fillText("Discover", x, y+10)

    renderScope.ctx.closePath()
}


@Composable
fun CircularMotionCanvasAnimation(n: Int = 400) {

    var mouseDown = false
    var buttonAlpha = 0.6
    var textAlpha = 0.0
    var particlesAlpha = 1.0
    var radians = 0.0
    val canvasWith = 1500.0
    val canvasHeight = 1000.0
    val particlesTheme = listOf("#2185C5", "#7ECEFD", "#FFF6E5", "#FF7F66")

    val particles = buildList {
        val increasedWith = canvasWith + 300
        val increasedHeight = canvasHeight + 300
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
        width = canvasWith.toInt(),
        height = canvasHeight.toInt(),
        modifier = Modifier
            .onMouseDown {  mouseDown = true }
            .onMouseUp { mouseDown = false }
            .onMouseOver { /*TODO: change cursor to pointer when over the central button */ }
            .height(100.percent)
            .width(100.percent),
        minDeltaMs = ONE_FRAME_MS_60_FPS,
    ){
        ctx.fillStyle = "rgba(10, 10, 10, $particlesAlpha)"
        ctx.fillRect(0.0, 0.0, canvasWith, canvasHeight)

        ctx.save {
            ctx.translate(canvasWith / 2, canvasHeight / 2)
            ctx.rotate(radians)
            particles.forEach { it.update(this) }
        }

        ctx.save {
            drawCentralButton(
                canvasWith / 2,
                canvasHeight / 2,
                100.0,
                "rgba(88, 92, 150, $buttonAlpha)",
                "rgba(200, 200, 200, $buttonAlpha)",
                this
            )
        }

        ctx.save {
            drawText(
                "Welcome\nto my place!",
                48,
                "rgba(255, 255, 255, $textAlpha)",
                canvasWith,
                canvasHeight,
                this
            )
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
