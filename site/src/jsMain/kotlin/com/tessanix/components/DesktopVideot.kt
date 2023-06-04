package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.ObjectFit
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
fun DesktopVideo(modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().position(Position.Relative).margin(40.px),
        contentAlignment = Alignment.Center
    ) {
        Video(videoDesktopAttrs) {
            Source(attrs = {
                attr("src", "KaraokeAppVideo_desktop.mp4")
                attr("type", "video/mp4")
            })
        }

        Image(
            "laptop_mockup.svg",
            modifier = Modifier.width(100.percent).height(100.percent)//.objectFit(ObjectFit.Contain)
        )
    }
}

val videoDesktopAttrs = Modifier
    .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
    .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
//    .objectFit(ObjectFit.Fill)
//    .position(Position.Absolute)
//    .top(0.percent)
//    .right(0.percent)
//    .translate((-15).percent, 7.percent)
//    .width(77.percent)
//    .height(84.percent)
    .toAttrs<AttrsScope<HTMLVideoElement>>()


//val videoAttrs =  Modifier.toAttrs<AttrsScope<HTMLVideoElement>> {
//    attr("width", "100%")
//    onMouseEnter { (it.target as HTMLVideoElement).setAttribute("controls", "") }
//    onMouseLeave { (it.target as HTMLVideoElement).removeAttribute("controls") }
//}

