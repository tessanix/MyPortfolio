package com.tessanix.pages

import androidx.compose.runtime.Composable
import com.tessanix.D_PATH_WAVE
import com.tessanix.components.CircularMotionCanvasAnimation
import com.tessanix.components.NavBar
import com.tessanix.components.SkillsWidget
import com.tessanix.lang
import com.varabyte.kobweb.compose.css.BackgroundRepeat
import com.varabyte.kobweb.compose.css.BackgroundSize
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.borderColor
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
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.addVariant
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.ExperimentalComposeWebSvgApi
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.svg.Path
import org.jetbrains.compose.web.svg.SvgElement


@OptIn(ExperimentalComposeWebSvgApi::class)
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
            //.height(80.vh)
            .padding(10.px)
            .margin(top = (-vhOffset).vh)
            .textAlign(TextAlign.Center)//.filter(blur(10.px))
            .toAttrs()
        ) {
            NavBar()

            H1(Modifier
                .fontFamily("Arial Black")
                .color(Color("rgb(113 187 215)"))
                .margin(topBottom = 150.px)
                .toAttrs()
            ) {
                if(lang=="french") {
                    Text("Salut! Moi, Tessan, je suis la solution qu'il te faut!"); Br()
                    Text("En quelques mots:"); Br()
                    Text("- Passioné"); Br()
                    Text("- Curieux"); Br()
                    Text("- Optimiste"); Br()
                }else{
                    Text("Hi! I, Tessan, am the solution you need!"); Br()
                    Text("In few words:"); Br()
                    Text("- Passionate"); Br()
                    Text("- Curious"); Br()
                    Text("- Optimistic"); Br()
                }
            }
        }

        Section(Modifier
            .fillMaxWidth()
            .color(Colors.White)
            .textAlign(TextAlign.Center)
            .padding(10.px)
            .toAttrs()
        ) {
            H1(Modifier
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
                    Image(src = "CV_TESSAN_FR-1.png", modifier = Modifier.height(400.px).width(290.px))
                    Button(attrs = Modifier
                      .margin(10.px)
                      .width(100.px)
                      .height(50.px)
                      .color(Colors.White)
                      .backgroundColor(Color("rgba(250,250,250,0.3)"))
                      .borderRadius(5.px)
                      .toAttrs()
                    ) { Text(if(lang=="french") "Téléchercher le CV" else "Download CV") }
                }
            }
        }

        Section(Modifier
            .fillMaxWidth()
            .color(Colors.White)
            .toAttrs()
        ) {
            H3(Modifier
                .textAlign(TextAlign.Center)
                .margin(50.px)
                .fontFamily("Arial")
                .toAttrs()
            ){ Text(if(lang=="french") "Projets" else "Projects") }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Div (
                    attrs = Modifier
                        .onClick { ctx.router.navigateTo("KaraokeAppProject") }
                        .then(DivProjectStyle.toModifier(DivProjectKaraokeAppVariant))
                        .toAttrs()
                ) {  }
                Div (
                    attrs = Modifier
                        .onClick { ctx.router.navigateTo("KaraokeAppProject") }
                        .then(DivProjectStyle.toModifier(DivProjectPortfolioVariant))
                        .toAttrs()
                ) {  }
                Div (
                    attrs = Modifier
                        .margin(10.px)
                        .width(30.percent)
                        .backgroundColor(Colors.Red)
                        .toAttrs()
                ) {  }
            }

            Div ( attrs = Modifier
                    .height(100.px)
                    .toAttrs()
            ) {  }
            SvgElement("svg",
                attrs = Modifier
                    .fillMaxWidth()
                    .height(220.px)
                    .margin(bottom = (-5).px)
                    .toAttrs{
                        attr("preserveAspectRatio", "none")
                        attr("viewBox","0 0 1440 260")
                    }
            ){
                Path(
                    d = D_PATH_WAVE,
                    attrs = {
                        //style { property("transform","translate(0, 0px)") }
                        attr("fill", "rgb(30, 53, 84)")
                    }
                )
            }
        }

        Footer(Modifier
            .fillMaxWidth()
            .height(600.px)
            .display(DisplayStyle.Flex)
            .flexDirection(FlexDirection.Column)
            .backgroundColor(Color("rgb(30, 53, 84)"))
            .toAttrs()
        ) {
            H3(Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .justifyContent(JustifyContent.Center)
                .color(Colors.White)
                .fontFamily("Arial")
                .gridArea("3", "1", "4", "2")
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
                        defaultValue("Email") // Uncontrolled mode
                        onInput { event -> println(event.value) }
                        style {
                            color(Colors.White)
                            backgroundColor(Color("rgba(250,250,250,0.3)"))
                            width(500.px)
                            borderRadius(5.px)
                            borderColor(Colors.White)
                        }
                    }
                )

                TextArea(
                    attrs = {
                        defaultValue(if(lang=="french") "Ecris ton message ..." else "Write your message ...") // Uncontrolled mode
                        style {
                            color(Colors.White)
                            backgroundColor(Color("rgba(250,250,250,0.3)"))
                            width(500.px)
                            height(200.px)
                            borderRadius(5.px)
                            borderColor(Colors.White)
                        }
                    }
                )

                Button(
                    attrs = Modifier
                        .width(100.px)
                        .height(50.px)
                        .color(Colors.White)
                        .backgroundColor(Color("rgba(250,250,250,0.3)"))
                        .borderRadius(5.px)
                        .toAttrs()
                ) {
                    Text(if(lang=="french") "Envoyer" else "Send")
                }
            }
        }
    }

}


val DivProjectStyle by ComponentStyle{
    base { Modifier
        .margin(10.px)
        .width(30.percent)
        .height(400.px)
        .backgroundSize(BackgroundSize.of(100.percent, 100.percent))
        .backgroundRepeat(BackgroundRepeat.NoRepeat)
    }
    hover { Modifier.scale(1.3) }

//    Breakpoint.XL {
//
//    }
}

val DivProjectKaraokeAppVariant by DivProjectStyle.addVariant {
   base { Modifier.backgroundImage(url("logoAppInSmartphoneMockup.svg")) }
}

val DivProjectPortfolioVariant by DivProjectStyle.addVariant {
    base { Modifier.backgroundImage(url("websiteInDesktopMockupRight.svg")) }
}





