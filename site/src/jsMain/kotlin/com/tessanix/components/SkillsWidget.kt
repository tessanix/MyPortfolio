package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text


@Composable
fun SkillsWidget() {
    Div(
        Modifier.display(DisplayStyle.Grid)
            .gridTemplateRows("1fr 1fr 1fr")
            .gridTemplateColumns("1fr 1fr 1fr 1fr")
            .toAttrs()
    ) {
        H3( Modifier
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .fontFamily("Arial")
            .gridArea("1", "1", "2", "2")
            .toAttrs()
        ){ Text("Langages: ") }

        CircularProgressBar(
            Modifier.gridArea("1", "2", "2", "3"),
            gradientColorStart = "rgb(61,122,171)",
            gradientColorEnd = "rgb(252,234,116)",
            value=85,
            textLabel="Python")
        CircularProgressBar(
            Modifier.gridArea("1", "3", "2", "4"),
            gradientColorStart = "rgb(49,114,174)",
            gradientColorEnd = "rgb(187,209,230)",
            value=65,
            textLabel="C++")
        CircularProgressBar(
            Modifier.gridArea("1", "4", "2", "5"),
            gradientColorStart = "rgb(183,38,222)",
            gradientColorEnd = "rgb(222,62,113)",
            value=70,
            textLabel="Kotlin")

        H3( Modifier
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .fontFamily("Arial")
            .gridArea("2", "1", "3", "2")
            .toAttrs()
        ){ Text("Positions: ") }

        CircularProgressBar(
            Modifier.gridArea("2", "2", "3", "3"),
            gradientColorStart = "rgb(255, 87, 51)",
            gradientColorEnd = "rgb(20, 215, 192)",
            value = 55,
            textLabel = "Mobile/Web FrontEnd")
        CircularProgressBar(
            Modifier.gridArea("2", "3", "3", "4"),
            gradientColorStart = "rgb(5,5,5)",
            gradientColorEnd = "rgb(57,255,20)",
            value = 65,
            textLabel = "BackEnd")

        H3(
            Modifier
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .fontFamily("Arial")
            .gridArea("3", "1", "4", "2")
            .toAttrs()
        ){ Text("Autres Technologies: ") }

        CircularProgressBar(
            Modifier.gridArea("3", "2", "4", "3"),
            gradientColorStart = "rgb(252,213,205)",
            gradientColorEnd = "rgb(244,76,44)",
            value = 75,
            textLabel = "Git")
        CircularProgressBar(
            Modifier.gridArea("3", "3", "4", "4"),
            gradientColorStart = "rgb(33,33,33)",
            gradientColorEnd = "rgb(239,168,8)",
            value = 70,
            textLabel = "Linux")
    }
}