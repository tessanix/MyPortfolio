package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.addVariant
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import kotlinx.coroutines.delay
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.*

@Composable
fun CustomLi(text:String){

    var alpha1 by remember { mutableStateOf(0.0) }

    var launchBackgroundAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(launchBackgroundAnimation) {
        launchBackgroundAnimation = false
        while(0.0 < alpha1){
            alpha1 -= 0.025; delay(30)
        }
    }

    Li(
        Modifier
            .onMouseEnter { alpha1 = 0.6 }
            .onMouseLeave { launchBackgroundAnimation = true }
            .margin(leftRight = 24.px)
            .padding(16.px)
            .borderRadius(50.percent)
            .styleModifier {
                property(
                    "box-shadow",
                    "0px 0px 30px 35px rgba(250,250,250, $alpha1),"+
                            "inset 0px 0px 9px 10px rgba(250,250,250, $alpha1)"
                )
            }
            .fontFamily("Arial")
            .color(Colors.White)
            .cursor(Cursor.Pointer)
            .fontSize(FontSize.XLarge)
            .toAttrs()
    ){ Text(text) }
}

@Composable
fun MyNav( modifier:Modifier ) { //variant: ComponentVariant){
    Nav {
        Ul(modifier.toAttrs()) {
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
fun Hamburger(
    isSideBarShown: Boolean,
    showSideBarFunc: (Boolean)->Unit
){
    Div(
        Modifier
            .onClick { showSideBarFunc(isSideBarShown) }
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

@Composable
fun NavBar(){
    var isSideBarShown by remember{ mutableStateOf(false) }
    val bp = rememberBreakpoint()

    if(bp < Breakpoint.MD){
        MyNav(
            NavUlStyle.toModifier(NavUlColumnStyle)
            .left(if(isSideBarShown) 0.px else (-300).px)
        )
        Hamburger(
            isSideBarShown = isSideBarShown,
            showSideBarFunc = {isSideBarShown =!isSideBarShown}
        )
    }
    else MyNav(NavUlStyle.toModifier())
}

val NavUlStyle by ComponentStyle {
    base { Modifier
        .zIndex(5)
        .listStyle("none")
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.SpaceAround)
        .margin(topBottom = 24.px)
    }
}

val NavUlColumnStyle by NavUlStyle.addVariant{
    base { Modifier
        .flexDirection(FlexDirection.Column)
        .width(300.px)
        .height(100.vh)
        .position(Position.Fixed)
        .top(0.px)
        .left(0.px) /* Offscreen by default */
        .transition(CSSTransition(
            "left", 0.3.s,
            TransitionTimingFunction.EaseInOut
        ))
    }
}



val HamburgerStyle by ComponentStyle {
    base { Modifier
        .cursor(Cursor.Pointer)
        .margin(leftRight = 10.px)
        .display(DisplayStyle.Block)
    }
}

