package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.PointerEvents
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.attributes.AttrsScope
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Source
import org.jetbrains.compose.web.dom.Video
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLVideoElement

@Composable
fun SmartphoneVideo() {
    Box(
        Modifier.fillMaxWidth().position(Position.Relative),
        contentAlignment = Alignment.Center
    ) {
        Video(videoSmartphoneAttrs) {
            Source(attrs = {
                attr("src", "karaokeappvideos-smartphone2.mp4")
                attr("type", "video/mp4")
            })
        }
        Image(
            "smartphone_mockup1.svg",
            modifier = Modifier
                .position(Position.Absolute)
                .top(0.percent)
                .right(0.percent)
                .translate((-41).percent, 6.percent)
                .width(55.percent)
                .height(88.percent)
                .pointerEvents(PointerEvents.None)
        )
    }
}

val videoSmartphoneAttrs = Modifier
    .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
    .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
    .width(55.percent)
    .height(86.percent)
    .margin(20.px)
    .borderRadius(13.percent)
    .toAttrs<AttrsScope<HTMLVideoElement>>()
