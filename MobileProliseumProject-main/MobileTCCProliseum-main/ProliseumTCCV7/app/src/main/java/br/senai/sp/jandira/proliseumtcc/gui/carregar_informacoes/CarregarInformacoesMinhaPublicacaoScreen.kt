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
import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.model.getTimeById
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemPostProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresDentroDeTimeList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresInfoPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasList
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetListaJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesMinhaPublicacaoScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    //SharedViewModel GET MINHA POSTAGEM
    sharedGetMinhaPostagem: SharedGetMinhaPostagem,
    sharedGetMinhaPostagemUser: SharedGetMinhaPostagemUser,
    sharedGetMinhaPostagemUserPropostas: SharedGetMinhaPostagemUserPropostas,
    sharedGetMinhaPostagemPostProfile: SharedGetMinhaPostagemPostProfile,

    onNavigate: (String) -> Unit
) {

    // CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    // DESIGN TELA
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
        // Imagem Capa
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                // Exibe um indicador de progresso enquanto carrega
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxSize(),
                    color = RedProliseum
                )

                // Se o tempo de espera terminou, continue com a validação do token
                // Restante do código aqui
                val token = sharedViewModelTokenEId.token
                // Restante do seu código de validação do token
                Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

                if(token != null && token.isNotEmpty()){

                    val getMinhaPostagemService = RetrofitFactoryCadastro().getMinhaPostagemService()

                    getMinhaPostagemService.getMyPost("Bearer " + token).enqueue(object : Callback<GetMinhaPostagem> {
                        override fun onResponse(call: Call<GetMinhaPostagem>, response: Response<GetMinhaPostagem>) {
                            if (response.isSuccessful) {

                                Log.d("GET TIME BY ID CERTO", "Resposta bem-sucedida: ${response.code()}")
                                val minhaPostagemResponse = response.body()

                                val userMinhaPostagem = minhaPostagemResponse?.user
                                val postProfileMinhaPostagem = minhaPostagemResponse?.postProfile

                                sharedGetMinhaPostagem.user =  minhaPostagemResponse?.user
                                sharedGetMinhaPostagem.postProfile = minhaPostagemResponse?.postProfile

                                if(userMinhaPostagem != null){
                                    val idMinhaPostagem = userMinhaPostagem.id
                                    val nomeUsuarioMinhaPostagem  = userMinhaPostagem.nome_usuario
                                    val nomeCompletoMinhaPostagem  = userMinhaPostagem.nome_completo
                                    val emailMinhaPostagem  = userMinhaPostagem.email
                                    val dataNascimentoMinhaPostagem  = userMinhaPostagem.data_nascimento
                                    val generoMinhaPostagem  = userMinhaPostagem.genero
                                    val nicknameMinhaPostagem  = userMinhaPostagem.nickname
                                    val biografiaMinhaPostagem  = userMinhaPostagem.biografia

                                    sharedGetMinhaPostagemUser.id = userMinhaPostagem.id
                                    sharedGetMinhaPostagemUser.nome_usuario = userMinhaPostagem.nome_usuario
                                    sharedGetMinhaPostagemUser.nome_completo = userMinhaPostagem.nome_completo
                                    sharedGetMinhaPostagemUser.email = userMinhaPostagem.email
                                    sharedGetMinhaPostagemUser.data_nascimento = userMinhaPostagem.data_nascimento
                                    sharedGetMinhaPostagemUser.genero = userMinhaPostagem.genero
                                    sharedGetMinhaPostagemUser.nickname = userMinhaPostagem.nickname
                                    sharedGetMinhaPostagemUser.biografia = userMinhaPostagem.biografia





                                    val propostasMinhaPostagem  = userMinhaPostagem.propostas

                                    sharedGetMinhaPostagemUser.propostas = userMinhaPostagem.propostas

                                    if(propostasMinhaPostagem != null){
                                        for(propostas in propostasMinhaPostagem){
                                            val idPropostas = propostas.id
                                            val menssagemPropostas = propostas.menssagem

                                            sharedGetMinhaPostagemUserPropostas.id = propostas.id
                                            sharedGetMinhaPostagemUserPropostas.menssagem = propostas.menssagem
                                        }
                                    }

                                }

                                if(postProfileMinhaPostagem != null){
                                    val idPostProfile = postProfileMinhaPostagem.id
                                    val descricaoPostProfile = postProfileMinhaPostagem.descricao
                                    val jogoPostProfile = postProfileMinhaPostagem.jogo
                                    val funcaoPostProfile = postProfileMinhaPostagem.funcao
                                    val eloPostProfile = postProfileMinhaPostagem.elo
                                    val horaPostProfile = postProfileMinhaPostagem.hora
                                    val tipoPostProfile = postProfileMinhaPostagem.tipo
                                    val prosPostProfile = postProfileMinhaPostagem.pros

                                    sharedGetMinhaPostagemPostProfile.id = postProfileMinhaPostagem.id
                                    sharedGetMinhaPostagemPostProfile.descricao = postProfileMinhaPostagem.descricao
                                    sharedGetMinhaPostagemPostProfile.jogo = postProfileMinhaPostagem.jogo
                                    sharedGetMinhaPostagemPostProfile.funcao = postProfileMinhaPostagem.funcao
                                    sharedGetMinhaPostagemPostProfile.elo = postProfileMinhaPostagem.elo
                                    sharedGetMinhaPostagemPostProfile.hora = postProfileMinhaPostagem.hora
                                    sharedGetMinhaPostagemPostProfile.tipo = postProfileMinhaPostagem.tipo
                                    sharedGetMinhaPostagemPostProfile.pros = postProfileMinhaPostagem.pros
                                }

                                if(postProfileMinhaPostagem == null){
                                    onNavigate("lista_de_publicacoes_jogadores")
                                }else{
                                    onNavigate("minha_postagem")
                                }



                            } else {
                                // Trate a resposta não bem-sucedida
                                Log.d("PerfilUsuarioJogadorScreen", "Resposta não bem-sucedida: ${response.code()}")
                                // Log de corpo da resposta (se necessário)
                                Log.d(
                                    "PerfilUsuarioJogadorScreen",
                                    "Corpo da resposta: ${response.errorBody()?.string()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<GetMinhaPostagem>, t: Throwable) {
                            // Trate o erro de falha na rede.
                            Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
                        }

                    })


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