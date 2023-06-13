package com.tessanix.pages

import androidx.compose.runtime.Composable
import com.tessanix.components.ProjectPageBar
import com.tessanix.lang
import com.tessanix.mainBackgroundColor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
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
import com.varabyte.kobweb.silk.components.style.breakpoint.Breakpoint
import com.varabyte.kobweb.silk.components.text.SpanText
import com.varabyte.kobweb.silk.theme.breakpoint.rememberBreakpoint
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement

@Page
@Composable
fun KaraokeAppProjectPage() {
    val bp = rememberBreakpoint()
    val ctx = rememberPageContext()

    val titleList = if(lang=="french")
            listOf("Le produit: ", "Outils utilisés: ", "Disponibilité: ", "Partage du code: ")
    else
        listOf("The product: ", "Used tools: ", "Availability: ", "Code sharing: ")

    val descListServer = if(lang=="french") listOf(
        "Serveur dédié à l'administrateur déployé sur le web.",
        "Le langage Kotlin et le framework Ktor.",
        "via un url avec des identifiants.",
        "Code source disponible sur github."
    )
    else listOf(
        "Server dedicated to the administrator deployed on the web.",
        "The Kotlin language and the Ktor framework.",
        "via a URL with credentials.",
        "Source code available on GitHub.",
    )

    val descListClient = if(lang=="french") listOf(
        "Application android native",
        "Le langage Kotlin et le framework Jetpack Compose",
        "Sur le play Store, uniquement dans la zone géographique Guadeloupe",
        "Code source disponible sur github"
    )
    else listOf(
        "Native Android application.",
        "The Kotlin language and the Jetpack Compose framework.",
        "On the Play Store, available only in the geographical zone of Guadeloupe.",
        "Source code available on GitHub."
    )

    @Composable
    fun ServerAndClientCards(modifier: Modifier){
        PresentationCard(
            modifier = modifier,
            srcImage1 = "serverIcon.svg",
            srcImage2 =  "KtorLogoIcon.svg",
            cardTitle = if(lang=="french") "Côté serveur:" else "Server side:",
            titleList = titleList,
            descList = descListServer,
            gitHubLink = "https://github.com/tessanix/KaraokeAppBackend"
        )
        PresentationCard(
            modifier = modifier,
            srcImage1 = "smartphoneIcon.svg",
            srcImage2 =  "androidNoCircleIcon.svg",
            cardTitle = if(lang=="french") "Côté client:" else "Client side:",
            titleList = titleList,
            descList = descListClient,
            gitHubLink = "https://github.com/tessanix/KaraokeApp"
        )
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .backgroundColor(Color(mainBackgroundColor))
    ) {

        ProjectPageBar(ctx, if(lang=="french") "Application Karaoke" else "Karaoke App")

        Column(
            Modifier
                .margin(10.px, 30.px)
                .color(Colors.White)
                .fontFamily("Arial")
        ) {
            if(lang=="french") {
                H1 { Text("Contexte:"); Br() }
                H3 {
                    Text("Afin d'aider un animateur Karaoké à moderniser son buiseness j'ai créer "); Br()
                    Text("un serveur web et une application mobile Android."); Br()
                    Text("L'animateur peut accéder au serveur via une page web de connexion."); Br()
                    Text("Le serveur web contient une base de données avec les titres des musiques que l'animateur propose mais aussi la liste des demandes faites par les clients"); Br()
                    Text("Il peut ensuite modifier et ajouter des titres de musique et supprimer les demandes déjà traités"); Br()
                    Text("L'application mobile est principalement dédiée aux clients venue pour l'animation Karaoké."); Br()
                    Text("Avec elle, les clients peuvent avoir les coordonées de contact de l'animateur, ils peuvent choisir un titre dans la base de données ");Br()
                    Text("et faire une demande qui sera envoyé au serveur dans la liste des demandes.")
                }
            }else {
                H1 { Text("Context:"); Br() }
                H3 {
                    Text("In order to help a Karaoke host modernize their business, I created "); Br()
                    Text("a web server and an Android mobile application."); Br()
                    Text("The host can access the server through a login web page."); Br()
                    Text("The web server contains a database with the titles of the songs offered by the host, as well as the list of requests made by the clients."); Br()
                    Text("He can then modify and add music titles, and delete the requests that have already been processed."); Br()
                    Text("The mobile application is primarily dedicated to the clients who come for the Karaoke entertainment."); Br()
                    Text("With it, clients can have the contact information of the host, they can choose a title from the database ");Br()
                    Text("and make a request that will be sent to the server in the list of requests.")
                }
            }
        }

        if(bp < Breakpoint.MD)
            ServerAndClientCards(Modifier.margin(20.px))
        else {
            Row(horizontalArrangement = Arrangement.SpaceAround) {
                ServerAndClientCards(
                    Modifier
                        .margin(leftRight = 20.px)
                        .fillMaxHeight()
                        .width(50.percent)
                )
            }
        }
        KaraokeVideo(Modifier.fillMaxWidth().margin(20.px))
    }
}




@Composable
fun PresentationCard(
    modifier: Modifier,
    srcImage1: String,
    srcImage2: String,
    cardTitle: String,
    titleList: List<String>,
    descList: List<String>,
    gitHubLink: String? = null
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fontSize(1.5.em)
            .padding(1.em)
            .borderRadius(35.px)
            .backgroundColor(rgba(255,255,255,0.2))
            .fontFamily("Arial")
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(src = srcImage1, modifier = Modifier.width(30.percent))
            Image(src = srcImage2, modifier = Modifier.width(30.percent))
        }
        H3 { Text(cardTitle) }
        Ul {
            for ((title, desc) in titleList.zip(descList)) {
                Li {
                    SpanText(
                        text = title,
                        modifier = Modifier.fontWeight(FontWeight.Bold)
                    ); Text(desc)
                }
            }
        }

        if(gitHubLink!=null){
            Link(
                path = gitHubLink,
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


@Composable
fun KaraokeVideo(modifier: Modifier){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Video(
            Modifier
                .width(100.percent).height(100.percent)
                .objectFit(ObjectFit.Fill)
                .onMouseEnter { (it.target as HTMLElement).setAttribute("controls", "") }
                .onMouseLeave { (it.target as HTMLElement).removeAttribute("controls") }
                .toAttrs()
        ) {
            Source(attrs = {
                attr("src", "karaokeAppPresentation.mp4")
                attr("type", "video/mp4")
            })
        }
    }
}
