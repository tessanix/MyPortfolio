package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.svg.Circle
import org.jetbrains.compose.web.svg.SvgElement


@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun CircularProgressBar(
    modifier: Modifier,
//    gradientColorStart: String,
//    gradientColorEnd: String,
    progressBarColor: String,
    value: Int = 65,
    textLabel: String,
    width: Int = 160,
    height: Int = 160,
    barThickness: Int = 20
) {
    var dashOffsetValue by remember { mutableStateOf(100) }
    var launchCircularAnimation by remember { mutableStateOf(false) }


    LaunchedEffect(launchCircularAnimation) {
        if (launchCircularAnimation) {
            for (i in 1..value) {
                dashOffsetValue = 100-i
                delay(20)
            }
        } else {
            dashOffsetValue = 100
        }
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Div(attrs = Modifier
            .position(Position.Relative)
            .width(width.px)
            .height(height.px)
            .display(DisplayStyle.Flex)
            .justifyContent(JustifyContent.Center)
            .alignItems(AlignItems.Center)
            .toAttrs {
                ref { element ->
                    window.addEventListener("scroll", {
                        val top = element.getBoundingClientRect().top
                        val bottom = element.getBoundingClientRect().bottom
                        val viewportHeight = window.innerHeight
                        val elementTopVisible = top >= 0 && top < viewportHeight
                        val elementBottomVisible = bottom > 0 && bottom <= viewportHeight
                        launchCircularAnimation = elementTopVisible || elementBottomVisible
                    })
                    onDispose { }
                }
            }
        ) {
            Div(
                Modifier
                    .width(width.px)
                    .height(height.px)
                    .display(DisplayStyle.Flex)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .borderRadius(50.percent)
                    .border(barThickness.px, LineStyle.Solid, rgb(68,68,68))
                    .fontWeight(FontWeight.Bold)
                    .fontFamily("Arial")
                    .toAttrs()
            ) { Text("${value}%") }

            SvgElement(
                name = "svg",
                attrs = Modifier
                    .width(width.px)
                    .height(height.px)
                    .position(Position.Absolute)
                    .top(0.px)
                    .left(0.px)
                    .toAttrs()
            ) {
//                val idText = if(textLabel!="Mobile/Web FrontEnd") textLabel else "MobileWebFrontEnd"

//                LinearGradient(id = "GradientColor$idText") {
//                    Stop(attrs = Modifier.toAttrs {
//                        attr("offset", "0%")
//                        attr("stop-color", gradientColorStart)
//                    })
//                    Stop(attrs = Modifier.toAttrs {
//                        attr("offset", "50%")
//                        attr("stop-color", gradientColorStart)
//                    })
//                    Stop(attrs = Modifier.toAttrs {
//                        attr("offset", "100%")
//                        attr("stop-color", gradientColorEnd)
//                    })
//                }
                Circle(
                    cx = width/2,
                    cy = height/2,
                    r = (width-barThickness)/2,
                    attrs = {
                        attr("stroke-linecap", "round") // make ends of the stroke (outline) of the circle round
                        attr("fill", "none") // make circle transparent
                        attr("stroke", progressBarColor) //"url(#GradientColor$idText)") // color the stroke (outline)
                        attr("stroke-width", "${barThickness}px")
                        attr("pathLength", "100")
                        attr("stroke-dasharray", "100") // defines the pattern of dashes and gaps used in the stroke of the circle.
                        attr("stroke-dashoffset", "$dashOffsetValue") // the distance by which the stroke is offset
                    }
                )
            }
        }

        Label(
            attrs = Modifier
                .margin(20.px)
                .fontWeight(FontWeight.Bold)
                .fontFamily("Arial")
                .toAttrs()
        ) {
            Text(textLabel)
        }
    }
}



