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
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneira
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneira
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimePropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoTimes
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
fun ListaDePublicacoesDeTimesScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedViewModelPerfil: SharedViewModelPerfil,
    sharedViewModelUser: SharedViewModelUser,
    sharedViewModelPerfilPropostas: SharedViewModelPerfilPropostas,
    sharedViewModelPerfilPropostasDe: SharedViewModelPerfilPropostasDe,
    sharedViewModelPerfilPropostasDeJogadores: SharedViewModelPerfilPropostasDeJogadores,
    sharedViewModelPerfilPropostasDePropostas: SharedViewModelPerfilPropostasDePropostas,

    sharedViewModelPlayerProfile: SharedViewModelPlayerProfile,
    sharedViewModelPlayerProfileTimeAtual: SharedViewModelPlayerProfileTimeAtual,
    sharedViewModelPlayerProfileTimeAtualJogadores: SharedViewModelPlayerProfileTimeAtualJogadores,
    sharedViewModelPlayerProfileTimeAtualPropostas: SharedViewModelPlayerProfileTimeAtualPropostas,


    // SharedViewModel GET MY TEAMS GERAL
    sharedGetMyTeamsGeral: SharedGetMyTeamsGeral,

    // SharedViewModelGetMyTeams de USUARIO
    sharedViewModelGetMyTeamsUser: SharedViewModelGetMyTeamsUser,
    sharedViewModelGetMyTeamsUserPropostas: SharedViewModelGetMyTeamsUserPropostas,
    sharedViewModelGetMyTeamsUserPropostasDe: SharedGetMyTeamsUserPropostasDe,
    sharedViewModelGetMyTeamsUserPropostasDeJogadores: SharedGetMyTeamsUserPropostasDeJogadores,
    sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos: SharedGetMyTeamsUserPropostasDeJogadoresAtivos,
    sharedViewModelGetMyTeamsUserPropostasDePropostas: SharedGetMyTeamsUserPropostasDePropostas,

    // SharedViewModelGetMyTeams de TIME
    sharedViewModelGetMyTeamsTime: SharedViewModelGetMyTeamsTime,
    sharedViewModelGetMyTeamsTimeJogadores: SharedViewModelGetMyTeamsTimeJogadores,
    sharedViewModelGetMyTeamsTimeJogadoresAtivos: SharedGetMyTeamsTimeJogadoresAtivos,
    sharedViewModelGetMyTeamsTimePropostas: SharedViewModelGetMyTeamsTimePropostas,

    sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
    sharedViewModelGetListaJogadores: SharedViewModelGetListaJogadores,
    sharedViewModelGetListaJogadoresList: SharedViewModelGetListaJogadoresList,
    sharedViewModelGetListaJogadoresInfoPerfil: SharedViewModelGetListaJogadoresInfoPerfil,
    sharedViewModelGetListaJogadoresTimeAtual: SharedViewModelGetListaJogadoresTimeAtual,
    sharedViewModelGetListaJogadoresDentroDeTime: SharedViewModelGetListaJogadoresDentroDeTime,
    sharedViewModelGetListaJogadoresDentroDeTimeList: SharedViewModelGetListaJogadoresDentroDeTimeList,
    sharedViewModelGetListaJogadoresPropostasList: SharedViewModelGetListaJogadoresPropostasList,
    sharedViewModelGetListaJogadoresPropostasRecebidas: SharedViewModelGetListaJogadoresPropostasRecebidas,


    sharedViewModelListaPublicacaoTimes: SharedViewModelListaPublicacaoTimes,

    sharedGetTimeListaPostagens: SharedGetTimeListaPostagens,
    sharedGetTimeListaPostagensPublicacao: SharedGetTimeListaPostagensPublicacao,
    sharedGetTimeListaPostagensPublicacaoDonoId: SharedGetTimeListaPostagensPublicacaoDonoId,
    sharedGetTimeListaPostagensPublicacaoTime: SharedGetTimeListaPostagensPublicacaoTime,
    sharedGetTimeListaPostagensPublicacaoTimeJogadores: SharedGetTimeListaPostagensPublicacaoTimeJogadores,
    sharedGetTimeListaPostagensPublicacaoTimePropostas: SharedGetTimeListaPostagensPublicacaoTimePropostas,

    //SharedViewModel GET MINHA POSTAGEM
    sharedGetMinhaPostagem: SharedGetMinhaPostagem,
    sharedGetMinhaPostagemUser: SharedGetMinhaPostagemUser,
    sharedGetMinhaPostagemUserPropostas: SharedGetMinhaPostagemUserPropostas,
    sharedGetMinhaPostagemPostProfile: SharedGetMinhaPostagemPostProfile,

    sharedEntrarNaPeneira: SharedEntrarNaPeneira,
    sharedEntrarNaPeneiraAcepted: SharedEntrarNaPeneiraAcepted,
    sharedEntrarNaPeneiraAceptedJogadores: SharedEntrarNaPeneiraAceptedJogadores,
    sharedEntrarNaPeneiraAceptedJogadoresPerfilId: SharedEntrarNaPeneiraAceptedJogadoresPerfilId,
    sharedEntrarNaPeneiraAceptedJogadoresTimeAtual: SharedEntrarNaPeneiraAceptedJogadoresTimeAtual,
    sharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores: SharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores,
    sharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas: SharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas,
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

    val dadosGeraisGetMyTeams = sharedGetMyTeamsGeral.myTeamsDadosGeral
    val dadosTimeGetMyTeams = sharedGetMyTeamsGeral.time

    val userIdGetMyTeams = sharedViewModelGetMyTeamsUser.idData

    val idTime = sharedViewModelGetMyTeamsTime.idData

    val nomeTime = sharedViewModelGetMyTeamsTime.nomeTimeData
    val jogoTime = sharedViewModelGetMyTeamsTime.jogoData

    //INFORMAÇÃO DE OUTROS JOGADORES

    val idInfoOutroJogador by remember { mutableStateOf(sharedViewModelGetListaJogadoresList.id) }
    val nickNameInfoOutroJogador = sharedViewModelGetListaJogadoresList.nickname
    val jogoInfoOutroJogador = sharedViewModelGetListaJogadoresList.jogo
    val funcaoInfoOutroJogador = sharedViewModelGetListaJogadoresList.funcao
    val eloInfoOutroJogador = sharedViewModelGetListaJogadoresList.elo

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

    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemErroInputsPerfil = rememberSaveable { mutableStateOf("") }

    var publicacoesTimes by remember {
        mutableStateOf(listOf<GetTimePostagemListPublicacao>())
    }

    // Se o tempo de espera terminou, continue com a validação do token
    // Restante do código aqui
