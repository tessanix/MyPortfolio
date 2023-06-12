package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.PageContext
import com.varabyte.kobweb.silk.components.icons.fa.FaArrowLeft
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.Text

@Composable
fun ProjectPageBar(ctx: PageContext, projectTitle: String ) {
    Row(
        Modifier
            .fillMaxWidth()
            .position(Position.Relative),
        horizontalArrangement = Arrangement.Center
    ) {
        FaArrowLeft(
            Modifier
                .position(Position.Absolute)
                .top(0.px)
                .left(0.px)
                .cursor(Cursor.Pointer)
                .display(DisplayStyle.Flex)
                .justifyContent(JustifyContent.Center)
                .alignItems(AlignItems.Center)
                .fontSize(2.em)
                .width(80.px)
                .height(80.px)
                .color(Colors.White)
                .onClick { ctx.router.navigateTo("/") },
            size = IconSize.LG
        )
        H1(
            Modifier
                .textAlign(TextAlign.Center)
                .color(Colors.White)
                .margin(20.px, 80.px)
                .fontFamily("Arial")
                .toAttrs()
        ) { Text(projectTitle) }
    }
}