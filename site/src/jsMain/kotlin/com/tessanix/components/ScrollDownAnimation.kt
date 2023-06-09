package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.CSSAnimation
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.silk.components.animation.Keyframes
import com.varabyte.kobweb.silk.components.style.*
import org.jetbrains.compose.web.ExperimentalComposeWebApi
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div


@Composable
fun ScrollDownAnimation(modifier: Modifier, isAppearing: Boolean){
    Box(
        modifier
        .position(Position.Relative)
        .width(36.px)
        .height(36.px)
        .opacity(0)
        .transition(CSSTransition("opacity", 3.s))
        .thenIf(isAppearing, Modifier.opacity(1))
    ) {
        Div(ScrollDownAnimationStyle.toAttrs())
        Div(ScrollDownAnimationStyle.toAttrs())
        Div(ScrollDownAnimationStyle.toAttrs())
    }
}


val moveDowArrow by Keyframes {
    25.percent { Modifier.opacity(1) }
    33.percent {
        Modifier.opacity(1).translateY(30.px);
    }
    67.percent {
        Modifier.opacity(1).translateY(40.px)
    }
    100.percent {
        Modifier
            .opacity(0)
            .translateY(55.px)
            .scale(0.5, 0.5, 0.5)
    }
}

@OptIn(ExperimentalComposeWebApi::class)
val ScrollDownAnimationStyle by ComponentStyle{
    base{
        Modifier
            .position(Position.Absolute)
            .width(40.px)
            .height(12.px)
            .opacity(0)
            .scale(0.5,0.5,0.5)
            .animation(
                CSSAnimation(
                    moveDowArrow.name,
                    3.s,
                    AnimationTimingFunction.EaseInOut,
                    iterationCount = AnimationIterationCount.Infinite
                )
            )
    }
    firstChild{
        Modifier.animation(
            CSSAnimation(
                moveDowArrow.name,
                3.s,
                AnimationTimingFunction.EaseInOut,
                1.s,
                AnimationIterationCount.Infinite
            )
        )
    }
    cssRule(":nth-child(2)"){
        Modifier.animation(
            CSSAnimation(
                moveDowArrow.name,
                3.s,
                AnimationTimingFunction.EaseInOut,
                2.s,
                AnimationIterationCount.Infinite
            )
        )
    }
    before{
        Modifier
            .content("")
            .position(Position.Absolute)
            .top(0.px)
            .height(100.percent)
            .width(51.percent)
            .backgroundColor(Colors.White)
            .left(0.px)
            .transform{ skew(0.deg, 30.deg) }
    }
    after{
        Modifier
            .content("")
            .position(Position.Absolute)
            .top(0.px)
            .height(100.percent)
            .width(50.percent)
            .backgroundColor(Colors.White)
            .right(0.px)
            .transform{ skew(0.deg, (-30).deg) }

    }
}