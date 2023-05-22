package com.tessanix.pages

import androidx.compose.runtime.*
import com.tessanix.components.DesktopVideo
import com.tessanix.components.SmartphoneVideo
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import kotlinx.browser.document
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.asList

@Page
@Composable
fun KaraokeAppProject() {
    val videos = document.getElementsByTagName("video").asList()
    var isPlaying by remember { mutableStateOf(false) }

    Row(
        Modifier.fillMaxWidth()
            .padding(20.px)
            .borderRadius(20.px)
            .backgroundColor(Color.gray)
            .position(Position.Relative),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        H1(Modifier.position(Position.Absolute).top(10.px).left(30.px).toAttrs()){
            Text("Karaoke App")
        }

        SmartphoneVideo()

        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button( Modifier.padding(10.px).margin(20.px).toAttrs {
                onClick {
                    isPlaying = !isPlaying
                    if (isPlaying) videos.forEach { video -> (video as HTMLVideoElement).play() }
                    else videos.forEach { video -> (video as HTMLVideoElement).pause() }
                }
            }) {
                Text(if (isPlaying) "Pause" else "Play")
            }
            DesktopVideo()
        }
    }
}