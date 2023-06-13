package com.tessanix.pages

import androidx.compose.runtime.Composable
import com.tessanix.components.ProjectPageBar
import com.tessanix.lang
import com.tessanix.mainBackgroundColor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import com.varabyte.kobweb.silk.components.graphics.Image
import com.varabyte.kobweb.silk.components.icons.fa.FaGithub
import com.varabyte.kobweb.silk.components.navigation.Link
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*


@Page
@Composable
fun KobwebSiteProjectPage() {
    val ctx = rememberPageContext()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth().fillMaxHeight().backgroundColor(Color(mainBackgroundColor))
    ) {
        ProjectPageBar(ctx, if(lang=="french") "Site web portfolio" else "Portfolio website")

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                src = "completeWebsiteIcon.svg",
                modifier = Modifier
                    .maxHeight(50.percent)
                    .maxWidth(100.percent)
                    .minHeight(80.percent)
                    .margin(1.em)
            )
        }

        H1(
            attrs = Modifier
                .backgroundColor(rgba(255,255,255,0.2))
                .margin(1.em)
                .padding(1.em)
                .borderRadius(35.px)
                .fontSize(1.5.em)
                .fontFamily("Arial")
                .toAttrs()
        ){
            if(lang == "french") {
                Text("Tu te trouves déjà sur mon deuxième projet."); Br()
                Text("Ce site web est un portfolio que j'ai créé."); Br()
                A(href = "https://kobweb.varabyte.com/",) { Text("Kobweb") }
                Text(" est le framework Kotlin que j'ai utilisé pour ce projet web."); Br()
                Text("Grâce à lui j'ai pu coder ce site sans utiliser HTML, CSS, et JavaScript directement."); Br()
                Text("Tout a été fait avec Kotlin!")
            }else{
                Text("You are already on my second project."); Br()
                Text("This website is a portfolio that I created."); Br()
                A(href = "https://kobweb.varabyte.com/",){ Text("Kobweb") }
                Text(" is the Kotlin framework I used for this web project."); Br()
                Text("Thanks to it, I was able to code this site without directly using HTML, CSS, and JavaScript."); Br()
                Text("Everything was done with Kotlin!")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            P(
                Modifier
                    .color(Colors.White)
                    .fontFamily("Arial")
                    .fontSize(16.px)
                    .fontWeight(FontWeight.Bold)
                    .toAttrs()
            ) { Text("Retrouve le code ici:") }

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