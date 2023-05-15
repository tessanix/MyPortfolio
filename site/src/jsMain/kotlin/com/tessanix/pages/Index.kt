package com.tessanix.pages

import androidx.compose.runtime.*
import com.tessanix.components.CircularMotionCanvasAnimation
import com.tessanix.components.DesktopVideo
import com.tessanix.components.NavBar
import com.tessanix.components.SmartphoneVideo
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Canvas2d
import com.varabyte.kobweb.silk.components.graphics.ONE_FRAME_MS_60_FPS
import com.varabyte.kobweb.silk.components.graphics.RenderScope
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.addVariant
import com.varabyte.kobweb.silk.components.style.toAttrs
import com.varabyte.kobweb.silk.theme.shapes.Circle
import com.varabyte.kobweb.silk.theme.shapes.Rect
import com.varabyte.kobweb.silk.theme.shapes.RectF
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.*


@Page
@Composable
fun HomePage() {
    val videos = document.getElementsByTagName("video").asList()
    var isPlaying by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Section (
            Modifier
            .width(100.percent)
            .height(100.vh)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .justifyContent(JustifyContent.Center)
            .toAttrs()
        ) {

            CircularMotionCanvasAnimation()
        }

        Section(Modifier
            .fillMaxWidth()
            .padding(10.px)
            .textAlign(TextAlign.Center)
            .backgroundImage(
                linearGradient(dir = LinearGradient.Direction.ToBottom){
                    add(Color("#0a0a0a"))
                    add(Color("#8156d7"))
                    add(Color("#ffffff"))

                }
            )
            .toAttrs()
        ) {
            NavBar()

            H1(
                Modifier.fontFamily("Arial Black").margin(topBottom = 50.px).toAttrs()
            ) {
                Text("Salut! Moi, Tessan, je suis la solution qu'il te faut!"); Br()
                Text("En quelques mots:"); Br()
                Text("- Passioné"); Br()
                Text("- Curieux"); Br()
                Text("- Optimiste"); Br()
            }
        }

        Section(Modifier
            .fillMaxWidth()
            .padding(10.px)
            .toAttrs()
        ) {
            H3(
                Modifier.fontFamily("Arial").toAttrs()
            ) {
                Text("Mes compétences en programmation:"); Br()
                Text("- Développement mobile Android natif fullstack en Kotlin)"); Br()
                Text("- Développement Backend pour le web"); Br()
                Text("- Développement logiciel bas niveau en C++"); Br()
                Text("- Scripts Python"); Br()
            }
        }

        Section(Modifier
            .fillMaxWidth()
            .padding(10.px)
            .toAttrs()
        ) {
            Text("Projects")

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


            Div( ProjectDivStyle.toAttrs(Width35ProjectDivStyleVariant) ) {
                Div( Modifier.fillMaxSize().backgroundColor(Color.bisque).toAttrs() ) {
                    Text("Project 2 : Kobweb website")
                }
            }
        }
//            Row( Modifier.fillMaxWidth() ){
//                Div( ProjectDivStyle.toAttrs(Width60ProjectDivStyleVariant) ) {
//                    Text("Project 3 : Hack Wifi App")
//                }
//                Div( ProjectDivStyle.toAttrs(Width40ProjectDivStyleVariant) ) {
//                    Text("Project 4 : Firewall Python")
//                }
//            }
//        }

        Footer(Modifier
            .fillMaxWidth()
            .backgroundColor(color = rgb(100,21,31))
            .toAttrs()
        ) { Text("Thank you!") }
    }
}



val ProjectDivStyle by ComponentStyle {
    base { Modifier.margin(5.px).backgroundColor(color = rgb(200,45,250)) }
}

val Width40ProjectDivStyleVariant by ProjectDivStyle.addVariant {
    base { Modifier.fillMaxWidth(40.percent) }
}

val Width60ProjectDivStyleVariant by ProjectDivStyle.addVariant {
    base { Modifier.fillMaxWidth(60.percent) }
}

val Width35ProjectDivStyleVariant by ProjectDivStyle.addVariant {
    base { Modifier.fillMaxWidth(35.percent) }
}

val Width65ProjectDivStyleVariant by ProjectDivStyle.addVariant {
    base { Modifier.fillMaxWidth(65.percent) }
}