//    val token = sharedViewModelTokenEId.token
    // Restante do seu código de validação do token
    Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

    if(token != null && token.isNotEmpty()){

//                val perPage by remember { mutableStateOf<Int?>(null) }
//                val page by remember { mutableStateOf<Int?>(null) }
//                val name by remember { mutableStateOf<String?>("") }

        val perPage = null
        val page = null
        val hora = null
        val elo = null
        val funcao = null
        val tipo = 1


        sharedViewModelListaPublicacaoTimes.perPage = perPage
        sharedViewModelListaPublicacaoTimes.page = page
        sharedViewModelListaPublicacaoTimes.hora = hora
        sharedViewModelListaPublicacaoTimes.elo = elo
        sharedViewModelListaPublicacaoTimes.funcao = funcao
        sharedViewModelListaPublicacaoTimes.tipo = tipo



        val getListaPublicacoesTimes = RetrofitFactoryCadastro().getTimePostagemListService()

        getListaPublicacoesTimes.postagemTimeService(tipo, perPage, page, hora, elo, funcao).enqueue(object :
            Callback<GetTimePostagemList> {
            override fun onResponse(call: Call<GetTimePostagemList>, response: Response<GetTimePostagemList>) {
                if (response.isSuccessful) {
                    Log.d("SUCESSO LISTA POSTAGENS", "Resposta bem-sucedida na lista de postagens: ${response.code()}")

                    val listaPublicacoesTimesResponse = response.body()

                    Log.d("BODY LISTA POSTAGENS", "Resposta bem-sucedida na lista de postagens: ${response.body()}")

                    val publicacoesTimesList = listaPublicacoesTimesResponse?.post

                    if (publicacoesTimesList != null) {
                        publicacoesTimes = publicacoesTimesList
                    }

                    sharedGetTimeListaPostagens.post = publicacoesTimesList

                    if(publicacoesTimesList != null){
                        for(listPublicacoes in publicacoesTimesList){
                            val idPublicao = listPublicacoes.id
                            val descricaoPublicao = listPublicacoes.descricao
                            val jogoPublicao = listPublicacoes.jogo
                            val funcaoPublicao = listPublicacoes.funcao
                            val eloPublicao = listPublicacoes.elo
                            val horaPublicao = listPublicacoes.hora
                            val tipoPublicao = listPublicacoes.tipo
                            val prosPublicao = listPublicacoes.pros

                            sharedGetTimeListaPostagensPublicacao.id = listPublicacoes.id
                            sharedGetTimeListaPostagensPublicacao.descricao = listPublicacoes.descricao
                            sharedGetTimeListaPostagensPublicacao.jogo = listPublicacoes.jogo
                            sharedGetTimeListaPostagensPublicacao.funcao = listPublicacoes.funcao
                            sharedGetTimeListaPostagensPublicacao.elo = listPublicacoes.elo
                            sharedGetTimeListaPostagensPublicacao.hora = listPublicacoes.hora
                            sharedGetTimeListaPostagensPublicacao.tipo = listPublicacoes.tipo
                            sharedGetTimeListaPostagensPublicacao.pros = listPublicacoes.pros

                            val listaInformacoesDonoId = listPublicacoes.dono_id
                            val listaInformacoesTime = listPublicacoes.time

                            sharedGetTimeListaPostagensPublicacao.dono_id = listaInformacoesDonoId
                            sharedGetTimeListaPostagensPublicacao.time = listPublicacoes.time


                            if(listaInformacoesDonoId != null){
                                val idlistaInformacoesDonoId = listaInformacoesDonoId.id
                                val nomeUsuariolistaInformacoesDonoId = listaInformacoesDonoId.nome_usuario
                                val nomeCompletolistaInformacoesDonoId = listaInformacoesDonoId.nome_completo
                                val emaillistaInformacoesDonoId = listaInformacoesDonoId.email
                                val dataNascimentolistaInformacoesDonoId = listaInformacoesDonoId.data_nascimento
                                val generolistaInformacoesDonoId = listaInformacoesDonoId.genero
                                val nicknamelistaInformacoesDonoId = listaInformacoesDonoId.nickname
                                val biografialistaInformacoesDonoId = listaInformacoesDonoId.biografia

                                sharedGetTimeListaPostagensPublicacaoDonoId.id = listaInformacoesDonoId.id
                                sharedGetTimeListaPostagensPublicacaoDonoId.nome_usuario = listaInformacoesDonoId.nome_usuario
                                sharedGetTimeListaPostagensPublicacaoDonoId.nome_completo = listaInformacoesDonoId.nome_completo
                                sharedGetTimeListaPostagensPublicacaoDonoId.email = listaInformacoesDonoId.email
                                sharedGetTimeListaPostagensPublicacaoDonoId.data_nascimento = listaInformacoesDonoId.data_nascimento
                                sharedGetTimeListaPostagensPublicacaoDonoId.genero = listaInformacoesDonoId.genero
                                sharedGetTimeListaPostagensPublicacaoDonoId.nickname = listaInformacoesDonoId.nickname
                                sharedGetTimeListaPostagensPublicacaoDonoId.biografia = listaInformacoesDonoId.biografia
                            }

                            if(listaInformacoesTime != null){
                                val idListaInformacoesTime = listaInformacoesTime.id
                                val nomeTimeListaInformacoesTime = listaInformacoesTime.nome_time
                                val jogoListaInformacoesTime = listaInformacoesTime.jogo
                                val biografiaListaInformacoesTime = listaInformacoesTime.biografia

                                sharedGetTimeListaPostagensPublicacaoTime.id = listaInformacoesTime.id
                                sharedGetTimeListaPostagensPublicacaoTime.nome_time = listaInformacoesTime.nome_time
                                sharedGetTimeListaPostagensPublicacaoTime.jogo = listaInformacoesTime.jogo
                                sharedGetTimeListaPostagensPublicacaoTime.biografia = listaInformacoesTime.biografia

                                val jogadoresListaInformacoesTime = listaInformacoesTime.jogadores
                                val propostasListaInformacoesTime = listaInformacoesTime.propostas

                                sharedGetTimeListaPostagensPublicacaoTime.jogadores = listaInformacoesTime.jogadores
                                sharedGetTimeListaPostagensPublicacaoTime.propostas = listaInformacoesTime.propostas

                                if(jogadoresListaInformacoesTime != null){
                                    for(jogadoresListaInformacoesTimePublicaoTime in jogadoresListaInformacoesTime){
                                        val idJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.id
                                        val nicknameJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.nickname
                                        val jogoJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.jogo
                                        val funcaoJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.funcao
                                        val eloJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.elo

                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.id = jogadoresListaInformacoesTimePublicaoTime.id
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.nickname = jogadoresListaInformacoesTimePublicaoTime.nickname
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.jogo = jogadoresListaInformacoesTimePublicaoTime.jogo
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.funcao = jogadoresListaInformacoesTimePublicaoTime.funcao
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.elo = jogadoresListaInformacoesTimePublicaoTime.elo
                                    }
                                }

                                if(propostasListaInformacoesTime != null){
                                    for(propostasListaInformacoesTimePublicacaoTime in propostasListaInformacoesTime){
                                        val idPropostasListaInformacoesTimePublicacaoTime = propostasListaInformacoesTimePublicacaoTime.id
                                        val menssagemPropostasListaInformacoesTimePublicacaoTime = propostasListaInformacoesTimePublicacaoTime.menssagem

                                        sharedGetTimeListaPostagensPublicacaoTimePropostas.id = propostasListaInformacoesTimePublicacaoTime.id
                                        sharedGetTimeListaPostagensPublicacaoTimePropostas.menssagem = propostasListaInformacoesTimePublicacaoTime.menssagem
                                    }
                                }
                            }
                        }
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

            override fun onFailure(call: Call<GetTimePostagemList>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
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
                    onNavigate("navigation_proliseum")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "VAGAS",
                fontFamily = customFontFamilyText,
                fontSize = 25.sp,
                fontWeight = FontWeight(900),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(100.dp))



        val listaIdsPerfisTimes = remember { mutableListOf<Int>() }

        val verificarPostProfile = sharedGetMinhaPostagem.postProfile

        val verificarSeJogadorTemTime = sharedViewModelPlayerProfile.time_atual

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
//            if(verificarPostProfile == null && verificarSeJogadorTemTime == null){

                Column(

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Button(
                        onClick = {
                            onNavigate("escolher_time_para_criar_postagem_time")
                        },
                        modifier = Modifier
                            .width(180.dp)
                            .height(50.dp)
                            .padding(start = 0.dp, top = 0.dp),
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum),
                    ) {
                        Text(
                            text = "CRIAR POSTAGEM",
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 12.sp
                        )
                    }

                }
//            } else if(verificarPostProfile != null){

                Spacer(modifier = Modifier.width(20.dp))

                Column(

                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            onNavigate("escolher_time_para_visualizar_postagem_time")
                        },
                        modifier = Modifier
                            .width(180.dp)
                            .height(50.dp)
                            .padding(start = 0.dp, top = 0.dp),
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum),
                    ) {
                        Text(
                            text = "MINHA POSTAGEM",
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 12.sp
                        )
                    }
                }

//            } else if(verificarSeJogadorTemTime != null){
                Log.e("JA TEM TIME","ESTE JOGADOR JA TEM TIME, PORTANTO NÃO PRECISA DE POSTAGEM!")
//            }
        }


        Column(
            modifier = Modifier
                .padding(top = 120.dp)
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
                    items(publicacoesTimes.size){ index ->
                        val infoPublicacaoTime = publicacoesTimes[index]

                        val donoDaPublicacao = infoPublicacaoTime.dono_id
                        val timePublicacao = infoPublicacaoTime.time

                        val idInfoPublicacao = infoPublicacaoTime?.id ?: 0
                        val descricaoInfoPublicacao = infoPublicacaoTime?.descricao ?: ""
                        val jogoInfoPublicacao = infoPublicacaoTime?.jogo ?: ""
                        val funcaoInfoPublicacao = infoPublicacaoTime?.funcao ?: ""
                        val eloInfoPublicacao = infoPublicacaoTime?.elo ?: ""
                        val horaInfoPublicacao = infoPublicacaoTime?.hora ?: 0
                        val tipoInfoPublicacao = infoPublicacaoTime?.tipo ?: false
                        val prosInfoPublicacao = infoPublicacaoTime?.pros ?: ""


                        val idDonoPublicacaoJogador = donoDaPublicacao?.id ?: 0
                        val nomeUsuarioDonoPublicacaoJogador = donoDaPublicacao?.nome_usuario ?: ""
                        val nomeCompletoDonoPublicacaoJogador = donoDaPublicacao?.nome_completo ?: ""
                        val emailDonoPublicacaoJogador = donoDaPublicacao?.email ?: ""
                        val dataNascimentoDonoPublicacaoJogador = donoDaPublicacao?.data_nascimento ?: ""
                        val generoDonoPublicacaoJogador = donoDaPublicacao?.genero ?: 0
                        val nicknameDonoPublicacaoJogador = donoDaPublicacao?.nickname ?: ""
                        val biografiaDonoPublicacaoJogador = donoDaPublicacao?.biografia ?: ""

                        val idTimePublicaoTime = timePublicacao?.id ?: 0
                        val nomeTimeTimePublicaoTime = timePublicacao?.nome_time ?: ""
                        val jogoTimePublicaoTime = timePublicacao?.jogo ?: 0
                        val biografiaTimePublicaoTime = timePublicacao?.biografia ?: ""


                        listaIdsPerfisTimes.add(idTimePublicaoTime)

                        val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                        if(idTimePublicaoTime != null && idTimePublicaoTime != 0){


                            val storage = Firebase.storage

                            if (publicacoesTimes != null && publicacoesTimes.isNotEmpty()) {

                                if (idTimePublicaoTime != null && idTimePublicaoTime != 0) {
                                    // Utilize o ID do perfil aqui
                                    imageRef.value = storage.reference.child("team/$idTimePublicaoTime/profile")
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


                        //jogos
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(550.dp)
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
                                    .height(650.dp)
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

                                    Row(
                                        modifier = Modifier
                                            .height(80.dp)
                                            .fillMaxWidth()
                                    ){


                                        Text(
                                            text = "${infoPublicacaoTime.descricao}",
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(600),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 14.sp
                                        )

                                        Spacer(modifier = Modifier.width(20.dp))


                                    }

                                    Spacer(modifier = Modifier.height(10.dp))


                                    Column {

                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(150.dp),
                                        ) {
                                            Column {

                                                Text(
                                                    text = "ELO",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 14.sp
                                                )

                                                Card(
                                                    modifier = Modifier
                                                        .height(55.dp)
                                                        .width(55.dp),
                                                    colors = CardDefaults.cardColors(RedProliseum)
                                                ) {
                                                    Image(
                                                        painter =
                                                        if ("${infoPublicacaoTime.elo}" == "0") painterResource(id = R.drawable.unranked_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "1") painterResource(id = R.drawable.iron_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "2") painterResource(id = R.drawable.bronze_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "3") painterResource(id = R.drawable.silver_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "4") painterResource(id = R.drawable.gold_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "5") painterResource(id = R.drawable.platinum_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "6") painterResource(id = R.drawable.emerald_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "7") painterResource(id = R.drawable.diamond_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "8") painterResource(id = R.drawable.master_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "9") painterResource(id = R.drawable.grandmaster_proliseum_elo)
                                                        else if ("${infoPublicacaoTime.elo}" == "10") painterResource(id = R.drawable.challenger_proliseum_elo)
                                                        else painter,
                                                        contentDescription = "",
                                                        modifier = Modifier.fillMaxSize(),
                                                    )
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(25.dp))

                                            Column {

                                                Text(
                                                    text = "FUNÇÃO",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 14.sp
                                                )


                                                Card(
                                                    modifier = Modifier
                                                        .height(55.dp)
                                                        .width(55.dp),
                                                    colors = CardDefaults.cardColors(RedProliseum)
                                                ) {
                                                    Image(
                                                        painter =
                                                        if ("${infoPublicacaoTime.funcao}" == "0") painterResource(
                                                            id = R.drawable.icontoplane
                                                        )
                                                        else if ("${infoPublicacaoTime.funcao}" == "1") painterResource(id = R.drawable.iconjungle)
                                                        else if ("${infoPublicacaoTime.funcao}" == "2") painterResource(id = R.drawable.iconmidlane)
                                                        else if ("${infoPublicacaoTime.funcao}" == "3") painterResource(id = R.drawable.iconsupport)
                                                        else if ("${infoPublicacaoTime.funcao}" == "4") painterResource(id = R.drawable.iconadc)
                                                        else painter,
                                                        contentDescription = "",
                                                        modifier = Modifier.fillMaxSize(),

                                                        )
                                                }
                                            }

                                            Spacer(modifier = Modifier.width(25.dp))

                                            Column(
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ){
                                                Text(
                                                    text = "HORARIO",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 14.sp
                                                )

                                                Text(
                                                    text = "${infoPublicacaoTime.hora}",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 16.sp
                                                )
                                            }

                                            Spacer(modifier = Modifier.width(25.dp))

                                            Column(
                                            ) {
                                                Text(
                                                    text = "PROS",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 14.sp
                                                )

                                                Text(
                                                    text = "${infoPublicacaoTime.pros}",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 14.sp
                                                )
                                            }

                                        }

                                        fun entrarNaPeneira(
                                            sharedViewModelTokenEId: SharedViewTokenEId
                                        ){

                                            val token = sharedViewModelTokenEId.token

                                            // Obtenha o serviço Retrofit para editar o perfil do usuário
                                            val entrarNaPeneiraService = RetrofitFactoryCadastro().entrarNaPeneiraService()

                                            // Realize a chamada de API para editar o perfil
                                            entrarNaPeneiraService.entrarNaPeneira( "Bearer " + token, idTimePublicaoTime )
                                                .enqueue(object : Callback<EntrarNaPeneira> {
                                                    override fun onResponse(
                                                        call: Call<EntrarNaPeneira>,
                                                        response: Response<EntrarNaPeneira>
                                                    ) {
                                                        if (response.isSuccessful) {
                                                            Log.d(
                                                                "EntrarNaPeneira",
                                                                "EntrarNaPeneira, Entrou na peneira com sucesso: ${response.code()}"
                                                            )

                                                            camposPreenchidosCorretamente = false
                                                            mensagemErroInputsPerfil.value = "Entrou na peneira!"
                                                            // Trate a resposta bem-sucedida, se necessário
                                                        } else {

                                                            // Trate a resposta não bem-sucedida
                                                            Log.d(
                                                                "EntrarNaPeneira",
                                                                "EntrarNaPeneira, Falha ao tentar entrar na peneira: ${response.code()}"
                                                            )
                                                            // Log do corpo da resposta (se necessário)
                                                            Log.d(
                                                                "EntrarNaPeneira",
                                                                "EntrarNaPeneira, Corpo da resposta: ${response.errorBody()?.string()}"
                                                            )
                                                        }
                                                    }

                                                    override fun onFailure(call: Call<EntrarNaPeneira>, t: Throwable) {
                                                        // Trate o erro de falha na rede.
                                                        Log.d("EntrarNaPeneira", "Erro de rede: ${t.message}")
                                                    }
                                                })
                                        }

                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            horizontalAlignment = Alignment.End,
                                            verticalArrangement = Arrangement.Center
                                        ) {
                                            Button(
                                                onClick = {
                                                    entrarNaPeneira(
                                                        sharedViewModelTokenEId
                                                    )




                                                },
                                                modifier = Modifier
                                                    .width(170.dp)
                                                    .height(50.dp)
                                                    .padding(start = 0.dp, top = 0.dp),
                                                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                                                colors = ButtonDefaults.buttonColors(RedProliseum),
                                            ) {
                                                Text(
                                                    text = "INSCREVER-SE",
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight(900),
                                                    color = Color.White
                                                )
                                            }
                                        }


                                    }



                                }

                            }
                        }
                    }
                }
            )
        }
        //PopUp
        LaunchedEffect(camposPreenchidosCorretamente) {
            if (!camposPreenchidosCorretamente) {
                delay(5000)
                camposPreenchidosCorretamente = true
                onNavigate("home")
            }
        }

        AnimatedVisibility(
            visible = !camposPreenchidosCorretamente,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Snackbar(
                modifier = Modifier.padding(top = 16.dp),
                action = {}
            ) {
                Text(text = mensagemErroInputsPerfil.value)
            }
        }
    }
}