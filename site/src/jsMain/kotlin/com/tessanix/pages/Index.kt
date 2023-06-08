package com.tessanix.pages

import androidx.compose.runtime.Composable
import com.tessanix.components.*
import com.tessanix.lang
import com.tessanix.mainBackgroundColor
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.LinearGradient
import com.varabyte.kobweb.compose.css.functions.linearGradient
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.*
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.style.*
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
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
                    .maxHeight(384.px)
                    .thenIf(bp < Breakpoint.SM, Modifier.width(100.percent))
                    .onClick { ctx.router.navigateTo("kobwebsiteproject") }
            )
        }
    }


    Column(
        modifier = Modifier
            .backgroundColor(Color(mainBackgroundColor))
            .overflow(Overflow.Hidden)
            .fillMaxSize(),
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
        ) { CircularMotionCanvasAnimation(bp, vhOffset = vhOffset) }

        Section(Modifier
            .fillMaxWidth()
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .alignItems(AlignItems.Center)
            .padding(10.px)
            .margin(top = (-vhOffset).vh)
            .height(vhOffset.vh)
            .textAlign(TextAlign.Center)
            .toAttrs()
        ) {
            NavBar()

            H3(
                Modifier
                    .zIndex(2)
                    .fontFamily("Arial Black")
                    .color(rgb(113, 187, 215))
                    .margin(top=150.px)
                    .toAttrs()
            ) {
                if(lang=="french"){
                    Text("Je suis un Ingénieur Logiciel prêt à coder!"); Br()
                    Text("Si tu veux me connaître en quelques mots, je suis:"); Br()
                }
                else{
                    Text("I am a Software Engineer ready to code!"); Br()
                    Text("If you want to know me in few words, I am:"); Br()
                }
            }
            AnimatedParagraphe(
                Modifier.zIndex(2),
                if(lang=="french") setOf(
                    "- Passioné" to rgb(25,91,175),
                    "- Curieux" to rgb(22,151,215),
                    "- Optimiste" to rgb(12,198,209)
                )
                else setOf(
                    "- Passionate" to rgb(25,91,175),
                    "- Curious" to rgb(22,151,215),
                    "- Optimistic" to rgb(12,198,209)
                )
            )
        }
        Section(Modifier
            .id("my-profile")
            .position(Position.Relative)
            .fillMaxWidth()
            .backgroundColor(Color(mainBackgroundColor))
            .color(Colors.White)
            .textAlign(TextAlign.Center)
            .padding(10.px)
            .toAttrs()
        ) {
            Div(
                Modifier
                    .position(Position.Absolute)
                    .height(600.px)
                    .width(130.percent)
                    .translateY((-100).percent)
                    .left((-15).percent)
                    .styleModifier {
                        property(
                            "background-image",
                            "linear-gradient(to top, $mainBackgroundColor, rgba(12, 12, 12, 0.1) 50%)," +
                                    "radial-gradient(ellipse 100% 110% at bottom center, $mainBackgroundColor, rgba(12, 12, 12, 0.1) 70%)"
                        )

                    }
                    .pointerEvents(PointerEvents.None)
                    .toAttrs()
            )
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

                    ResumeLayout(true)
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

                    ResumeLayout(false)
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
                    add(Color(mainBackgroundColor), stop = 0.percent)
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


