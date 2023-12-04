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

enum class EloLol(val imageRes: Int, val id: Int) {
    UNRANKED(br.senai.sp.jandira.proliseumtcc.R.drawable.unranked_proliseum_elo, 0),
    IRON(br.senai.sp.jandira.proliseumtcc.R.drawable.iron_proliseum_elo, 1),
    BRONZE(br.senai.sp.jandira.proliseumtcc.R.drawable.bronze_proliseum_elo, 2),
    SILVER(br.senai.sp.jandira.proliseumtcc.R.drawable.silver_proliseum_elo, 3),
    GOLD(br.senai.sp.jandira.proliseumtcc.R.drawable.gold_proliseum_elo, 4),
    PLATINUM(br.senai.sp.jandira.proliseumtcc.R.drawable.platinum_proliseum_elo, 5),
    ESMERALD(br.senai.sp.jandira.proliseumtcc.R.drawable.emerald_proliseum_elo, 6),
    DIAMOND(br.senai.sp.jandira.proliseumtcc.R.drawable.diamond_proliseum_elo, 7),
    MASTER(br.senai.sp.jandira.proliseumtcc.R.drawable.master_proliseum_elo, 8),
    GRANDMASTER(br.senai.sp.jandira.proliseumtcc.R.drawable.grandmaster_proliseum_elo, 9),
    CHALLENGER(br.senai.sp.jandira.proliseumtcc.R.drawable.challenger_proliseum_elo, 10);

    fun toRepresentationStringEloLol(): String {
        return when (this) {
            UNRANKED -> "0"
            IRON -> "1"
            BRONZE -> "2"
            SILVER -> "3"
            GOLD -> "4"
            PLATINUM -> "5"
            ESMERALD -> "6"
            DIAMOND -> "7"
            MASTER -> "8"
            GRANDMASTER -> "9"
            CHALLENGER -> "10"
        }
    }
}

@Composable
fun ToggleButtonEloLol(onJogoSelected: (EloLol?) -> Unit) {
    val selectedJogoButton = remember { mutableStateOf<EloLol?>(null) }

    // Divida a lista de EloLol em sublistas de tamanho 3
    val elosPorLinha = EloLol.values().toList().chunked(3)

    Column {
        // Para cada sublista (linha) de EloLol
        elosPorLinha.forEach { linha ->
            Row {
                // Para cada EloLol na linha
                linha.forEach { elo ->
                    val isEloLolSelected = elo == selectedJogoButton.value

                    val painterJogo = rememberImagePainter(data = elo.imageRes)

                    Card(
                        modifier = Modifier.size(90.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .clickable {
                                    selectedJogoButton.value = if (isEloLolSelected) null else elo
                                    onJogoSelected(selectedJogoButton.value)
                                }
                                .background(
                                    if (isEloLolSelected) RedProliseum else Color.White,
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
                                        if (isEloLolSelected) RedProliseum else Color.White,
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