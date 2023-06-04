package com.tessanix.pages

import androidx.compose.runtime.*
import com.tessanix.components.AnimatedParagraphe
import com.tessanix.components.CircularMotionCanvasAnimation
import com.tessanix.components.NavBar
import com.tessanix.components.SkillsWidget
import com.tessanix.lang
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.url
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
import com.varabyte.kobweb.silk.components.style.*
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.browser.window
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.*


@Page
@Composable
fun HomePage() {
    val bp = rememberBreakpoint()
    val ctx = rememberPageContext()
    val vhOffset = 50
    val backgroundColor = "rgb(19 34 80)" //"rgb(25 33 90)
    var canvasWidth by remember{ mutableStateOf(window.innerWidth.toDouble()) }
    var canvasHeight by remember{ mutableStateOf(window.innerHeight*1.5) }

    @Composable
    fun DivProjects(){
        Div( attrs = ImageContainerProjectStyle.toAttrs() ) {
            Image(
                src = "logoAppInSmartphone3.svg",
                modifier = ImageProjectStyle.toModifier()
                    .width(200.px)
                    .onClick { ctx.router.navigateTo("karaokeappproject") }
            )
        }
        Div ( attrs = ImageContainerProjectStyle.toModifier()
                .padding(10.percent).toAttrs()
        ) {
            Image(
                src = "websiteOnLaptop1.svg",
                modifier = ImageProjectStyle.toModifier()
                    .maxHeight(300.px)
                    .width(100.percent)
                    .onClick { ctx.router.navigateTo("kobwebsiteproject") }
            )
        }
    }


    Column(
        modifier = Modifier.backgroundColor(Color(backgroundColor)).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Section (
            Modifier
                //.id("top-page")
                .width(100.percent)
                .height((100+vhOffset).vh)
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .justifyContent(JustifyContent.Center)
                .toAttrs()
        ) {
            when {
                bp < Breakpoint.SM -> {
                    canvasWidth = window.innerWidth.toDouble()
                    canvasHeight = window.innerHeight*1.5
                    CircularMotionCanvasAnimation(canvasWidth, canvasHeight, vhOffset = vhOffset, backgroundColor = backgroundColor)
                }
                bp < Breakpoint.MD -> {
                    canvasWidth = window.innerWidth.toDouble()
                    canvasHeight = window.innerHeight*1.5
                    CircularMotionCanvasAnimation(canvasWidth, canvasHeight, vhOffset = vhOffset, backgroundColor = backgroundColor)
                }
                bp < Breakpoint.LG -> {
                    canvasWidth = window.innerWidth.toDouble()
                    canvasHeight = window.innerHeight*1.5
                    CircularMotionCanvasAnimation(canvasWidth, canvasHeight, vhOffset = vhOffset, backgroundColor = backgroundColor)
                }
                bp < Breakpoint.XL -> {
                    canvasWidth = window.innerWidth.toDouble()
                    canvasHeight = window.innerHeight*1.5
                    CircularMotionCanvasAnimation(canvasWidth, canvasHeight, vhOffset = vhOffset, backgroundColor = backgroundColor)
                }
                else -> {
                    canvasWidth = window.innerWidth.toDouble()
                    canvasHeight = window.innerHeight*1.5
                    CircularMotionCanvasAnimation(canvasWidth, canvasHeight, vhOffset = vhOffset, backgroundColor = backgroundColor)
                }
            }
        }

        Section(Modifier
            .fillMaxWidth()
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .alignItems(AlignItems.Center)
            .padding(10.px)
            .margin(top = (-vhOffset).vh)
            .textAlign(TextAlign.Center)//.filter(blur(10.px))
            .toAttrs()
        ) {
            NavBar()

            H3(
                Modifier
                    .fontFamily("Arial Black")
                    .color(rgb(113, 187, 215))
                    .margin(top=150.px)
                    .toAttrs()
            ) {
                if(lang=="french"){
                    Text("Salut! Moi, Tessan, je suis la solution qu'il te faut!"); Br()
                    Text("En quelques mots:"); Br()
                }
                else{
                    Text("Hi! I, Tessan, am the solution you need!"); Br()
                    Text("In few words:"); Br()
                }
            }
            AnimatedParagraphe(
                if(lang=="french") listOf("- Passioné", "- Curieux", "- Optimiste")
                else listOf("- Passionate", "- Curious", "- Optimistic")
            )
        }

        Section(Modifier
            .id("my-profile")
            .fillMaxWidth()
            .color(Colors.White)
            .textAlign(TextAlign.Center)
            .padding(10.px)
            .toAttrs()
        ) {
            H1(TitleUnderlinedStyle
                .toModifier()
                .fontSize(2.em)
                .fontFamily("Arial Black")
                .margin(topBottom = 90.px)
                .toAttrs()
            ) { Text(if(lang=="french") "Mon profil:" else "My profile:") }

            if(bp < Breakpoint.SM) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    H3(
                        Modifier
                            .margin(2.em)
                            .fontSize(1.5.em)
                            .fontFamily("Arial Black")
                            .toAttrs()
                    ) { Text(if (lang == "french") "Mes compétences:" else "My skills:") }

                    SkillsWidget(bp)

                    H3(
                        Modifier
                            .margin(2.em)
                            .fontSize(1.5.em)
                            .fontFamily("Arial Black")
                            .toAttrs()
                    ) { Text(if (lang == "french") "Mon CV:" else "My Resume:") }

                    Image(
                        src = "CV_TESSAN_FR-1.png",
                        modifier = Modifier
                            .height(400.px)
                            .width(290.px)
                            .border(3.px, LineStyle.Solid, Colors.Gray)
                            .borderRadius(10.px)
                    )
                    Button(
                        attrs = Modifier
                            .cursor(Cursor.Pointer)
                            .fontFamily("Arial Black")
                            .borderColor(Colors.White)
                            .borderRadius(35.px)
                            .margin(topBottom = 20.px)
                            .padding(10.px)
                            .color(Colors.White)
                            .backgroundColor(Color("rgba(250,250,250,0.3)"))
                            .toAttrs()
                    ) { Text(if (lang == "french") "Télécharger" else "Download") }
                }
            } else{
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        H3(
                            Modifier
                                .margin(2.em)
                                .fontSize(1.5.em)
                                .fontFamily("Arial Black")
                                .toAttrs()
                        ) { Text(if (lang == "french") "Mes compétences:" else "My skills:") }

                        SkillsWidget(bp)
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        H3(
                            Modifier
                                .margin(2.em)
                                .fontSize(1.5.em)
                                .fontFamily("Arial Black")
                                .toAttrs()
                        ) { Text(if (lang == "french") "Mon CV:" else "My Resume:") }

                        Image(
                            src = "CV_TESSAN_FR-1.png",
                            modifier = Modifier
                                .height(400.px)
                                .width(290.px)
                                .border(3.px, LineStyle.Solid, Colors.Gray)
                                .borderRadius(10.px)
                        )
                        Button(
                            attrs = Modifier
                                .cursor(Cursor.Pointer)
                                .fontFamily("Arial Black")
                                .borderColor(Colors.White)
                                .borderRadius(35.px)
                                .margin(topBottom = 20.px)
                                .padding(10.px)
                                .color(Colors.White)
                                .backgroundColor(Color("rgba(250,250,250,0.3)"))
                                .toAttrs()
                        ) { Text(if (lang == "french") "Télécharger" else "Download") }
                    }
                }
            }
        }

        Section(Modifier
            .id("my-work")
            .fillMaxWidth()
            //.minWidth(900.px)
            //.padding(bottom = 150.px)
            .backgroundImage(
               linearGradient(dir = LinearGradient.Direction.ToBottom) {
                    add(Color(backgroundColor), stop = 0.percent)
                    add(rgb(0, 0, 0), stop = 100.percent)
               }
            ).display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .alignItems(AlignItems.Center)
            .color(Colors.White)
            .toAttrs()
        ) {
            H1(TitleUnderlinedStyle
                .toModifier()
                .fontSize(2.em)
                .margin(50.px, 0.px)
                .fontFamily("Arial")
                .toAttrs()
            ){ Text(if(lang=="french") "Mes travaux" else "My work") }
            if(bp < Breakpoint.LG){
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { DivProjects() }
            }else {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) { DivProjects() }
            }
        }

        Footer(Modifier
            .id("contact")
            .fillMaxWidth()
            .minHeight(600.px)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .alignItems(AlignItems.Center)
            .backgroundImage(url("horizonPlanet_1.jpg"))
            .backgroundRepeat(BackgroundRepeat.NoRepeat)
            .backgroundSize(BackgroundSize.Cover)
            .toAttrs()
        ) {
            H1(TitleUnderlinedStyle
                .toModifier()
                .color(Colors.White)
                .fontFamily("Arial")
                .toAttrs()
            ){ Text( if(lang=="french") "Me contacter" else "Contact me") }

            H3(Modifier
                .display(DisplayStyle.InlineBlock)
                .color(Colors.White)
                .fontFamily("Arial")
                .toAttrs()
            ){ Text( if(lang=="french") "Merci de votre visite!" else "Thank you for your visit!") }

            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight().fillMaxWidth()
            ) {

                Input(
                    type = InputType.Email,
                    attrs = InputStyle.toAttrs {
                        placeholder("Email")
                        onInput { event -> println(event.value) }
                    }
                )

                TextArea(
                    attrs = InputStyle
                        .toModifier()
                        .minHeight(200.px)
                        .toAttrs {
                            placeholder(if(lang=="french") "Ecris ton message..." else "Write your message...")
                        }
                )

                Button(
                    attrs = Modifier
                        .cursor(Cursor.Pointer)
                        .padding(10.px)
                        .fontFamily("Arial Black")
                        .color(Colors.White)
                        .backgroundColor(Color("rgba(250,250,250,0.3)"))
                        .borderColor(Colors.White)
                        .borderRadius(35.px)
                        .toAttrs()
                ) { Text(if(lang=="french") "Envoyer" else "Send") }
            }
        }
    }
}


