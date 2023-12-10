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
import br.senai.sp.jandira.proliseumtcc.components.Genero
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarTelaEditarPublicacaoTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedGetTimeListaPostagens: SharedGetTimeListaPostagens,
    sharedGetTimeListaPostagensPublicacao: SharedGetTimeListaPostagensPublicacao,
    sharedGetTimeListaPostagensPublicacaoDonoId: SharedGetTimeListaPostagensPublicacaoDonoId,
    sharedGetTimeListaPostagensPublicacaoTime: SharedGetTimeListaPostagensPublicacaoTime,
    sharedGetTimeListaPostagensPublicacaoTimeJogadores: SharedGetTimeListaPostagensPublicacaoTimeJogadores,
    sharedGetTimeListaPostagensPublicacaoTimePropostas: SharedGetTimeListaPostagensPublicacaoTimePropostas,
    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 0.5 segundos antes de continuar
        delay(1000)
        loading = false
    }

    var idTimeEscolhido by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacaoTime.id) }

    var idMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.id) }
    var descricaoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.descricao) }
    var jogoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.jogo) }
    var funcaoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.funcao) }
    var eloMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.elo) }
    var horaMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.hora) }
    var tipoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.tipo) }
    var prosMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.pros) }

    var selectedGeneroUser by remember { mutableStateOf<Genero?>(null) }


    LaunchedEffect(sharedGetTimeListaPostagensPublicacao, sharedGetTimeListaPostagensPublicacaoTime) {

        // Esta parte só será executada quando o composable for inicializado
        idMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.id
        descricaoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.descricao
        jogoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.jogo
        funcaoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.funcao
        eloMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.elo
        horaMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.hora
        tipoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.tipo
        prosMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.pros

        idTimeEscolhido = sharedGetTimeListaPostagensPublicacaoTime.id
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    Log.e("id do time", "ID DO TIME ESCOLHIDO ${idTimeEscolhido}")
    Log.e("ID DA PUBLICACAO DA PUBLICACAO ", "ID DA PUBLICACAO ${idMinhaPublicacaoState}")
    Log.e("DESCRICAO DA PUBLICACAO ", "DESCRICAO DA PUBLICACAO ${descricaoMinhaPublicacaoState}")
    Log.e("JOGO DA PUBLICACAO", "JOGO DA PUBLICACAO ${jogoMinhaPublicacaoState}")
    Log.e("FUNCAO DA PUBLICACAO", "FUNCAO DA PUBLICACAO ${funcaoMinhaPublicacaoState}")
    Log.e("ELO DA PUBLICACAO", "ELO DA PUBLICACAO ${eloMinhaPublicacaoState}")
    Log.e("HORA DA PUBLICACAO", "HORA DA PUBLICACAO ${horaMinhaPublicacaoState}")
    Log.e("TIPO DA PUBLICACAO", "TIPODA PUBLICACAO ${tipoMinhaPublicacaoState}")
    Log.e("PROS DA PUBLICACAO", "PROS DA PUBLICACAO ${prosMinhaPublicacaoState}")

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

            } else {

                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    sharedGetTimeListaPostagensPublicacao.id = idMinhaPublicacaoState
                    sharedGetTimeListaPostagensPublicacao.descricao = descricaoMinhaPublicacaoState
                    sharedGetTimeListaPostagensPublicacao.jogo = jogoMinhaPublicacaoState
                    sharedGetTimeListaPostagensPublicacao.funcao = funcaoMinhaPublicacaoState
                    sharedGetTimeListaPostagensPublicacao.elo = eloMinhaPublicacaoState
                    sharedGetTimeListaPostagensPublicacao.tipo = tipoMinhaPublicacaoState
                    sharedGetTimeListaPostagensPublicacao.pros = prosMinhaPublicacaoState

                    sharedGetTimeListaPostagensPublicacaoTime.id = idTimeEscolhido


                    onNavigate("editar_minha_publicacao_time")

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