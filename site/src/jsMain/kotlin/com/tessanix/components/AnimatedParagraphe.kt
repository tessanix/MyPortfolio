package com.tessanix.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.AnimationIterationCount
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.Width
import com.varabyte.kobweb.compose.dom.ref
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
fun AnimatedParagraphe(words: List<String>) {
    var currentWord by remember { mutableStateOf(0) }
    H3(
        Modifier
            .fontFamily("Arial Black")
            .color(rgb(113, 187, 215))
            .toAttrs()
    ) {

        Column {
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
  // lateinit val letterSize : Double

    Box(
//        ref = ref{
//            letterSize = window.getComputedStyle(it).fontSize.removeSuffix("px").toDouble()
//        },
        modifier = ContainerTextStyle.toModifier()
            .height(30.px)
            .thenIf(!writingCondition, Modifier.width(if (isWritten) 130.px else 0.px))
            .thenIf(
                writingCondition,
                Modifier
                    .animation(
                    TypingCursorAnimation.toAnimation(
                        duration = 4.s,
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
    from { Modifier.width(0.px) }
    to { Modifier.width(130.px) }
}

val ContainerTextStyle by ComponentStyle.base {
    Modifier
        .content("")
        .position(Position.Relative)
        .overflow(Overflow.Hidden)
}