val InputStyle by ComponentStyle{
    base { Modifier
        .fontWeight(FontWeight.Bold)
        .backgroundColor(Color("rgba(250,250,250,0.3)"))
        .padding(1.em)
        .minWidth(300.px)
        .width(30.percent)
        .borderRadius(35.px)
        .borderColor(Colors.Black)
    }
    cssRule("::placeholder"){
        Modifier.color(Colors.Black)
    }
}

val ImageContainerProjectStyle by ComponentStyle{
    base { Modifier
        .display(DisplayStyle.Flex)
        .justifyContent(JustifyContent.Center)
        .margin(10.px)
        .width(100.percent)
    }
}

val ImageProjectStyle by ComponentStyle{
    base { Modifier
        .margin(10.px)
        .cursor(Cursor.Pointer)
        .transition(
            CSSTransition("all", 1.s, TransitionTimingFunction.Ease)
        )
    }
    hover { Modifier.scale(1.3) }
}

val TitleUnderlinedStyle by ComponentStyle {
    base {
        Modifier.display(DisplayStyle.InlineBlock)
            .position(Position.Relative)

    }
    after {
        Modifier.content("")
            .position(Position.Absolute)
            .left(5.percent)
            .bottom((-5).px)
            .backgroundColor(Colors.White)
            .width(90.percent)
            .height(5.px)
    }
}


