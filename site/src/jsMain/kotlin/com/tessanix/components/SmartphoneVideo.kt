package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.HTMLElement


@Composable
fun SmartphoneVideo(modifier: Modifier) {

    val width = 67
    val height = 82

    val diffW = -0.1166 // %
    val diffH = 0.03529 // %

    Box(
        modifier = modifier.fillMaxWidth().position(Position.Relative),
        contentAlignment = Alignment.Center
    ) {
        Video( Modifier
                .width(width.percent)
                .height(height.percent)
                .borderRadius(17.percent)
                .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
                .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
                .toAttrs()
        ) {
            Source(attrs = {
                attr("src", "karaokeappvideos-smartphone2.mp4")
                attr("type", "video/mp4")
            })
        }
        Image(
            "smartphone mockup_2 transparent screen.svg",
            modifier = Modifier
                .position(Position.Absolute)
                .width((width+width*diffW).percent)
                .height((height+height*diffH).percent)
                .pointerEvents(PointerEvents.None) // prevent image to block mouse event
        )


//        Svg(viewBox = "0 0 98.341644 179.35681",
//            attrs={
//                attr("preserveAspectRatio","none")
//                attr("width","98.341644mm")
//                        attr("height","179.35681mm")
//                        attr("viewBox","0 0 98.341644 179.35681")
//                        attr("version","1.1")
//            }) {
//            Use("smartphone mockup_2 transparent screen.svg#defs2")
//            Use("smartphone mockup_2 transparent screen.svg#layer1")
//        }


//        Video( Modifier
//                .width(100.percent)
//                .height(100.percent)
//                //.borderRadius(15.percent)
//                .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
//                .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
//                .toAttrs()
//        ) {
//            Source(attrs = {
//                attr("src", "karaokeappvideos-smartphone2.mp4")
//                attr("type", "video/mp4")
//            })
//        }

//        Object( attrs = {
//            ref { objectElement ->
//                objectElement.addEventListener("load", {
//                    console.log(
//                    objectElement.getSVGDocument()?.documentElement?.getElementsByTagName("rect")?.get(0)
//                    ) // target mask
//                })
//                onDispose {  }
//            }
//            attr("width", "100%")
//            attr("height", "100%")
//            attr("data", "smartphone mockup_2 transparent screen.svg")
//            attr("type","image/svg+xml")
//        })

    }
}
