package br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaJogadores
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaTimes
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay

@Composable
fun CarregarFiltragemListaJogadoresScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedFiltragemListaJogadores: SharedFiltragemListaJogadores,

    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 0.5 segundos antes de continuar
        delay(500)
        loading = false
    }

    var filtragemNomeJogadorState by remember { mutableStateOf(sharedFiltragemListaJogadores.name) }

    LaunchedEffect(sharedFiltragemListaJogadores) {

        // Esta parte só será executada quando o composable for inicializado
        filtragemNomeJogadorState = sharedFiltragemListaJogadores.name

        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    // DESIGN DA TELA
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum, AzulEscuroProliseum
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )

            } else {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )

                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    onNavigate("lista_de_jogadores")

                } else{
                    Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(),
                        color = RedProliseum
                    )
                }
            }
        }
    }
}