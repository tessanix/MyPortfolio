package com.tessanix.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier

val programmingLanguages: List<@Composable (m:Modifier, w:Int, h:Int)->Unit> = listOf(
    { m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(61,122,171)",
            gradientColorEnd = "rgb(252,234,116)",
            value=85,
            textLabel="Python")
    },
    { m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(49,114,174)",
            gradientColorEnd = "rgb(187,209,230)",
            value = 65,
            textLabel = "C++"
        )
    },
    { m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(183,38,222)",
            gradientColorEnd = "rgb(222,62,113)",
            value=70,
            textLabel="Kotlin")
    }
)

val positions: List<@Composable (m:Modifier, w:Int, h:Int)->Unit> = listOf(
    {m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(255, 87, 51)",
            gradientColorEnd = "rgb(20, 215, 192)",
            value = 55,
            textLabel = "Mobile/Web FrontEnd")
    },
    {m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(5,5,5)",
            gradientColorEnd = "rgb(57,255,20)",
            value = 65,
            textLabel = "BackEnd")
    }
)

val otherTechs: List<@Composable (m:Modifier, w:Int, h:Int)->Unit> = listOf(
    { m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(252,213,205)",
            gradientColorEnd = "rgb(244,76,44)",
            value = 75,
            textLabel = "Git")

    },
    { m, w, h ->
        CircularProgressBar(
            modifier = m,
            width = w, height = h,
            gradientColorStart = "rgb(33,33,33)",
            gradientColorEnd = "rgb(239,168,8)",
            value = 70,
            textLabel = "Linux")
    }
)