package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TransitionTimingFunction
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowLeft
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowRight
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.style.toAttrs
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.keywords.auto
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text

@Composable
fun SkillsWidget(){
    val bp = rememberBreakpoint()
    val progressBarWidth = 140
    val progressBarHeight = 140

    if (bp < Breakpoint.LG) SkillsWidgetCompact(progressBarWidth, progressBarHeight)
    else SkillsWidgetGrid(progressBarWidth, progressBarHeight)
}

@Composable
fun SkillsWidgetGrid(progressBarWidth: Int, progressBarHeight: Int) {

    Div(
        Modifier.display(DisplayStyle.Grid)
            .gridTemplateRows("1fr 1fr 1fr")
            .gridTemplateColumns("1fr 1fr 1fr 1fr")
            .toAttrs()
    ) {
        H3( TextSkillsStyle.toModifier()
            .gridArea("1", "1", "2", "2")
            .toAttrs()
        ){ Text("Langages: ") }

        programmingLanguages[0](Modifier.gridArea("1", "2", "2", "3"), progressBarWidth, progressBarHeight)
        programmingLanguages[1](Modifier.gridArea("1", "3", "2", "4"), progressBarWidth, progressBarHeight)
        programmingLanguages[2](Modifier.gridArea("1", "4", "2", "5"), progressBarWidth, progressBarHeight)

        H3( TextSkillsStyle.toModifier()
            .gridArea("2", "1", "3", "2")
            .toAttrs()
        ){ Text("Positions: ") }

        positions[0](Modifier.gridArea("2", "2", "3", "3"), progressBarWidth, progressBarHeight)
        positions[1](Modifier.gridArea("2", "3", "3", "4"), progressBarWidth, progressBarHeight)

        H3(
            TextSkillsStyle.toModifier()
            .gridArea("3", "1", "4", "2")
            .toAttrs()
        ){ Text("Autres Technologies: ") }

        otherTechs[0](Modifier.gridArea("3", "2", "4", "3"), progressBarWidth, progressBarHeight)
        otherTechs[1](Modifier.gridArea("3", "3", "4", "4"), progressBarWidth, progressBarHeight)
    }
}

@Composable
fun SkillsWidgetCompact(progressBarWidth:Int, progressBarHeight:Int) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        H3( TextSkillsStyle.toAttrs() ){ Text("Langages: ") }
        Slider(progressBarWidth, progressBarHeight, programmingLanguages)
        H3( TextSkillsStyle.toAttrs() ){ Text("Positions: ") }
        Slider(progressBarWidth, progressBarHeight, positions)
        H3( TextSkillsStyle.toAttrs() ){ Text("Autres Technologies: ") }
        Slider(progressBarWidth, progressBarHeight, otherTechs)
    }
}

@Composable
fun Slider(
    progressBarWidth: Int,
    progressBarHeight: Int,
    elements: List<@Composable  (m:Modifier, w:Int, h:Int) -> Unit>
){
    val progressBarRightLeftMargin = 20

    val sliderHeight = 230
    val sliderWidth = progressBarWidth + 2*progressBarRightLeftMargin

    var counter by remember { mutableStateOf(0) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {

        FaArrowLeft(
            modifier = Modifier
                .cursor(Cursor.Pointer)
                .onClick { counter-- }
        )

        Div(ContainerSliderStyle
            .toModifier()
            .width(sliderWidth.px)
            .height(sliderHeight.px)
            .toAttrs()
        ) {
            if(counter == elements.size) counter = 0
            if(counter == -1) counter = elements.size-1

            Row(modifier = SliderStyle
                    .toModifier()
                    .left( (-sliderWidth*counter).px )
            ) {
                for (element in elements) {
                    element(
                        Modifier
                        .width(auto)
                        .height(auto)
                        .margin(0.px, progressBarRightLeftMargin.px),
                        progressBarWidth,
                        progressBarHeight
                    )
                }
            }
        }

        FaArrowRight(
            modifier = Modifier
                .cursor(Cursor.Pointer)
                .onClick { counter++ }
        )
    }

}

val TextSkillsStyle by ComponentStyle {
    base { Modifier
        .display(DisplayStyle.Flex)
        .alignItems(AlignItems.Center)
        .justifyContent(JustifyContent.Center)
        .fontFamily("Arial")
    }
}

val ContainerSliderStyle by ComponentStyle{
    base { Modifier
        .position(Position.Relative)
        .display(DisplayStyle.Flex)
        .flexDirection(FlexDirection.Column)
        .justifyContent(JustifyContent.Center)
        .alignItems(AlignItems.Center)
        //.border(width=1.px, LineStyle.Solid, color=Colors.Black)
        .margin(0.px, 20.px)
        .overflow(Overflow.Hidden)
    }
}

val SliderStyle by ComponentStyle{
    base { Modifier
        .position(Position.Absolute)
        .transition(
            CSSTransition(
                "left", 1.s,
                TransitionTimingFunction.EaseInOut
            )
        )
    }
}
