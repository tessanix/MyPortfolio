package com.tessanix

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.ScrollBehavior
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.breakpoint.BreakpointSizes
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.init.InitSilk
import com.varabyte.kobweb.silk.init.InitSilkContext
import com.varabyte.kobweb.silk.init.registerBaseStyle
import org.jetbrains.compose.web.css.*

@InitSilk
fun initSilk(ctx: InitSilkContext) {
    // Apply style to elements:
    ctx.stylesheet.apply {

        registerBaseStyle("html") {
            Modifier
                .scrollBehavior(ScrollBehavior.Smooth)
                .overflowY(Overflow.Scroll)
        }
        registerBaseStyle("::-webkit-scrollbar") {
            Modifier.width(12.px)
        }
        registerBaseStyle("::-webkit-scrollbar-thumb") {
            Modifier.backgroundColor(rgb(20, 20, 20)).borderRadius(100.vw)
        }
        registerBaseStyle("::-webkit-scrollbar-track") {
            Modifier.backgroundColor(rgb(80, 80, 80))//.borderRadius(100.vw)
        }
    }
    // Initialize breakpoints:
    ctx.theme.breakpoints = BreakpointSizes(
        sm = 650.px,
        md = 850.px,
        lg = 1100.px,
        xl = 2000.px
    )
}


var lang by mutableStateOf("french")
const val mainBackgroundColor = "rgb(19 34 80)"

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {

    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}
