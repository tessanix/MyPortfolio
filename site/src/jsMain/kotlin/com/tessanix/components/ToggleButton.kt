package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TransitionProperty
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.before
import com.varabyte.kobweb.silk.components.style.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label


@Composable
fun ToggleButton(){
    var checked by remember { mutableStateOf(false) }

    Label(
        //forId = inputId,
        attrs = Modifier.width(60.px).height(30.px).toAttrs()
    ) {
        CheckboxInput(
            checked,
            attrs = Modifier
                //.id(inputId)
                .display(DisplayStyle.None)
                .onClick { checked = !checked }
                .then(CheckboxToggleButtonStyle.toModifier())
                .toAttrs()
        )
        Div( attrs = DivToggleButtonStyle.toAttrs() )
    }
}

val CheckboxToggleButtonStyle by ComponentStyle {
    cssRule(":checked ~ .div-toggle-button::before") {
        Modifier.translateX(30.px)
    }
}

val DivToggleButtonStyle by ComponentStyle {
    val width = 60.px
    val height = 30.px

    base { Modifier
        .position(Position.Relative)
        .height(height)
        .width(width)
        .cursor(Cursor.Pointer)
        .backgroundColor(Colors.Gray)
        .borderRadius(34.px)
        .transition(
            CSSTransition(
                TransitionProperty.of("background"),
                0.2.s
            )
        )
    }
    before { Modifier
        .content("")
        .position(Position.Absolute)
        .height(height)
        .width(height)
        .boxShadow(0.px, 0.px, 10.px, color = Color("rgba(0,0,0,0.25)"))
        .borderRadius(50.percent)
        .backgroundColor(Colors.White)
        .top(0.px)
        .left(0.px)
        .transition(
            CSSTransition(
                TransitionProperty.of("transform"),
                0.2.s
            )
        )
    }
}
