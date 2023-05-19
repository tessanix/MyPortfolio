package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import kotlinx.browser.window
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.svg.Circle
import org.jetbrains.compose.web.svg.LinearGradient
import org.jetbrains.compose.web.svg.Stop
import org.jetbrains.compose.web.svg.SvgElement


@OptIn(ExperimentalComposeWebSvgApi::class)
@Composable
fun CircularProgressBar(
    modifier: Modifier,
    value: Int = 65,
    textLabel: String? = null,
    width: Int = 160,
    height: Int = 160,
    barThickness: Int = 20
) {
    var dashOffsetValue by remember { mutableStateOf(472.0) }
    var launchCircularAnimation by remember { mutableStateOf(false) }

    /*
        value = 0% -> 472 dashOffset;
        value = 100% -> 0 dashOffset;
        value = x% -> 472 - x * (472/100) dashOffset
     */

    LaunchedEffect(launchCircularAnimation) {
        if (launchCircularAnimation) {
            for (i in 1..value) {
                dashOffsetValue = (472.0 - i * (472.0 / 100))
                delay(35)
            }
        } else {
            dashOffsetValue = 472.0
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
                    .borderRadius(50.percent)
                    .display(DisplayStyle.Flex)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .styleModifier {
                        property(
                            "box-shadow",
                            "6px 6px 10px -1px rgba(0, 0, 0, 0.15), " +
                                    "-6px -6px 10px -1px rgba(255, 255, 255, 0.7)"
                        )
                    }
                    .toAttrs()
            ) {
                Div(
                    Modifier
                        .width((width - 2 * barThickness).px)
                        .height((height - 2 * barThickness).px)
                        .display(DisplayStyle.Flex)
                        .justifyContent(JustifyContent.Center)
                        .alignItems(AlignItems.Center)
                        .borderRadius(50.percent)
                        .color(Color("#00000"))
                        .fontWeight(FontWeight.Bold)
                        .fontFamily("Arial")
                        .styleModifier {
                            property(
                                "box-shadow",
                                "inset 4px 4px 6px -1px rgba(0, 0, 0, 0.15), " +
                                        "inset -4px -4px 6px -1px rgba(255, 255, 255, 0.7), " +
                                        "-0.5px -0.5px 0px rgba(255, 255, 255, 1), " +
                                        "0.5px 0.5px 0px rgba(0, 0, 0, 0.15), " +
                                        "0px 12px 10px -10px rgba(0, 0, 0, 0.05)"
                            )
                        }.toAttrs()
                ) {
                    Text("${value}%")
                }
            }

            SvgElement(
                name = "svg",
                attrs = Modifier
                    .width(width.px)
                    .height(width.px)
                    .position(Position.Absolute)
                    .top(0.px)
                    .left(0.px)
                    .toAttrs()
            ) {
                LinearGradient(id = "GradientColor") {
                    Stop(attrs = Modifier.toAttrs {
                        attr("offset", "0%")
                        attr("stop-color", "#e91e63")
                    })
                    Stop(attrs = Modifier.toAttrs {
                        attr("offset", "100%")
                        attr("stop-color", "#673ab7")
                    })
                }
                Circle(
                    cx = 80,
                    cy = 80,
                    r = 70,
                    attrs = {
                        attr("stroke-linecap", "round") // make ends of the stroke (outline) of the circle round
                        attr("fill", "none") // make circle transparent
                        attr("stroke", "url(#GradientColor)") // color the stroke (outline)
                        attr("stroke-width", "${barThickness}px")
                        attr(
                            "stroke-dasharray",
                            "472"
                        ) // defines the pattern of dashes and gaps used in the stroke of the circle.
                        attr("stroke-dashoffset", "$dashOffsetValue") // the distance by which the stroke is offset
                    }
                )
            }
        }

        if (textLabel != null) {
            Label(
                attrs = Modifier
                    .margin(20.px)
                    .color(Color("#00000"))
                    .fontWeight(FontWeight.Bold)
                    .fontFamily("Arial").toAttrs()
            ) {
                Text(textLabel)
            }
        }
    }
}



