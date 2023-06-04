package com.tessanix.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.asList

@Page
@Composable
fun KaraokeAppProjectPage() {
    val backgroundColor = rgb(19, 34, 80)

    val videos = document.getElementsByTagName("video").asList()
    var isPlaying by remember { mutableStateOf(false) }

    Div(
        Modifier
            .height(100.vh)
            .fillMaxWidth()
            .display(DisplayStyle.Grid)
            .gridTemplateRows("1fr ".repeat(12))
            .gridTemplateColumns("1fr ".repeat(16))
            .backgroundColor(backgroundColor)
            .toAttrs()
    ) {

//        Card(
//            { P{
//                    Text("Côté client:")
//                    Br()
//                    Text("- Application android native faite avec le langage Kotlin et le framework Jetpack Compose")
//                }
//            },
//            Modifier
//                .gridArea("1", "6", "3", "13")
//                .margin(topBottom = 20.px)
//
//        )
        Card(
            {
                Image(
                    src="serverIcon.svg",
                    modifier=Modifier.width(4.em).height(4.em).margin(10.px)
                )
                Image(
                    src="KtorLogoIcon.svg",
                    modifier=Modifier.width(4.em).height(4.em).margin(10.px)
                )
                H3{ Text("Côté serveur:") }
                Ul {
                    Li{
                        SpanText(
                            text = "Le produit: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text( "Serveur dédié à l'administrateur déployé sur le web")
                    }
                    Li{
                        SpanText(
                            text = "Outils utilisés: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text( "Le langage Kotlin et le framework Ktor")
                    }

                    Li{
                        SpanText(
                            text = "Disponibilité: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text("via un url avec des identifiants")
                    }
                    Li{
                        SpanText(
                            text = "Partage du code: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text("Code source disponible sur github")
                    }
                }
            },
                Modifier
                    .gridArea("1", "14", "10", "17")
                    .margin(20.px, 20.px, 0.px, 20.px)
        )
        Card(
            {
                Image(
                    src="smartphoneIcon.svg",
                    modifier=Modifier.width(4.em).height(4.em).margin(10.px)
                )
                Image(
                    src="androidNoCircleIcon.svg",
                    modifier=Modifier.width(4.em).height(4.em).margin(10.px)
                )
                H3{ Text("Côté client:") }
                Ul {
                    Li{
                        SpanText(
                            text = "Le produit: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text( "Application android native")
                    }
                    Li{
                        SpanText(
                            text = "Outils utilisés: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text( "Le langage Kotlin et le framework Jetpack Compose")
                    }

                    Li{
                        SpanText(
                            text = "Disponibilité: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text("Sur le play Store, uniquement dans la zone géographique Guadeloupe")
                    }
                    Li{
                        SpanText(
                            text = "Partage du code: ",
                            modifier = Modifier.fontWeight(FontWeight.Bold)
                        )
                        Text("Code source disponible sur github")
                    }
                }
            },
            Modifier
                .gridArea("3", "1", "13", "4")
                .margin(0.px, 20.px, 20.px, 20.px)

        )

       H1( Modifier
           .gridArea("1", "1", "2", "4")
           .color(Colors.White)
           .margin(20.px)
           .fontFamily("Arial")
           .toAttrs()
       ) { Text("Karaoke App") }


        SmartphoneVideo(
            Modifier
                .gridArea("2", "4", "11", "7")
                .margin(20.px, 10.px, 20.px, 20.px)
        )

        DesktopVideo(
            Modifier
                .gridArea("2", "7", "11", "14")
                .margin(20.px, 20.px, 20.px, 10.px)
        )


        Button(
            Modifier
                .gridArea("11", "6", "12", "8")
                .padding(10.px)
                .borderRadius(35.px)
                .margin(30.px)
                .onClick {
                    isPlaying = !isPlaying
                    if (isPlaying) videos.forEach { video -> (video as HTMLVideoElement).play() }
                    else videos.forEach { video -> (video as HTMLVideoElement).pause() }
                }
                .toAttrs()
        ) { Text(if (isPlaying) "Pause both videos" else "Play both videos") }

    }
}


@Composable
fun Card(
    content: @Composable () -> Unit ,
    modifier: Modifier
){
    Box(
        modifier
            .display(DisplayStyle.Block)
            .fontSize(2.em)
            .padding(1.em)
            .borderRadius(35.px)
            .backgroundColor(rgba(255,255,255,0.2))
            .fontFamily("Arial")
    ){
        content()
    }
}

@Composable
fun SmartphoneVideo(modifier: Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        FaPhone(modifier = Modifier.color(Colors.White).height(80.px).width(80.px).margin(10.px))

        Video(
            Modifier
                .width(100.percent).height(100.percent)
                .objectFit(ObjectFit.Fill)
                .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
                .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
                .toAttrs()
        ) {
            Source(attrs = {
                attr("src", "karaokeappvideos-smartphone2.mp4")
                attr("type", "video/mp4")
            })
        }
    }
}

@Composable
fun DesktopVideo(modifier: Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        FaServer(modifier = Modifier.color(Colors.White).height(80.px).width(80.px).margin(10.px))

        Video(
            Modifier
                .width(100.percent).height(100.percent)
                .objectFit(ObjectFit.Fill)
                .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
                .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
                .toAttrs()
        ) {
            Source(attrs = {
                attr("src", "KaraokeAppVideo_desktop.mp4")
                attr("type", "video/mp4")
            })
        }
    }
}