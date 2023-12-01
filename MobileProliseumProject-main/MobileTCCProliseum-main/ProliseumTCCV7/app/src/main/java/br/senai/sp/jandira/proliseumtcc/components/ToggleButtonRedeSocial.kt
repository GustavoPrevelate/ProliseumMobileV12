package br.senai.sp.jandira.proliseumtcc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberImagePainter

enum class RedeSocial(val imageRes: Int, val id: Int) {
    DISCORD(br.senai.sp.jandira.proliseumtcc.R.drawable.discord_icon, 0),
    TWITTERX(br.senai.sp.jandira.proliseumtcc.R.drawable.twitterx_icon, 1),
    FACEBOOK(br.senai.sp.jandira.proliseumtcc.R.drawable.facebook_icon, 2),
    INSTAGRAM(br.senai.sp.jandira.proliseumtcc.R.drawable.instagram_icon, 3),
    YOUTUBE(br.senai.sp.jandira.proliseumtcc.R.drawable.youtube_icon, 4),
    TWITCH(br.senai.sp.jandira.proliseumtcc.R.drawable.twitch_icon, 5);

    fun toRepresentationStringRedeSocial(): String {
        return when (this) {
            DISCORD -> "0"
            TWITTERX -> "1"
            FACEBOOK -> "2"
            INSTAGRAM -> "3"
            YOUTUBE -> "4"
            TWITCH -> "5"
        }
    }
}

@Composable
fun ToggleButtonRedeSocial(onRedeSocialSelected: (RedeSocial?) -> Unit) {
    val selectedRedeSocialButton = remember { mutableStateOf<RedeSocial?>(null) }

    // Divida a lista de EloLol em sublistas de tamanho 3
    val RedesSociaisPorLinha = RedeSocial.values().toList().chunked(3)

    Column {
        // Para cada sublista (linha) de EloLol
        RedesSociaisPorLinha.forEach { linha ->
            Row {
                // Para cada EloLol na linha
                linha.forEach { elo ->
                    val isRedeSocialSelected = elo == selectedRedeSocialButton.value

                    val painterJogo = rememberImagePainter(data = elo.imageRes)

                    Card(
                        modifier = Modifier.size(90.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable {
                                    selectedRedeSocialButton.value = if (isRedeSocialSelected) null else elo
                                    onRedeSocialSelected(selectedRedeSocialButton.value)
                                }
                                .background(
                                    if (isRedeSocialSelected) RedProliseum else Color.White,
                                    shape = RoundedCornerShape(24.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterJogo,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(90.dp)
                                    .padding(10.dp)
                                    .background(
                                        if (isRedeSocialSelected) RedProliseum else Color.White,
                                        shape = RoundedCornerShape(20.dp)
                                    ),
                                alignment = Alignment.Center
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(20.dp)) // Espaço entre os cartões
                }
            }
            Spacer(modifier = Modifier.height(20.dp)) // Espaço entre as linhas
        }
    }
}