package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.FontSize
import com.varabyte.kobweb.compose.css.JustifyContent
import com.varabyte.kobweb.compose.css.color
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Nav
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul

@Composable
fun CustomLi(text:String) = Li(
    Modifier
        .margin(leftRight = 20.px)
        .fontFamily("Arial")
        .styleModifier { color("#ffffff") }
        .fontSize(FontSize.XLarge)
        .toAttrs()
){ Text(text) }

@Composable
fun NavBar(){
    Nav {
        Ul(
            Modifier
            .listStyle("none")
            .display(DisplayStyle.Flex)
            .justifyContent(JustifyContent.SpaceAround)
            .margin(topBottom = 24.px)
            .toAttrs()
        ) {
            CustomLi("Haut de page")
            CustomLi("Mes compétences")
            CustomLi("Mes travaux")
            CustomLi("Me laisser un message")
        }
    }
}