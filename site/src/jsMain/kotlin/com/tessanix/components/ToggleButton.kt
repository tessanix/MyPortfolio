package com.tessanix.components

import androidx.compose.runtime.*
import com.tessanix.lang
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.css.functions.url
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.style.*
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.CheckboxInput
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Label


@Composable
fun ToggleButton(onCheckedFunc: (Boolean)->Unit){
    var checked by remember { mutableStateOf(true) }

    Label(
        //forId = inputId,
        attrs = Modifier.width(60.px).height(30.px).toAttrs()
    ) {
        CheckboxInput(
            checked,
            attrs = Modifier
                //.id(inputId)
                .display(DisplayStyle.None)
                .onClick {
                    checked = !checked
                    onCheckedFunc(checked)
                }
                .then(CheckboxToggleButtonStyle.toModifier())
                .toAttrs()
        )
        Div( attrs =
            if(lang=="french")
                DivToggleButtonStyle.toAttrs(DivToggleFrenchBefore)
            else
                DivToggleButtonStyle.toAttrs(DivToggleEnglishBefore)
        )
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
        .top(0.px)
        .left(0.px)
        .backgroundRepeat(BackgroundRepeat.NoRepeat)
        .backgroundSize(BackgroundSize.Contain)
        .transition(
            CSSTransition(
                "transform",
                0.5.s
            )
        )
    }
}

val DivToggleFrenchBefore by DivToggleButtonStyle.addVariant{
    before {
        Modifier.backgroundImage(url("franceFlag.png"))
    }
}

val DivToggleEnglishBefore by DivToggleButtonStyle.addVariant{
    before {
        Modifier.backgroundImage(url("united-statesFlag.png"))
    }
}