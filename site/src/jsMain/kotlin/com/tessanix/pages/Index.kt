package com.tessanix.pages

import androidx.compose.runtime.Composable
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
import com.varabyte.kobweb.compose.ui.attrsModifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.*
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.svg.SVGElement


@Page
@Composable
fun HomePage() {
    val ctx = rememberPageContext()
    val vhOffset = 50
    val backgroundColor = "rgb(19 34 80)" //"rgb(25 33 90)

    Column(
        modifier = Modifier.backgroundColor(Color(backgroundColor)).fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally

    ){
        Section (
            Modifier
                .id("top-page")
                .width(100.percent)
                .height((100+vhOffset).vh)
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .justifyContent(JustifyContent.Center)
                .toAttrs()
        ) {
            CircularMotionCanvasAnimation(vhOffset = vhOffset, backgroundColor = backgroundColor)
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
            .id("my-skills")
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
            ) { Text(if(lang=="french") "Mon profil d'ingénieur logiciel:" else "My software engineer profile:") }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {

                SkillsWidget()
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        src = "CV_TESSAN_FR-1.png",
                        modifier = Modifier
                            .height(400.px)
                            .width(290.px)
                            .border(3.px, LineStyle.Solid, Colors.Gray)
                            .borderRadius(10.px)
                    )
                    Button(attrs = Modifier
                      .cursor(Cursor.Pointer)
                      .fontFamily("Arial Black")
                      .borderColor(Colors.White)
                      .borderRadius(35.px)
                      .margin(topBottom = 20.px)
                      .padding(10.px)
                      .color(Colors.White)
                      .backgroundColor(Color("rgba(250,250,250,0.3)"))
                      .toAttrs()
                    ) { Text(if(lang=="french") "Télécharger le CV" else "Download CV") }
                }
            }
        }

        Section(Modifier
            .id("my-work")
            .fillMaxWidth()
            .minWidth(900.px)
            .padding(bottom = 150.px)
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
            ){ Text(if(lang=="french") "Projets" else "Projects") }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Div (
                    attrs = ImageContainerProjectStyle.toModifier()
                        .onClick { ctx.router.navigateTo("KaraokeAppProject") }
                        .toAttrs()
                ) {
                    Image(
                        src = "logoAppInSmartphone3.svg",
                        modifier = ImageProjectStyle.toModifier().width(200.px)
                    )
                }
                Div (
                    attrs = ImageContainerProjectStyle.toModifier()
                        .onClick { ctx.router.navigateTo("KaraokeAppProject") }
                        .toAttrs()
                ) {
                    Image(
                        src = "websiteOnLaptop1.svg",
                        modifier = ImageProjectStyle.toModifier().height(300.px)
                    )
                }
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
                modifier = Modifier.fillMaxHeight()
            ) {

                Input(
                    type = InputType.Email,
                    attrs = {
                        placeholder("Email")
                        //defaultValue("Email") // Uncontrolled mode
                        onInput { event -> println(event.value) }
                        style {
                            //color(Colors.Black)
                            fontWeight(FontWeight.Bold)
                            backgroundColor(Color("rgba(250,250,250,0.3)"))
                            padding(0.7.em)
                            minWidth(450.px)
                            borderRadius(35.px)
                            borderColor(Colors.Black)
                        }
                    }
                )

                TextArea(
                    attrs = {
                        placeholder(if(lang=="french") "Ecris ton message..." else "Write your message...")
                        //defaultValue() // Uncontrolled mode
                        style {
                            fontSize(16.px)
                            //color(Colors.Black)
                            backgroundColor(Color("rgba(250,250,250,0.3)"))
                            minWidth(450.px)
                            minHeight(200.px)
                            borderRadius(35.px)
                            padding(1.em)
                            borderColor(Colors.Black)
                        }
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
                ) {
                    Text(if(lang=="french") "Envoyer" else "Send")
                }
            }
        }
    }

}

val ImageContainerProjectStyle by ComponentStyle{
    base { Modifier
        .display(DisplayStyle.Flex)
        .justifyContent(JustifyContent.Center)
        .margin(10.px)
        .width(30.percent)
    }
}

val ImageProjectStyle by ComponentStyle{
    base { Modifier
        .margin(10.px)
        .transition(
            CSSTransition("transform", 2.s, TransitionTimingFunction.EaseInOut)
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


