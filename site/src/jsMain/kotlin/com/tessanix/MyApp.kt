package com.tessanix

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.modifiers.minHeight
import com.varabyte.kobweb.core.App
import com.varabyte.kobweb.silk.SilkApp
import com.varabyte.kobweb.silk.components.layout.Surface
import com.varabyte.kobweb.silk.components.style.common.SmoothColorStyle
import com.varabyte.kobweb.silk.components.style.toModifier
import org.jetbrains.compose.web.css.vh

//@InitSilk
//fun updateTheme(ctx: InitSilkContext) {
//    // Configure silk here
//}


//@InitSilk
//fun initializeBreakpoints(ctx: InitSilkContext) {
//    ctx.theme.breakpoints = BreakpointSizes(
//        sm = 30.cssRem,
//        md = 48.cssRem,
//        lg = 62.cssRem,
//        xl = 80.cssRem
//    )
//}

var lang by mutableStateOf("french")

@App
@Composable
fun MyApp(content: @Composable () -> Unit) {

    SilkApp {
        Surface(SmoothColorStyle.toModifier().minHeight(100.vh)) {
            content()
        }
    }
}
