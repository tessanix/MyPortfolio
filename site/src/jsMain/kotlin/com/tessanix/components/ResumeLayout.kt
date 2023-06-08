package com.tessanix.components

import androidx.compose.runtime.*
import com.tessanix.lang
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaMagnifyingGlass
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.before
import com.varabyte.kobweb.silk.components.style.hover
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.A
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Text

@Composable
fun ResumeLayout( compactView: Boolean ) {
    var isMouseOverResume by remember { mutableStateOf(false) }
    var resumeClicked by remember { mutableStateOf(false) }

    @Composable
    fun LayoutContent(){
        H3(
            Modifier
                .margin(2.em)
                .fontSize(1.5.em)
                .fontFamily("Arial Black")
                .toAttrs()
        ) { Text(if (lang == "french") "Mon CV:" else "My Resume:") }

        Box(Modifier.position(Position.Relative)){

            FaMagnifyingGlass(
                modifier = MagnifyingGlassIconStyle.toModifier()
                    .onMouseEnter { isMouseOverResume = true }
                    .onMouseLeave { isMouseOverResume = false }
                    .onClick { resumeClicked = true }
                    .thenIf(isMouseOverResume, MagnifyingGlassIconGrow.toModifier()),
                size = IconSize.XXL
            )
            Image(
                src = "CV_TESSAN_FR-1.png",
                modifier = Modifier
                    .height(400.px)
                    .width(290.px)
                    .border(3.px, LineStyle.Solid, Colors.Gray)
                    .borderRadius(10.px)
            )
        }
        A(
            href = "CV_TESSAN_FR.pdf",
            attrs = Modifier
                .cursor(Cursor.Pointer)
                .textDecorationLine(TextDecorationLine.None)
                .border(2.px, LineStyle.Solid, Colors.White)
                .fontFamily("Arial Black")
                .borderColor(Colors.White)
                .borderRadius(35.px)
                .margin(topBottom = 20.px)
                .padding(10.px)
                .color(Colors.White)
                .backgroundColor(Color("rgba(250,250,250,0.3)"))
                .toAttrs{ attr("download", "") }
        ) { Text(if (lang == "french") "Télécharger" else "Download") }
    }

    if(compactView) LayoutContent()
    else  Column(horizontalAlignment = Alignment.CenterHorizontally) { LayoutContent() }

    if(resumeClicked) PreviewBox(isCompactView = compactView, closePreviewFunc = { resumeClicked=false })
}

@Composable
fun PreviewBox(isCompactView: Boolean, closePreviewFunc: ()->Unit){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .zIndex(5)
            .position(Position.Fixed)
            .top(50.percent).left(50.percent)
            .translate((-50).percent, (-50).percent)
            .height(100.percent)
            .width(100.percent)
    ){
        Image(
            src = "CV_TESSAN_FR-1.png",
            modifier = Modifier
                .thenIf(!isCompactView, Modifier.height(80.percent))
                .thenIf(isCompactView, Modifier.width(100.percent))
                .border(3.px, LineStyle.Solid, Colors.Gray)
                .borderRadius(10.px)
                .cursor(Cursor.Pointer)
                .onClick { closePreviewFunc() }
        )
    }
}


val MagnifyingGlassIconStyle by ComponentStyle {
    base{
        Modifier
            .color(rgb(130,130,130))
            .top(50.percent).left(50.percent)
            .translate((-50).percent, (-50).percent)
            .width(100.percent)
            .height(100.percent)
            .position(Position.Absolute)
            .display(DisplayStyle.Flex)
            .alignItems(AlignItems.Center)
            .justifyContent(JustifyContent.Center)
            .cursor(Cursor.Pointer)
            .transition(CSSTransition("all", 1.s, AnimationTimingFunction.Ease))
    }
    hover{ Modifier.backgroundColor(rgba(0,0,0, 0.4)) }
    before { Modifier.transition(CSSTransition("all", 0.5.s, AnimationTimingFunction.Ease)) }
}


val MagnifyingGlassIconGrow by ComponentStyle {
    before { Modifier.scale(1.3).color(rgb(250,250,250)) }
}
