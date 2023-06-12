package com.tessanix.pages

import androidx.compose.runtime.Composable
import com.tessanix.components.ProjectPageBar
import com.tessanix.lang
import com.tessanix.mainBackgroundColor
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.core.rememberPageContext
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.HTMLElement


@Page
@Composable
fun QtVpnProject() {
    val ctx = rememberPageContext()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.backgroundColor(Color(mainBackgroundColor)).fillMaxWidth()
    ) {

        ProjectPageBar(ctx,
            if(lang=="french") "Interface graphique d'application bureau"
            else "Graphical user interface of a desktop application"
        )

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
                Text("Ici, j'ai essayé de refaire l'interface graphique d'une application bureau déjà existante: ")
                A(href = "https://openvpn.net/client/client-connect-vpn-for-windows/",) { Text("OpenVpn") }; Br()
                Text("J'ai utilisé le langage C++ et le framework Qt pour la réaliser."); Br()
                Text("Mon but était simplement de reproduire cette interface le plus fidèlement possible,"); Br()
                Text("puis d'y ajouter ma touche personnel pour les couleurs et les icones."); Br()
                Text("Ci-dessous vous pouvez voir la comparaison des interfaces via une video:"); Br()
                Text("A droite l'application originale et à gauche mon application.")
            }else {
                Text("Here, I tried to recreate the graphical interface of an existing desktop application: ")
                A(href = "https://openvpn.net/client/client-connect-vpn-for-windows/",){ Text("OpenVpn") }; Br()
                Text("I used the C++ language and the Qt framework to achieve it."); Br()
                Text("My goal was simply to reproduce this interface as faithfully as possible,"); Br()
                Text("and then add my personal touch for the colors and icons."); Br()
                Text("Below, you can see the interface comparison through a video:"); Br()
                Text("On the right, the original application, and on the left, my application.")
            }
        }
        Column(
            modifier = Modifier.margin(1.em),
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
                    attr("src", "OpenVpnQt.mp4")
                    attr("type", "video/mp4")
                })
            }
        }
    }
}