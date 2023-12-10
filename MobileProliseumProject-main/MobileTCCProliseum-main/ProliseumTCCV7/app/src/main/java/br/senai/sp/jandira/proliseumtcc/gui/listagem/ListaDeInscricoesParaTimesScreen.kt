package br.senai.sp.jandira.proliseumtcc.gui.listagem

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.gui.postagem.deletarPublicacaoJogador
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.GetPeneira
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.PeneiraResponse
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidasGeral
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacaoDonoId
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneira
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeralDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeralDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeralDePropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListaDeInscricoesParaTimesScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedViewModelPerfil: SharedViewModelPerfil,
    sharedViewModelUser: SharedViewModelUser,
    sharedViewModelPerfilPropostas: SharedViewModelPerfilPropostas,
    sharedViewModelPerfilPropostasDe: SharedViewModelPerfilPropostasDe,
    sharedViewModelPerfilPropostasDeJogadores: SharedViewModelPerfilPropostasDeJogadores,
    sharedViewModelPerfilPropostasDePropostas: SharedViewModelPerfilPropostasDePropostas,


    // SharedViewModelGetMyTeams de USUARIO
    sharedViewModelGetMyTeamsUser: SharedViewModelGetMyTeamsUser,
    sharedViewModelGetMyTeamsUserPropostas: SharedViewModelGetMyTeamsUserPropostas,
    sharedViewModelGetMyTeamsUserPropostasDe: SharedGetMyTeamsUserPropostasDe,
    sharedViewModelGetMyTeamsUserPropostasDeJogadores: SharedGetMyTeamsUserPropostasDeJogadores,
    sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos: SharedGetMyTeamsUserPropostasDeJogadoresAtivos,
    sharedViewModelGetMyTeamsUserPropostasDePropostas: SharedGetMyTeamsUserPropostasDePropostas,

    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,

    sharedGetPeneira: SharedGetPeneira,
    sharedGetPeneiraAcepted: SharedGetPeneiraAcepted,
    sharedGetPeneiraAceptedJogadores: SharedGetPeneiraAceptedJogadores,
    sharedGetPeneiraAceptedJogadoresPerfilId: SharedGetPeneiraAceptedJogadoresPerfilId,
    sharedGetPeneiraAceptedTime: SharedGetPeneiraAceptedTime,
    sharedGetPeneiraAceptedTimeJogadores: SharedGetPeneiraAceptedTimeJogadores,
    sharedGetPeneiraAceptedTimePropostas: SharedGetPeneiraAceptedTimePropostas,

    sharedGetTimeListaPostagens: SharedGetTimeListaPostagens,
    sharedGetTimeListaPostagensPublicacao: SharedGetTimeListaPostagensPublicacao,
    sharedGetTimeListaPostagensPublicacaoDonoId: SharedGetTimeListaPostagensPublicacaoDonoId,
    sharedGetTimeListaPostagensPublicacaoTime: SharedGetTimeListaPostagensPublicacaoTime,
    sharedGetTimeListaPostagensPublicacaoTimeJogadores: SharedGetTimeListaPostagensPublicacaoTimeJogadores,
    sharedGetTimeListaPostagensPublicacaoTimePropostas: SharedGetTimeListaPostagensPublicacaoTimePropostas,
    onNavigate: (String) -> Unit
) {
    val token = sharedViewModelTokenEId.token
    Log.d("TESTE TOKEN", "Token: $token")


    val imageOrgRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageOrgCapaRef = remember { mutableStateOf<StorageReference?>(null) }

    val idUser = sharedViewModelUser.id
    val nomeUser = sharedViewModelUser.nome_usuario
    val fullNomeUser = sharedViewModelUser.nome_completo
    val dataNascimentoUser = sharedViewModelUser.data_nascimento
    val emailUser = sharedViewModelUser.email
    val nickNameUser = sharedViewModelUser.nickname
    val biografiaUser = sharedViewModelUser.biografia
    val generoPerfilUser = sharedViewModelUser.genero

    val userIdGetMyTeams = sharedViewModelGetMyTeamsUser.idData


    //Log.e("ID DE OUTRO JOGADOR", "aqui esta o id de outro jogador ${idInfoOutroJogador}")

    Log.d("PerfilUsuarioJogadorScreen", "Id do usuario organizador: $userIdGetMyTeams")

    val testeid = remember { mutableStateOf<Int?>(0) }
    val listaIds = remember {mutableListOf<Int?>()}

    val listaIdDeJogadores = remember { mutableListOf<Int?>() }

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageOrgRef.value = storage.reference.child("${idUser}/orgprofile")
        }

        if (idUser != null && idUser != 0) {
            imageOrgCapaRef.value = storage.reference.child("${idUser}/orgcapa")
        }

    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

    //    FIREBASE


    var imageOrgUri by remember { mutableStateOf<Uri?>(null) }
    var imageOrgCapaUri by remember { mutableStateOf<Uri?>(null) }


    if (imageOrgRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uri = imageOrgRef.value!!.downloadUrl.await()
                imageOrgUri = uri

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uri}")

            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    if (imageOrgCapaRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriCapa = imageOrgCapaRef.value!!.downloadUrl.await()
                imageOrgCapaUri = uriCapa


                Log.e("URI CAPA DO USUARIO 02", "URI da imagem do usuario ${uriCapa}")
            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

    // FIREBASE
    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUser}")
    Log.e("URI CAPA DO USUARIO 03", "URI da imagem do usuario ${imageOrgCapaRef}")

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var photoUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        photoUri = it
    }

    var painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(photoUri)
            .build()
    )

    var inscricoesJogadores by remember {
        mutableStateOf(listOf<GetPeneiraAcepted>())
    }




    var campoAceitarProposta by rememberSaveable { mutableStateOf(true) }
    var mensagemAceitarProposta = rememberSaveable { mutableStateOf("") }

    var campoRecusarProposta by rememberSaveable { mutableStateOf(true) }
    var mensagemRecusarProposta = rememberSaveable { mutableStateOf("") }

    var idTimeEscolhido by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacaoTime.id) }

    Log.e("ID DO TIME INSCRICOES","ID DO TIME ESCOLHIDO LISTA DE INSCRICOES: ${idTimeEscolhido}")


    LaunchedEffect(sharedGetTimeListaPostagensPublicacaoTime) {

        idTimeEscolhido = sharedGetTimeListaPostagensPublicacaoTime.id

    }

    Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

    if(token != null && token.isNotEmpty()){

//                val perPage by remember { mutableStateOf<Int?>(null) }
//                val page by remember { mutableStateOf<Int?>(null) }
//                val name by remember { mutableStateOf<String?>("") }


        val getPeneiraService = RetrofitFactoryCadastro().getPeneiraService()

        getPeneiraService.getPeneiraPostagemTime("Bearer " + token, idTimeEscolhido).enqueue(object :
            Callback<GetPeneira> {
            override fun onResponse(call: Call<GetPeneira>, response: Response<GetPeneira>) {
                if (response.isSuccessful) {
                    Log.d("SUCESSO LISTA INSCRICOES RECEBIDAS", "Resposta bem-sucedida na lista de inscricoes recebidas: ${response.code()}")

                    val listaInscricoesRecebidas = response.body()

                    Log.d("BODY INSCRICOES RECEBIDAS", "Resposta bem-sucedida na lista de inscricoes recebidas: ${response.body()}")

                    val inscricoesList = listaInscricoesRecebidas?.acepted

                    if (inscricoesList != null) {
                        inscricoesJogadores = inscricoesList
                    }

                    sharedGetPeneira.acepted = inscricoesList

                    if(inscricoesList != null){
                        for(listInscricoesRecebidas in inscricoesList){

                            val idListInscricoesRecebidas = listInscricoesRecebidas.id
                            val menssagemListInscricoesRecebidas = listInscricoesRecebidas.menssagem

                            sharedGetPeneiraAcepted.id = listInscricoesRecebidas.id
                            sharedGetPeneiraAcepted.menssagem = listInscricoesRecebidas.menssagem


                            val timeListInscricoesRecebidas = listInscricoesRecebidas.time

                            sharedGetPeneiraAcepted.time = listInscricoesRecebidas.time

                            if(timeListInscricoesRecebidas != null){
                                val idTimeListInscricoesRecebidas = timeListInscricoesRecebidas.id
                                val nomeTimeTimeListInscricoesRecebidas = timeListInscricoesRecebidas.nome_time
                                val jogoTimeListInscricoesRecebidas = timeListInscricoesRecebidas.jogo
                                val biografiaTimeListInscricoesRecebidas = timeListInscricoesRecebidas.biografia

                                sharedGetPeneiraAceptedTime.id = timeListInscricoesRecebidas.id
                                sharedGetPeneiraAceptedTime.nome_time = timeListInscricoesRecebidas.nome_time
                                sharedGetPeneiraAceptedTime.jogo = timeListInscricoesRecebidas.jogo
                                sharedGetPeneiraAceptedTime.biografia = timeListInscricoesRecebidas.biografia

                                val jogadoresTimeListInscricoesRecebidas = timeListInscricoesRecebidas.jogadores

                                sharedGetPeneiraAceptedTime.jogadores = timeListInscricoesRecebidas.jogadores

                                if(jogadoresTimeListInscricoesRecebidas != null){
                                    for( jogadoresTimeListInscricoes in jogadoresTimeListInscricoesRecebidas){
                                        val idJogadoresTimeListInscricoes = jogadoresTimeListInscricoes.id
                                        val nicknameJogadoresTimeListInscricoes = jogadoresTimeListInscricoes.nickname
                                        val jogoJogadoresTimeListInscricoes = jogadoresTimeListInscricoes.jogo
                                        val funcaoJogadoresTimeListInscricoes = jogadoresTimeListInscricoes.funcao
                                        val eloJogadoresTimeListInscricoes = jogadoresTimeListInscricoes.elo


                                        sharedGetPeneiraAceptedTimeJogadores.id = jogadoresTimeListInscricoes.id
                                        sharedGetPeneiraAceptedTimeJogadores.nickname = jogadoresTimeListInscricoes.nickname
                                        sharedGetPeneiraAceptedTimeJogadores.jogo = jogadoresTimeListInscricoes.jogo
                                        sharedGetPeneiraAceptedTimeJogadores.funcao = jogadoresTimeListInscricoes.funcao
                                        sharedGetPeneiraAceptedTimeJogadores.elo = jogadoresTimeListInscricoes.elo


                                    }
                                }

                                val propostasTimeListInscricoesRecebidas = timeListInscricoesRecebidas.propostas

                                sharedGetPeneiraAceptedTime.propostas = timeListInscricoesRecebidas.propostas

                                if(propostasTimeListInscricoesRecebidas != null){
                                    for(propostasTimeListInscricoes in propostasTimeListInscricoesRecebidas){
                                        val idPropostasTimeListInscricoes = propostasTimeListInscricoes.id
                                        val menssagemPropostasTimeListInscricoes = propostasTimeListInscricoes.menssagem

                                        sharedGetPeneiraAceptedTimePropostas.id = propostasTimeListInscricoes.id
                                        sharedGetPeneiraAceptedTimePropostas.menssagem = propostasTimeListInscricoes.menssagem

                                    }
                                }
                            }


                            val jogadoresListInscricoesRecebidas = listInscricoesRecebidas.jogadores

                            sharedGetPeneiraAcepted.jogadores = listInscricoesRecebidas.jogadores

                            if(jogadoresListInscricoesRecebidas != null){
                                for(jogadoresListInscricoesRecebidasJogadores in jogadoresListInscricoesRecebidas){

                                    val idJogadoresListInscricoesRecebidasJogadores = jogadoresListInscricoesRecebidasJogadores.id
                                    val nicknameJogadoresListInscricoesRecebidasJogadores = jogadoresListInscricoesRecebidasJogadores.nickname
                                    val jogoJogadoresListInscricoesRecebidasJogadores = jogadoresListInscricoesRecebidasJogadores.jogo
                                    val funcaoJogadoresListInscricoesRecebidasJogadores = jogadoresListInscricoesRecebidasJogadores.funcao
                                    val eloJogadoresListInscricoesRecebidasJogadores = jogadoresListInscricoesRecebidasJogadores.elo

                                    sharedGetPeneiraAceptedJogadores.id = jogadoresListInscricoesRecebidasJogadores.id
                                    sharedGetPeneiraAceptedJogadores.nickname = jogadoresListInscricoesRecebidasJogadores.nickname
                                    sharedGetPeneiraAceptedJogadores.jogo = jogadoresListInscricoesRecebidasJogadores.jogo
                                    sharedGetPeneiraAceptedJogadores.funcao = jogadoresListInscricoesRecebidasJogadores.funcao
                                    sharedGetPeneiraAceptedJogadores.elo = jogadoresListInscricoesRecebidasJogadores.elo


                                    val perfilIdJogadoresListInscricoesRecebidasJogadores = jogadoresListInscricoesRecebidasJogadores.perfil_id

                                    sharedGetPeneiraAceptedJogadores.perfil_id = jogadoresListInscricoesRecebidasJogadores.perfil_id

                                    if(perfilIdJogadoresListInscricoesRecebidasJogadores != null){
                                        val idPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.id
                                        val nomeUsuarioPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.nome_usuario
                                        val nomeCompletoPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.nome_completo
                                        val emailPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.email
                                        val dataNascimentoPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.data_nascimento
                                        val generoPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.genero
                                        val nicknamePerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.nickname
                                        val biografiaPerfilIdJogadoresListInscricoesRecebidasJogadores = perfilIdJogadoresListInscricoesRecebidasJogadores.biografia


                                        sharedGetPeneiraAceptedJogadoresPerfilId.id = perfilIdJogadoresListInscricoesRecebidasJogadores.id
                                        sharedGetPeneiraAceptedJogadoresPerfilId.nome_usuario = perfilIdJogadoresListInscricoesRecebidasJogadores.nome_usuario
                                        sharedGetPeneiraAceptedJogadoresPerfilId.nome_completo = perfilIdJogadoresListInscricoesRecebidasJogadores.nome_completo
                                        sharedGetPeneiraAceptedJogadoresPerfilId.email = perfilIdJogadoresListInscricoesRecebidasJogadores.email
                                        sharedGetPeneiraAceptedJogadoresPerfilId.data_nascimento = perfilIdJogadoresListInscricoesRecebidasJogadores.data_nascimento
                                        sharedGetPeneiraAceptedJogadoresPerfilId.genero = perfilIdJogadoresListInscricoesRecebidasJogadores.genero
                                        sharedGetPeneiraAceptedJogadoresPerfilId.nickname = perfilIdJogadoresListInscricoesRecebidasJogadores.nickname
                                        sharedGetPeneiraAceptedJogadoresPerfilId.biografia = perfilIdJogadoresListInscricoesRecebidasJogadores.biografia
                                    }
                                }
                            }


                        }
                    }

                } else {
                    // Trate a resposta não bem-sucedida
                    Log.d("ListaDeInscricoesParaTimesScreen", "Resposta não bem-sucedida: ${response.code()}")
                    // Log de corpo da resposta (se necessário)
                    Log.d(
                        "ListaDeInscricoesParaTimesScreen",
                        "Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<GetPeneira>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("ListaDeInscricoesParaTimesScreen", "Erro de rede: ${t.message}")
            }

        })

    } else{
        Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")

    }



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
        ///////
        // Imagem Capa
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(200.dp)
//
//        ) {
//
//            if (idUser != null && idUser != 0) {
//                // Exiba a imagem se a URI estiver definida
//                AsyncImage(
//                    model = imageOrgCapaUri,
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )
//            } else {
//                // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
//
//            }
//        }

        Row(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        ) {
            Icon(
                modifier = Modifier.clickable {
                    //rememberNavController.navigate("home")
                    onNavigate("escolher_time_para_visualizar_postagem_time")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "INSCRITOS",
                fontFamily = customFontFamilyText,
                fontSize = 25.sp,
                fontWeight = FontWeight(900),
                color = Color.White
            )
        }



        val listaIdsPerfisJogadores = remember { mutableListOf<Int>() }


        Column(
            modifier = Modifier
                .padding(top = 40.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                content = {
                    items(inscricoesJogadores.size){ index ->
                        val infoInscricoesRecebidas = inscricoesJogadores[index]

                        val idDaPeneira = infoInscricoesRecebidas.id ?: 0
                        val menssagemDaPeneira = infoInscricoesRecebidas.menssagem ?: ""
                        val timeDaPeneira = infoInscricoesRecebidas.time ?: null
                        val jogadoresDaPeneira = infoInscricoesRecebidas.jogadores ?: null



                        val idTimeDaPeneira = timeDaPeneira?.id ?: 0
                        val nomeTimeTimeDaPeneira = timeDaPeneira?.nome_time ?: ""
                        val jogoTimeDaPeneira = timeDaPeneira?.jogo ?: 0
                        val biografiaTimeDaPeneira = timeDaPeneira?.biografia ?: ""
                        val jogadoresTimeDaPeneira = timeDaPeneira?.jogadores ?: null
                        val propostasTimeDaPeneira = timeDaPeneira?.propostas ?: null

                        if(jogadoresDaPeneira != null){
                            for(jogadoresDaPeneiraList in jogadoresDaPeneira){

                                val idJogadoresDaPeneiraList = jogadoresDaPeneiraList.id ?: 0
                                val nicknameJogadoresDaPeneiraList = jogadoresDaPeneiraList.nickname ?: ""
                                val jogoJogadoresDaPeneiraList = jogadoresDaPeneiraList.jogo ?: 0
                                val funcaoJogadoresDaPeneiraList = jogadoresDaPeneiraList.funcao ?: 0
                                val eloJogadoresDaPeneiraList = jogadoresDaPeneiraList.elo ?: 0

                                val perfilIdJogadoresDaPeneiraList = jogadoresDaPeneiraList.perfil_id ?: null

                                val idPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.id ?: 0
                                val nomeUsuarioPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.nome_usuario ?: ""
                                val nomeCompletoPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.nome_completo ?: ""
                                val emailPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.email ?: ""
                                val dataNascimentoPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.data_nascimento ?: ""
                                val generoPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.genero ?: 0
                                val nicknamePerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.nickname ?: ""
                                val biografiaPerfilIdJogadoresDaPeneiraList = perfilIdJogadoresDaPeneiraList?.biografia ?: ""

                                listaIdsPerfisJogadores.add(idPerfilIdJogadoresDaPeneiraList)

                                val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                                if(idPerfilIdJogadoresDaPeneiraList != null && idPerfilIdJogadoresDaPeneiraList != 0){


                                    val storage = Firebase.storage

                                    if (inscricoesJogadores != null && inscricoesJogadores.isNotEmpty()) {

                                        if (idPerfilIdJogadoresDaPeneiraList != null && idPerfilIdJogadoresDaPeneiraList != 0) {
                                            // Utilize o ID do perfil aqui
                                            imageRef.value = storage.reference.child("$idPerfilIdJogadoresDaPeneiraList/profile")
                                        } else {
                                            Log.e("ID DO PERFIL INVÁLIDO", "O ID do perfil é nulo ou igual a zero")
                                        }
                                    } else {
                                        Log.e("LISTA DE JOGADORES VAZIA", "A lista de jogadores está vazia ou nula")
                                    }


                                } else{
                                    Log.e("TOKEN NULO", "Token do usuario esta nulo")
                                    Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
                                }

                                var imageUri by remember { mutableStateOf<Uri?>(null) }

                                if (imageRef.value != null) { // Verifique a referência do Firebase
                                    LaunchedEffect(Unit) {
                                        try {
                                            val uriOrg = imageRef.value!!.downloadUrl.await()
                                            imageUri = uriOrg

                                            Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriOrg}")

                                        } catch (e: Exception) {
                                            // Trate os erros, se houver algum
                                            Log.e("DEBUG", "Erro ao buscar imagem: $e")
                                        }
                                    }
                                }

                                Log.e("ID DO TIME ","ID DO TIME ${idTimeEscolhido}")
                                Log.e("ID DO JOGADOR ","ID DO JOGADOR DA PENEIRA ${idJogadoresDaPeneiraList}")


                                fun RecusarInscricaoFunction(
                                    sharedViewModelTokenEId: SharedViewTokenEId,
                                ){

                                    val token = sharedViewModelTokenEId.token

                                    val recusarAProposta = 0

                                    val recusarPeneiraService = RetrofitFactoryCadastro().deletarPeneiraService()

                                    recusarPeneiraService.deletarPeneira("Bearer " + token, idTimeEscolhido, idJogadoresDaPeneiraList, recusarAProposta).enqueue(object :
                                        Callback<PeneiraResponse> {
                                        override fun onResponse(call: Call<PeneiraResponse>, response: Response<PeneiraResponse>) {
                                            if (response.isSuccessful) {
                                                Log.d("SUCESSO AO RECUSAR INSCRICAO", "Resposta bem-sucedida, inscricao recusada!: ${response.code()}")

                                            } else {
                                                // Trate a resposta não bem-sucedida
                                                Log.d("listaInscricoesRecebidasJogadoresScreen", " listaInscricoesRecebidasJogadoresScreen, Resposta não bem-sucedida: ${response.code()}")
                                                // Log de corpo da resposta (se necessário)
                                                Log.d(
                                                    "listaInscricoesRecebidasJogadoresScreen",
                                                    "listaInscricoesRecebidasJogadoresScreen, Corpo da resposta: ${response.errorBody()?.string()}"
                                                )
                                            }
                                        }

                                        override fun onFailure(call: Call<PeneiraResponse>, t: Throwable) {
                                            // Trate o erro de falha na rede.
                                            Log.d("listaInscricoesRecebidasJogadoresScreen", " listaPropostasRecebidasJogadoresScreen, Erro de rede: ${t.message}")
                                        }

                                    })
                                }


                                fun AceitarInscricaoFunction(
                                    sharedViewModelTokenEId: SharedViewTokenEId,
                                ){

                                    val token = sharedViewModelTokenEId.token

                                    val aceitarAInscricao = 1

                                    val aceitarInscricaoService = RetrofitFactoryCadastro().deletarPeneiraService()

                                    aceitarInscricaoService.deletarPeneira("Bearer " + token, idTimeEscolhido, idJogadoresDaPeneiraList, aceitarAInscricao).enqueue(object :
                                        Callback<PeneiraResponse> {
                                        override fun onResponse(call: Call<PeneiraResponse>, response: Response<PeneiraResponse>) {
                                            if (response.isSuccessful) {
                                                Log.d("SUCESSO AO ACEITAR INSCRIÇÃO", "Resposta bem-sucedida, inscrição aceita!: ${response.code()}")

                                            } else {
                                                // Trate a resposta não bem-sucedida
                                                Log.d("listaInscricoesRecebidasJogadoresScreen", " listaInscricoesRecebidasJogadoresScreen, Resposta não bem-sucedida: ${response.code()}")
                                                // Log de corpo da resposta (se necessário)
                                                Log.d(
                                                    "listaInscricoesRecebidasJogadoresScreen",
                                                    "listaInscricoesRecebidasJogadoresScreen, Corpo da resposta: ${response.errorBody()?.string()}"
                                                )
                                            }
                                        }

                                        override fun onFailure(call: Call<PeneiraResponse>, t: Throwable) {
                                            // Trate o erro de falha na rede.
                                            Log.d("listaInscricoesRecebidasJogadoresScreen", " listaInscricoesRecebidasJogadoresScreen, Erro de rede: ${t.message}")
                                        }

                                    })
                                }


                                //jogos
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .height(350.dp)
                                        .padding(top = 20.dp),
                                ) {
                                    Button(
                                        onClick = {
//                                    sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador = idInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.nomeUsuarioInfoPerfilJogador = nomeUsuarioInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.nomeCompletoInfoPerfilJogador = nomeCompletoInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.emailInfoPerfilJogador = emailInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.dataNascimentoInfoPerfilJogador = dataNascimentoInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.generoInfoPerfilJogador = generoInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.nickNameInfoPerfilJogador = nickNameInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.biografiaInfoPerfilJogador = biografiaInfoPerfilJogador
//
//                                    onNavigate("carregar_informacoes_perfil_outro_jogador")

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(350.dp)
                                            .padding(start = 0.dp, top = 0.dp),
                                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                        colors = ButtonDefaults.buttonColors(BlackTransparentProliseum),
                                    ) {

                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize(),


                                            ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(top = 20.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                            ) {
                                                Box(contentAlignment = Alignment.BottomEnd) {
                                                    Card(
                                                        modifier = Modifier
                                                            .height(150.dp)
                                                            .width(150.dp),

                                                        shape = CircleShape
                                                    ) {

                                                        if (idUser != null && idUser != 0) {
                                                            // Exiba a imagem se a URI estiver definida
                                                            AsyncImage(
                                                                model = imageUri,
                                                                contentDescription = null,
                                                                modifier = Modifier.fillMaxSize(),
                                                                contentScale = ContentScale.Crop
                                                            )
                                                        } else {
                                                            // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
                                                            Text("Carregando imagem...")
                                                        }
                                                    }
                                                }

                                            }



                                            Spacer(modifier = Modifier.height(10.dp))

                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                            ){
                                                Row(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Center,
                                                ) {
                                                    if (nicknameJogadoresDaPeneiraList != null) {
                                                        Text(
                                                            text = "${nicknameJogadoresDaPeneiraList}",
                                                            color = Color.White,
                                                            modifier = Modifier.padding(5.dp),
                                                            fontWeight = FontWeight(600),
                                                            fontFamily = customFontFamilyText,
                                                            fontSize = 22.sp
                                                        )
                                                    }
                                                }

                                                Spacer(modifier = Modifier.height(20.dp))


                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .height(50.dp),
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    Button(
                                                        onClick = {
                                                            AceitarInscricaoFunction(
                                                                sharedViewModelTokenEId
                                                            )


                                                            campoAceitarProposta = false
                                                            mensagemAceitarProposta.value = "INSCRIÇÃO ACEITA!"

                                                        },
                                                        modifier = Modifier
                                                            .width(150.dp)
                                                            .height(50.dp),
                                                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                                                        colors = ButtonDefaults.buttonColors(RedProliseum),
                                                    ) {
                                                        Text(
                                                            text = "ACEITAR",
                                                            color = Color.White,
                                                            modifier = Modifier
                                                                .padding(5.dp)
                                                                .background(
                                                                    color = RedProliseum,
                                                                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
                                                                ),
                                                            fontWeight = FontWeight(900),
                                                            fontFamily = customFontFamilyText,
                                                            fontSize = 16.sp
                                                        )
                                                    }


                                                    Spacer(modifier = Modifier.width(25.dp))

                                                    Button(
                                                        onClick = {

                                                            RecusarInscricaoFunction(
                                                                sharedViewModelTokenEId
                                                            )

                                                            campoRecusarProposta = false
                                                            mensagemRecusarProposta.value = "INSCRIÇÃO RECUSADA!"


                                                        },
                                                        modifier = Modifier
                                                            .width(150.dp)
                                                            .height(50.dp),
                                                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                                                        colors = ButtonDefaults.buttonColors(RedProliseum),
                                                    ) {
                                                        Text(
                                                            text = "RECUSAR",
                                                            color = Color.White,
                                                            modifier = Modifier
                                                                .padding(5.dp)
                                                                .background(
                                                                    color = RedProliseum,
                                                                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
                                                                ),
                                                            fontWeight = FontWeight(900),
                                                            fontFamily = customFontFamilyText,
                                                            fontSize = 16.sp
                                                        )
                                                    }

                                                }

                                            }

                                            Spacer(modifier = Modifier.height(20.dp))










                                        }

                                    }
                                }

                                Spacer(modifier = Modifier.height(40.dp))


                            }
                        }

                    }
                }
            )
        }

        LaunchedEffect(campoRecusarProposta) {
            if (!campoRecusarProposta) {
                delay(3000)
                campoRecusarProposta = true
                onNavigate("carregar_tela_listagem_inscricoes")
            }
        }

        AnimatedVisibility(
            visible = !campoRecusarProposta,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Snackbar(
                modifier = Modifier.padding(top = 16.dp),
                action = {}
            ) {
                Text(text = mensagemRecusarProposta.value)
            }
        }

        LaunchedEffect(campoAceitarProposta) {
            if (!campoAceitarProposta) {
                delay(3000)
                campoAceitarProposta = true
                onNavigate("carregar_informacoes_perfil_usuario")
            }
        }

        AnimatedVisibility(
            visible = !campoAceitarProposta,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Snackbar(
                modifier = Modifier.padding(top = 16.dp),
                action = {}
            ) {
                Text(text = mensagemAceitarProposta.value)
            }
        }




    }

}

