package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.functions.radialGradient
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.ComponentVariant
import com.varabyte.kobweb.silk.components.style.addVariant
import com.varabyte.kobweb.silk.components.style.toModifier
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.*

@Composable
fun CustomLi(text:String){

    var alpha1 by remember { mutableStateOf(0.0) }
    var alpha2 by remember { mutableStateOf(0.0) }

    var launchBackgroundAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(launchBackgroundAnimation){
        launchBackgroundAnimation = false
        while(0.0 < alpha1 && 0.0 < alpha2) {
            alpha1 -= 0.01; alpha2 -= 0.01
            delay(30)
        }
    }

    Li(
        Modifier
            .onMouseEnter { alpha1 = 0.2; alpha2 = 1.0 }
            .onMouseLeave { launchBackgroundAnimation = true }
            .margin(leftRight = 24.px)
            .padding(16.px)
            .borderRadius(30.percent)
            .backgroundImage(
                gradient = radialGradient {
                    add(Color("rgba(250,250,250, $alpha1)"), stop = 0.percent)
                    add(Color("rgba(10,10,10, $alpha2)"), stop=60.percent)
                }
            ) //Color("rgba(250,250,250,$alpha)")))
            .fontFamily("Arial")
            .color(Colors.White)
            .cursor(Cursor.Pointer)
            .fontSize(FontSize.XLarge)
            .toAttrs()
    ){ Text(text) }
}

@Composable
fun MyNav(variant: ComponentVariant){
    Nav {
        Ul(NavUlStyle.toModifier(variant).toAttrs()) {
            CustomLi("Haut de page")
            CustomLi("Mes compétences")
            CustomLi("Mes travaux")
            CustomLi("Me laisser un message")
            Li(Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .margin(leftRight = 24.px)
                .padding(16.px)
                .toAttrs()
            ){ ToggleButton() }
        }
    }
}

@Composable
fun NavBar(){
    var showSideBar by remember{ mutableStateOf(false) }

//    var windowWidth = produceState(initialValue = window.innerWidth) {
//       value = window.innerWidth
//    }
//
//    LaunchedEffect(windowWidth.value){
//        print("tu es le plus fort !!!")
//        if( windowWidth.value > 850) showSideBar = false
//    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = SideBarColumnStyle
            .toModifier()
            .left(if(showSideBar) 0.px else (-300).px)
    ) { MyNav(NavUlColumnStyle) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.width(100.percent)
    ) {
        MyNav(NavUlRowStyle)

        Div(
            Modifier
                .onClick { showSideBar = !showSideBar }
                .then(HamburgerStyle.toModifier())
                .toAttrs()
        ) {
            for(i in 1 .. 3){
                Div( attrs = Modifier
                    .width(30.px)
                    .height(2.px)
                    .margin(6.px, 0.px)
                    .backgroundColor(Colors.White)
                    .toAttrs()
                )
            }
        }
    }
}

val SideBarColumnStyle by ComponentStyle {
    base { Modifier
        .width(300.px)
        .height(100.vh)
        .position(Position.Fixed)
        .top(0.px)
        .left((-300).px) /* Offscreen by default */
        .transition(CSSTransition(
            TransitionProperty.of("left"),
            0.3.s,
            TransitionTimingFunction.EaseInOut
        ))
    }
}

val NavUlStyle by ComponentStyle {
    base { Modifier
        .listStyle("none")
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.SpaceAround)
        .margin(topBottom = 24.px)
    }
}

val NavUlColumnStyle by NavUlStyle.addVariant{
    base { Modifier.flexDirection(FlexDirection.Column) }
}


val NavUlRowStyle by NavUlStyle.addVariant {
    cssRule(CSSMediaQuery.MediaFeature("max-width", 850.px)) {
        Modifier.display(DisplayStyle.None)
    }
}

val HamburgerStyle by ComponentStyle {
    base { Modifier
        .cursor(Cursor.Pointer)
        .margin(leftRight = 10.px)
        .display(DisplayStyle.None)
    }
    cssRule(CSSMediaQuery.MediaFeature("max-width", 850.px)) {
        Modifier.display(DisplayStyle.Block)
    }
}

