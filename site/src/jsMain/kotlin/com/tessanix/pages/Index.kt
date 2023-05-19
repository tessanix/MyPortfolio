package com.tessanix.pages

import androidx.compose.runtime.*
import com.tessanix.components.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.addVariant
import com.varabyte.kobweb.silk.components.style.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.asList
import org.jetbrains.compose.web.css.AlignItems


@Page
@Composable
fun HomePage() {
    val videos = document.getElementsByTagName("video").asList()
    var isPlaying by remember { mutableStateOf(false) }

    val vhOffset = 50

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Section (
            Modifier
            .width(100.percent)
            .height((100+vhOffset).vh)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .justifyContent(JustifyContent.Center)
            .toAttrs()
        ) {
            CircularMotionCanvasAnimation(vhOffset = vhOffset)
        }

        Section(Modifier
            .fillMaxWidth()
            .height(50.vh)
            .padding(10.px)
            .margin(top = (-vhOffset).vh)
            .textAlign(TextAlign.Center)//.filter(blur(10.px))
            .toAttrs()
        ) {
            NavBar()

            H1(
                Modifier
                    .fontFamily("Arial Black")
                    .styleModifier { color("rgb(113 187 215)") }
                    .margin(topBottom = 150.px).toAttrs()
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
            //.height(50.vh)
            .display(DisplayStyle.Grid)
            .gridTemplateRows("1fr 1fr 1fr")
            .gridTemplateColumns("1fr 1fr 1fr 1fr")
            //.gridAutoRows()
            .padding(10.px)
            .toAttrs()
        ) {
            H3( Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .fontFamily("Arial")
                .gridArea("1", "1", "2", "2")
                .toAttrs()
            ){ Text("Langages: ") }

            CircularProgressBar(Modifier.gridArea("1", "2", "2", "3"), value=85, textLabel="Python")
            CircularProgressBar(Modifier.gridArea("1", "3", "2", "4"), value=65, textLabel="C++")
            CircularProgressBar(Modifier.gridArea("1", "4", "2", "5"), value=70, textLabel="Kotlin")

            H3( Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .fontFamily("Arial")
                .gridArea("2", "1", "3", "2")
                .toAttrs()
            ){ Text("Positions: ") }

            CircularProgressBar(Modifier.gridArea("2", "2", "3", "3"), value = 55, textLabel = "Mobile/Web FrontEnd")
            CircularProgressBar(Modifier.gridArea("2", "3", "3", "4"), value = 65, textLabel = "BackEnd")

            H3(Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .fontFamily("Arial")
                .gridArea("3", "1", "4", "2")
                .toAttrs()
            ){ Text("Autres Technologies: ") }
            CircularProgressBar(Modifier.gridArea("3", "2", "4", "3"), value = 75, textLabel = "Git")
            CircularProgressBar(Modifier.gridArea("3", "3", "4", "4"), value = 70, textLabel = "Linux")
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


