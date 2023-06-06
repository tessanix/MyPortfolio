package com.tessanix.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowLeft
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.document
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLVideoElement
import org.w3c.dom.asList

@Page
@Composable
fun KaraokeAppProjectPage() {
    val bp = rememberBreakpoint()
    val ctx = rememberPageContext()
    val backgroundColor = rgb(19, 34, 80)

    val videos = document.getElementsByTagName("video").asList()
    var isPlaying by remember { mutableStateOf(false) }

    val titleList = listOf("Le produit: ", "Outils utilisés: ", "Disponibilité: ", "Partage du code: ")

    val descListServer = listOf(
        "Serveur dédié à l'administrateur déployé sur le web",
        "Le langage Kotlin et le framework Ktor",
        "via un url avec des identifiants",
        "Code source disponible sur github"
    )

    val descListClient = listOf(
        "Application android native",
        "Le langage Kotlin et le framework Jetpack Compose",
        "Sur le play Store, uniquement dans la zone géographique Guadeloupe",
        "Code source disponible sur github"
    )

    when{
        bp < Breakpoint.XL -> {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
               modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .backgroundColor(backgroundColor)
            ) {
                Row(
                    Modifier
                    .fillMaxWidth()
                    .position(Position.Relative),
                    horizontalArrangement = Arrangement.Center
                ) {
                    FaArrowLeft(
                        Modifier
                            .position(Position.Absolute)
                            .top(0.px)
                            .left(0.px)
                            .cursor(Cursor.Pointer)
                            .display(DisplayStyle.Flex)
                            .justifyContent(JustifyContent.Center)
                            .alignItems(AlignItems.Center)
                            .fontSize(2.em)
                            .width(80.px)
                            .height(80.px)
                            .color(Colors.White)
                            .onClick { ctx.router.navigateTo("/") },
                        size = IconSize.LG
                    )
                    H1(
                        Modifier
                            .textAlign(TextAlign.Center)
                            .color(Colors.White)
                            .margin(20.px)
                            .fontFamily("Arial")
                            .toAttrs()
                    ) { Text("Karaoke App") }
                }

                    if(bp < Breakpoint.MD){
                        KaraokeAppCard(
                            modifier = Modifier
                                .gridArea("3", "1", "13", "4")
                                .margin(20.px),
                            srcImage1 = "smartphoneIcon.svg",
                            srcImage2 =  "androidNoCircleIcon.svg",
                            cardTitle = "Côté client:",
                            titleList = titleList,
                            descList = descListClient
                        )
                        KaraokeAppCard(
                            modifier = Modifier
                                .gridArea("1", "14", "10", "17")
                                .margin(20.px),
                            srcImage1 = "serverIcon.svg",
                            srcImage2 =  "KtorLogoIcon.svg",
                            cardTitle = "Côté serveur:",
                            titleList = titleList,
                            descList = descListServer)
                    }else {
                        Row(horizontalArrangement = Arrangement.SpaceAround){
                            KaraokeAppCard(
                                modifier = Modifier
                                    .gridArea("3", "1", "13", "4")
                                    .margin(leftRight =  20.px).fillMaxHeight().width(50.percent),
                                srcImage1 = "smartphoneIcon.svg",
                                srcImage2 =  "androidNoCircleIcon.svg",
                                cardTitle = "Côté client:",
                                titleList = titleList,
                                descList = descListClient
                            )
                            KaraokeAppCard(
                                modifier = Modifier
                                    .gridArea("1", "14", "10", "17")
                                    .margin(leftRight = 20.px).fillMaxHeight().width(50.percent),
                                srcImage1 = "serverIcon.svg",
                                srcImage2 =  "KtorLogoIcon.svg",
                                cardTitle = "Côté serveur:",
                                titleList = titleList,
                                descList = descListServer
                            )
                        }
                    }

                Button(
                    Modifier
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

                if(bp < Breakpoint.LG){
                    SmartphoneVideo(Modifier.margin(20.px))
                    DesktopVideo(Modifier.margin(20.px))
                }else {
                    Row(horizontalArrangement = Arrangement.SpaceAround) {
                        SmartphoneVideo(Modifier.margin(20.px).width(30.percent))
                        DesktopVideo(Modifier.margin(20.px).width(70.percent))
                    }
                }
            }
        }
        else -> {
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

                FaArrowLeft(
                    Modifier
                        .cursor(Cursor.Pointer)
                        .display(DisplayStyle.Flex)
                        .justifyContent(JustifyContent.Center)
                        .alignItems(AlignItems.Center)
                        .fontSize(2.em)
                        .gridArea("1", "1", "1", "2")
                        .width(80.px)
                        .height(80.px)
                        .color(Colors.White)
                        .onClick { ctx.router.navigateTo("/") },
                    size = IconSize.LG
                )
                H1(
                    Modifier
                        .textAlign(TextAlign.Center)
                        .gridArea("1", "7", "1", "9")
                        .color(Colors.White)
                        .margin(20.px)
                        .fontFamily("Arial")
                        .toAttrs()
                ) { Text("Karaoke App") }

                KaraokeAppCard(
                    modifier = Modifier
                        .gridArea("3", "1", "13", "4")
                        .margin(0.px, 20.px, 20.px, 20.px),
                    srcImage1 = "smartphoneIcon.svg",
                    srcImage2 = "androidNoCircleIcon.svg",
                    cardTitle = "Côté client:",
                    titleList = titleList,
                    descList = descListClient
                )
                KaraokeAppCard(
                    modifier = Modifier
                        .gridArea("1", "14", "10", "17")
                        .margin(20.px, 20.px, 0.px, 20.px),
                    srcImage1 = "serverIcon.svg",
                    srcImage2 = "KtorLogoIcon.svg",
                    cardTitle = "Côté serveur:",
                    titleList = titleList,
                    descList = descListServer
                )

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
    }
}


@Composable
fun KaraokeAppCard(
    modifier: Modifier,
    srcImage1: String,
    srcImage2: String,
    cardTitle: String,
    titleList: List<String>,
    descList: List<String>,
) {
    Div(
        modifier
            //.display(DisplayStyle.Block)
            .fontSize(2.em)
            .padding(1.em)
            .borderRadius(35.px)
            .backgroundColor(rgba(255,255,255,0.2))
            .fontFamily("Arial").toAttrs()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(src = srcImage1, modifier = Modifier.width(40.percent))
            Image(src = srcImage2, modifier = Modifier.width(40.percent))
        }
        H3 { Text(cardTitle) }
        Ul {
            for ((title, desc) in titleList.zip(descList)) {
                Li {
                    SpanText(
                        text = title,
                        modifier = Modifier.fontWeight(FontWeight.Bold)
                    ); Text(desc)
                }
            }
        }
    }
}

@Composable
fun SmartphoneVideo(modifier: Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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