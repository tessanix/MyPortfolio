package com.tessanix.pages

import androidx.compose.runtime.Composable
import com.tessanix.mainBackgroundColor
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


@Page
@Composable
fun KobwebSiteProjectPage() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().backgroundColor(Color(mainBackgroundColor))
    ) {

        Image(
            src = "tu-es-la.svg", modifier = Modifier.height(80.percent)
        )

        H1{
            Text("Tu te trouves déjà sur mon deuxième projet."); Br()
            Text("Ce site web est un portfolio que j'ai créé."); Br()
            A(
                href = "https://kobweb.varabyte.com/",
                attrs = Modifier
                    //.textDecorationLine(TextDecorationLine.None)
                    .toAttrs()
            ){ Text("Kobweb") }; Text(" est le framework Kotlin que j'ai utilisé pour ce projet web."); Br()
            Text("Grâce à lui j'ai pu coder ce site sans utiliser HTML, CSS, et JavaScript directement. Tout a été fait avec Kotlin!")

        }
        Row( verticalAlignment = Alignment.CenterVertically ) {
            P { Text("Retrouve le code ici:") }

            Link(
                path = "https://github.com/tessanix/MyPortfolio",
                modifier = Modifier
                    .textDecorationLine(TextDecorationLine.None)
                    .width(80.px)
                    .height(80.px)
            ){
                FaGithub(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .display(DisplayStyle.Flex)
                        .justifyContent(JustifyContent.Center)
                        .alignItems(AlignItems.Center)
                        .fontSize(2.em)
                        .color(Colors.White)

                )
            }
        }
    }
}