package com.tessanix.components

import androidx.compose.runtime.*
import com.tessanix.lang
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaX
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.addVariant
import com.varabyte.kobweb.silk.components.style.after
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.dom.*

@Composable
fun CustomLi(
    text:String,
    href: String
){
    var isMouseOver by remember { mutableStateOf(false) }

    Li(
        Modifier
            .onMouseEnter { isMouseOver = true }
            .onMouseLeave { isMouseOver = false }
            .margin(leftRight = 24.px)
            .padding(16.px)
            .thenIf(isMouseOver, MenuTitleUnderlinedStyle.toModifier(MenuTitleUnderlinedGrow) )
            .thenIf(!isMouseOver, MenuTitleUnderlinedStyle.toModifier(MenuTitleUnderlinedShrink) )
            .fontFamily("Arial")
            .fontSize(FontSize.XLarge)
            .toAttrs()
    ){
        A(
            href = href,
            attrs = Modifier
                .textDecorationLine(TextDecorationLine.None)
                .color(Colors.White)
                .toAttrs()
        ) { Text(text) }
    }
}

@Composable
fun MyNav( addX:Boolean=false, modifier:Modifier, onCloseNav:(()->Unit)?=null ) { //variant: ComponentVariant){
    Nav(modifier.toAttrs()) {
        if(addX && onCloseNav!=null)
            FaX(
                modifier = Modifier
                    .display(DisplayStyle.Flex)
                    .justifyContent(JustifyContent.Center)
                    .alignItems(AlignItems.Center)
                    .width(100.percent)
                    .height(50.px)
                    .margin(top=20.px)
                    .color(Colors.White)
                    .cursor(Cursor.Pointer)
                    .onClick { onCloseNav() },
                size = IconSize.LG
            )

        Ul(
            Modifier
            .listStyle("none")
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .flexDirection(if(addX) FlexDirection.Column else FlexDirection.Row)
            .justifyContent(JustifyContent.SpaceAround)
            .margin(topBottom = 24.px)
            .padding(0.px).toAttrs()
        ) {

            //CustomLi(if(lang=="french") "Haut de page" else "Page top", "#top-page")
            CustomLi(if(lang=="french") "Mon profil" else "My profile", "#my-profile")
            CustomLi(if(lang=="french") "Mes travaux" else "My work", "#my-work")
            CustomLi("Contact", "#contact")

            Li(Modifier
                .display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .margin(leftRight = 24.px)
                .padding(16.px)
                .toAttrs()
            ){ ToggleButton(onCheckedFunc = { lang = if(it) "french" else "english" }) }
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
            addX =true,
            modifier = NavStyle.toModifier(NavColumnStyle)
            .left(if(isSideBarShown) 0.px else (-300).px),
            onCloseNav = { isSideBarShown = false }
        )
        Hamburger(
            isSideBarShown = isSideBarShown,
            showSideBarFunc = {isSideBarShown = !isSideBarShown}
        )
    }
    else MyNav(modifier = NavStyle.toModifier())
}

val NavStyle by ComponentStyle {
    base { Modifier
        .zIndex(3)
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
    }
}

val NavColumnStyle by NavStyle.addVariant{
    base { Modifier
        .flexDirection(FlexDirection.Column)
        .backgroundColor(Color("rgba(10,10,10,0.2)"))
        .width(300.px)
        .height(100.vh)
        .position(Position.Fixed)
        .top(0.px)
        .left(0.px) /* Offscreen by default */
        .transition(CSSTransition(
            "left", 0.3.s,
            TransitionTimingFunction.EaseInOut
        ))//.pointerEvents(PointerEvents.None)
    }
}

val MenuTitleUnderlinedStyle by ComponentStyle {
    base { Modifier.position(Position.Relative) }
    after {
        Modifier.content("")
            .position(Position.Absolute)
            .left(5.percent)
            .bottom((-5).px)
            .backgroundColor(Colors.White)
            .height(5.px)
            .transition(CSSTransition("all", 1.5.s, AnimationTimingFunction.Ease ))
    }
}

val MenuTitleUnderlinedGrow by MenuTitleUnderlinedStyle.addVariant {
    after { Modifier.width(90.percent) }
}

val MenuTitleUnderlinedShrink by MenuTitleUnderlinedStyle.addVariant {
    after { Modifier.width(0.percent) }
}

val HamburgerStyle by ComponentStyle {
    base { Modifier
        .cursor(Cursor.Pointer)
        .margin(leftRight = 10.px)
        .display(DisplayStyle.Block)
    }
}


