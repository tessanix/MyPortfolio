package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.thenIf
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.silk.components.animation.Keyframes
import com.varabyte.kobweb.silk.components.animation.toAnimation
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.base
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.components.text.SpanText
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.css.AnimationTimingFunction.Companion.steps
import org.jetbrains.compose.web.dom.H3


@Composable
fun AnimatedParagraphe(modifier: Modifier, words: List<String>) {
    var currentWord by remember { mutableStateOf(0) }
    //var largestChildWidth by remember { mutableStateOf(0) }

    H3(
        modifier
            .fontFamily("Arial Black")
            .color(rgb(113, 187, 215))
            .toAttrs()
    ) {

        Column(
//            ref = ref{ columnParentElement ->
//                val list = columnParentElement.children.asList()
//                largestChildWidth = list.maxOf { spanChildElement ->
//                    (spanChildElement.firstChild as HTMLElement).offsetWidth
//                }
//            },
            modifier = Modifier.width(125.px)//largestChildWidth.px)
        ) {
            words.forEachIndexed { index, word ->
                WritingLineAnimatedText(
                    text = word,
                    writingCondition = currentWord == index,
                    onWritingEnd = { currentWord = (currentWord + 1) % words.size }, // modulo operator is looping from 0 to 2
                    isWritten = currentWord >= index + 1,
                    delayAtEnd = if (index == 2) 3000L else 0L
                )
            }
        }
    }
}

@Composable
fun WritingLineAnimatedText(
    text: String,
    writingCondition: Boolean,
    onWritingEnd: () -> Unit,
    isWritten: Boolean,
    delayAtEnd: Long = 0L,
) {
    val maxChildWidth = 125 //by remember{ mutableStateOf(0)}
    Box(
//        ref = ref{
//            childWidth = (it.firstElementChild as HTMLElement).offsetWidth
//        },
        modifier = ContainerTextStyle.toModifier()
            .height(30.px)
            .width((maxChildWidth+2).px)
            .thenIf(!writingCondition, Modifier.width(if (isWritten) (maxChildWidth+2).px else 0.px))
            .thenIf(
                writingCondition,
                Modifier
                    .animation(
                    TypingCursorAnimation.toAnimation(
                        duration = 2.5.s,
                        timingFunction = steps(text.length+1),
                        iterationCount = AnimationIterationCount.of(1)
                    ),
                    CursorBlinkingAnimation.toAnimation(
                        duration = 0.4.s,
                        timingFunction = steps(1, StepPosition.End),
                        iterationCount = AnimationIterationCount.Infinite,
                        direction = AnimationDirection.Alternate,
                    )
                ).onAnimationEnd {
                    window.setTimeout({ onWritingEnd() }, delayAtEnd.toInt())
                }
            )
    ) {
        SpanText(text, Modifier.width(Width.MaxContext))
    }
}


val CursorBlinkingAnimation by Keyframes {
    val visibleCursorModifier = Modifier.borderRight(2.px, LineStyle.Solid, Colors.White)
    0.percent { visibleCursorModifier }
    50.percent { Modifier.borderRight(color = Colors.Transparent) }
    100.percent { visibleCursorModifier }
}

val TypingCursorAnimation by Keyframes {
    from { Modifier.width(0.percent) }
    to { Modifier.width(100.percent) }
}

val ContainerTextStyle by ComponentStyle.base {
    Modifier
        .content("")
        .position(Position.Relative)
        .overflow(Overflow.Hidden)
}