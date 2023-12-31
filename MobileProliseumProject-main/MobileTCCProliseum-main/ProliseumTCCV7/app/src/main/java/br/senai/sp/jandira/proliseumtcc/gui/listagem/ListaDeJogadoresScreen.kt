package br.senai.sp.jandira.proliseumtcc.gui.listagem

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetListaJogadoresList
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdDoUsuario
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.cinzaProliseumElos
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeJogadoresScreen(
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

    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,

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

//    sharedGetProfileByIdDoUsuario: SharedGetProfileByIdDoUsuario,
//    sharedGetProfileByIdUser: SharedGetProfileByIdUser,
//    sharedGetProfileByIdUserRedeSocial: SharedGetProfileByIdUserRedeSocial,
//    sharedGetProfileByIdUserHighlights: SharedGetProfileByIdUserHighlights,
//
//    sharedGetProfileByIdPlayerProfile: SharedGetProfileByIdPlayerProfile,
//    sharedGetProfileByIdPlayerProfileTimeAtual: SharedGetProfileByIdPlayerProfileTimeAtual,
//    sharedGetProfileByIdPlayerProfileTimeAtualJogadores: SharedGetProfileByIdPlayerProfileTimeAtualJogadores,
//    sharedGetProfileByIdPlayerProfileTimeAtualPropostas: SharedGetProfileByIdPlayerProfileTimeAtualPropostas,

    sharedFiltragemListaJogadores: SharedFiltragemListaJogadores,
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

    val idUsuarioJogadorPerfilUser = sharedViewModelPerfilJogador.id
    val nickNamejogadorPerfilUser = sharedViewModelPerfilJogador.nickname
    val jogoJogadorPerfilUser = sharedViewModelPerfilJogador.jogo
    val funcaoJogadorPerfilUser = sharedViewModelPerfilJogador.funcao
    val eloJogadorPerfilUser = sharedViewModelPerfilJogador.elo

    val nomeOrganizacao = sharedViewModelPerfilOrganizador.nome_organizacao
    val biografiaOrganizacao = sharedViewModelPerfilOrganizador.biografia

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

    var jogadores by remember {
        mutableStateOf(listOf<ResponseGetListaJogadoresList>())
    }

    // Se o tempo de espera terminou, continue com a validação do token
    // Restante do código aqui
//    val token = sharedViewModelTokenEId.token
    // Restante do seu código de validação do token
    Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

    var filtragemNomeJogadorState by remember { mutableStateOf(sharedFiltragemListaJogadores.name) }

    LaunchedEffect(sharedFiltragemListaJogadores) {

        // Esta parte só será executada quando o composable for inicializado
        filtragemNomeJogadorState = sharedFiltragemListaJogadores.name

        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    if(token != null && token.isNotEmpty()){

//                val perPage by remember { mutableStateOf<Int?>(null) }
//                val page by remember { mutableStateOf<Int?>(null) }
//                val name by remember { mutableStateOf<String?>("") }

        val perPage = 0
        val page = 0
        val nomeJogadorFiltrado = sharedFiltragemListaJogadores.name


        sharedViewModelNomeJogadorListaJogadores.perPage = perPage
        sharedViewModelNomeJogadorListaJogadores.page = page
        sharedViewModelNomeJogadorListaJogadores.name = nomeJogadorFiltrado



        val getInfoJogadoresService = RetrofitFactoryCadastro().getJogadoresService()

        getInfoJogadoresService.getListajogadores(perPage, page, nomeJogadorFiltrado).enqueue(object :
            Callback<ResponseGetListaJogadores> {
            override fun onResponse(call: Call<ResponseGetListaJogadores>, response: Response<ResponseGetListaJogadores>) {
                if (response.isSuccessful) {
                    Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")


                    val filtragemPorNomeData = response.body()

                    val listaJogadores = filtragemPorNomeData?.players

                    if (listaJogadores != null) {
                        jogadores = listaJogadores
                    }

                    sharedViewModelGetListaJogadores.playerList = listaJogadores ?: emptyList()


                    if(listaJogadores != null){
                        for (infoJogadores in listaJogadores){
                            val idJogador = infoJogadores.id
                            val nicknameJogador = infoJogadores.nickname
                            val jogoJogador = infoJogadores.jogo
                            val funcaoJogador = infoJogadores.funcao
                            val eloJogador = infoJogadores.elo

                            testeid.value = infoJogadores.id

                            listaIds.add(idJogador)

                            sharedViewModelGetListaJogadoresList.id = infoJogadores.id
                            sharedViewModelGetListaJogadoresList.nickname = nicknameJogador
                            sharedViewModelGetListaJogadoresList.jogo = jogoJogador
                            sharedViewModelGetListaJogadoresList.funcao = funcaoJogador
                            sharedViewModelGetListaJogadoresList.elo = eloJogador

                            //Log.e("ID DE OUTRO JOGADOR 02", "aqui esta o id de outro jogador ${idJogador}")



                            val infoPerfilId = infoJogadores.perfil_id

                            val idInfoPerfilJogador = infoPerfilId?.id
                            val nomeUsuarioInfoPerfilJogador = infoPerfilId?.nome_usuario
                            val nomeCompletoInfoPerfilJogador = infoPerfilId?.nome_completo
                            val emailInfoPerfilJogador = infoPerfilId?.email
                            val senhaInfoPerfilJogador = infoPerfilId?.senha
                            val dataNascimentoInfoPerfilJogador = infoPerfilId?.data_nascimento
                            val generoInfoPerfilJogador = infoPerfilId?.genero
                            val nickNameInfoPerfilJogador = infoPerfilId?.nickname
                            val biografiaInfoPerfilJogador = infoPerfilId?.biografia

                            listaIdDeJogadores.add(idInfoPerfilJogador)

//                            sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador = idInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.nomeUsuarioInfoPerfilJogador = nomeUsuarioInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.nomeCompletoInfoPerfilJogador = nomeCompletoInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.emailInfoPerfilJogador = emailInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.dataNascimentoInfoPerfilJogador = dataNascimentoInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.generoInfoPerfilJogador = generoInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.nickNameInfoPerfilJogador = nickNameInfoPerfilJogador
//                            sharedViewModelGetListaJogadoresInfoPerfil.biografiaInfoPerfilJogador = biografiaInfoPerfilJogador

                            val infoTimeAtual = infoJogadores.time_atual

                            val idInfoTime = infoTimeAtual?.id
                            val nomeInfoTime = infoTimeAtual?.nome_time
                            val jogoInfoTime = infoTimeAtual?.jogo
                            val biografiaInfoTime = infoTimeAtual?.biografia

                            sharedViewModelGetListaJogadoresTimeAtual.idInfoTime = idInfoTime
                            sharedViewModelGetListaJogadoresTimeAtual.nomeInfoTime = nomeInfoTime
                            sharedViewModelGetListaJogadoresTimeAtual.jogoInfoTime = jogoInfoTime
                            sharedViewModelGetListaJogadoresTimeAtual.biografiaInfoTime = biografiaInfoTime


                            val infoJogadoresDentroTime = infoTimeAtual?.jogadores

                            if(infoJogadoresDentroTime != null){
                                for (infoJogadoresTime in infoJogadoresDentroTime){
                                    val idInfoJogadoresDentroDoTime = infoJogadoresTime.id
                                    val nickNameInfoJogadoresDentroDoTime = infoJogadoresTime.nickname
                                    val jogoInfoJogadoresDentroDoTime = infoJogadoresTime.jogo
                                    val funcaoInfoJogadoresDentroDoTime = infoJogadoresTime.funcao
                                    val eloInfoJogadoresDentroDoTime = infoJogadoresTime.elo

                                    sharedViewModelGetListaJogadoresDentroDeTimeList.id = idInfoJogadoresDentroDoTime
                                    sharedViewModelGetListaJogadoresDentroDeTimeList.nickname = nickNameInfoJogadoresDentroDoTime
                                    sharedViewModelGetListaJogadoresDentroDeTimeList.jogo = jogoInfoJogadoresDentroDoTime
                                    sharedViewModelGetListaJogadoresDentroDeTimeList.funcao = funcaoInfoJogadoresDentroDoTime
                                    sharedViewModelGetListaJogadoresDentroDeTimeList.elo = eloInfoJogadoresDentroDoTime
                                }
                            }

                            val infoPropostas = infoTimeAtual?.propostas

                            if (infoPropostas != null){
                                for (infoProposta in infoPropostas){
                                    val idInfoProposta = infoProposta.id
                                    val menssagemInfoProposta = infoProposta.menssagem

                                    sharedViewModelGetListaJogadoresPropostasList.id = idInfoProposta
                                    sharedViewModelGetListaJogadoresPropostasList.menssagem = menssagemInfoProposta

                                }
                            }
                        }
                    }

                    Log.e("ID DE OUTRO JOGADOR 04", "aqui esta o id de outro jogador ${listaIds}")



                    Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelUser.id}, Nome de usuario: ${sharedViewModelUser.nome_usuario}")
                    Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")



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

            override fun onFailure(call: Call<ResponseGetListaJogadores>, t: Throwable) {
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

        Column {
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
                    text = "JOGADORES",
                    fontFamily = customFontFamilyText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(
                    onClick = {

                        sharedFiltragemListaJogadores.name = filtragemNomeJogadorState

                        onNavigate("carregar_filtragem_lista_jogadores")

                    },
                    modifier = Modifier
                        .width(390.dp)
                        .height(50.dp)
                        .padding(start = 0.dp, top = 0.dp),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(RedProliseum),
                ) {
                    Text(
                        text = "Filtrar",
                        fontFamily = customFontFamilyText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(900),
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ){



                filtragemNomeJogadorState?.let {
                    OutlinedTextField(
                        value = it,
                        onValueChange = { filtragemNomeJogadorState = it },
                        modifier = Modifier

                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "FILTRAGEM DE JOGADORES",
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(600),
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color(255, 255, 255, 255),
                            focusedBorderColor = Color(255, 255, 255, 255),
                            cursorColor = Color.White
                        ),
                        textStyle = TextStyle(color = Color.White)
                    )
                }


            }

            Spacer(modifier = Modifier.height(20.dp))
        }



        val listaIdsPerfisJogadores = remember { mutableListOf<Int>() }

        Column(
            modifier = Modifier
                .padding(top = 160.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                content = {
                    items(jogadores.size){ index ->
                        val playerListJogadores = jogadores[index]

                        val infoPerfilId = playerListJogadores.perfil_id

                        val idInfoPerfilJogador = infoPerfilId?.id ?: 0
                        val nomeUsuarioInfoPerfilJogador = infoPerfilId?.nome_usuario ?: ""
                        val nomeCompletoInfoPerfilJogador = infoPerfilId?.nome_completo ?: ""
                        val emailInfoPerfilJogador = infoPerfilId?.email ?: ""
                        val dataNascimentoInfoPerfilJogador = infoPerfilId?.data_nascimento ?: ""
                        val generoInfoPerfilJogador = infoPerfilId?.genero ?: 0
                        val nickNameInfoPerfilJogador = infoPerfilId?.nickname ?: ""
                        val biografiaInfoPerfilJogador = infoPerfilId?.biografia ?: ""





                        listaIdsPerfisJogadores.add(idInfoPerfilJogador)

                        val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                        if(idInfoPerfilJogador != null && idInfoPerfilJogador != 0){


                            val storage = Firebase.storage

                            if (jogadores != null && jogadores.isNotEmpty()) {
                                val playerListJogadores = jogadores[index]
                                val infoPerfilId = playerListJogadores.perfil_id

                                if (idInfoPerfilJogador != null && idInfoPerfilJogador != 0) {
                                    // Utilize o ID do perfil aqui
                                    imageRef.value = storage.reference.child("$idInfoPerfilJogador/profile")
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
                                .height(250.dp)
                                .padding(top = 20.dp),
                        ) {
                            Button(
                                onClick = {
                                    sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador = idInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.nomeUsuarioInfoPerfilJogador = nomeUsuarioInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.nomeCompletoInfoPerfilJogador = nomeCompletoInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.emailInfoPerfilJogador = emailInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.dataNascimentoInfoPerfilJogador = dataNascimentoInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.generoInfoPerfilJogador = generoInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.nickNameInfoPerfilJogador = nickNameInfoPerfilJogador
                                    sharedViewModelGetListaJogadoresInfoPerfil.biografiaInfoPerfilJogador = biografiaInfoPerfilJogador

                                    onNavigate("carregar_informacoes_perfil_outro_jogador_escolhido")

                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(250.dp)
                                    .padding(start = 0.dp, top = 0.dp),
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                colors = ButtonDefaults.buttonColors(BlackTransparentProliseum),
                            ) {

                                Row(
                                    modifier = Modifier
                                        .fillMaxSize(),
                                    verticalAlignment = Alignment.CenterVertically
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

                                    Spacer(modifier = Modifier.padding(start = 20.dp))

                                    Column {
                                        Text(
                                            text = "${playerListJogadores.nickname}".toUpperCase(),
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(900),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 16.sp
                                        )
                                        Column(){
                                            Card(
                                                modifier = Modifier
                                                    .size(70.dp),
                                                colors = CardDefaults.cardColors(RedProliseum)
                                            ) {
                                                Image(
                                                    painter =
                                                    if ("${playerListJogadores.jogo}" == "0") painterResource(
                                                        id = R.drawable.iconlol
                                                    )
                                                    else if ("${playerListJogadores.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                                    else if ("${playerListJogadores.jogo}" == "2") painterResource(id = R.drawable.iconlol)
                                                    else painter,
                                                    contentDescription = "",
                                                    modifier = Modifier.fillMaxSize(),
                                                    alignment = Alignment.Center,
                                                    colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                                                )
                                            }
                                        }




                                        Spacer(modifier = Modifier.height(5.dp))

                                        Row(
                                            modifier = Modifier.fillMaxWidth()
                                        ){
                                            Column {
                                                Text(
                                                    text = "ELO: ",
                                                    color = Color.White,
                                                    fontWeight = FontWeight(900),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 12.sp
                                                )


                                                Spacer(modifier = Modifier.height(2.dp))

                                                Card(
                                                    modifier = Modifier
                                                        .height(55.dp)
                                                        .width(55.dp),
                                                    colors = CardDefaults.cardColors(RedProliseum)
                                                ) {
                                                    Image(
                                                        painter =
                                                        if ("${playerListJogadores.elo}" == "0") painterResource(
                                                            id = R.drawable.unranked_proliseum_elo
                                                        )
                                                        else if ("${playerListJogadores.elo}" == "1") painterResource(id = R.drawable.iron_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "2") painterResource(id = R.drawable.bronze_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "3") painterResource(id = R.drawable.silver_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "4") painterResource(id = R.drawable.gold_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "5") painterResource(id = R.drawable.platinum_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "6") painterResource(id = R.drawable.emerald_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "7") painterResource(id = R.drawable.diamond_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "8") painterResource(id = R.drawable.master_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "9") painterResource(id = R.drawable.grandmaster_proliseum_elo)
                                                        else if ("${playerListJogadores.elo}" == "10") painterResource(id = R.drawable.challenger_proliseum_elo)
                                                        else painter,
                                                        contentDescription = "",
                                                        modifier = Modifier.fillMaxSize(),
                                                    )
                                                }
                                            }



                                            Spacer(modifier = Modifier.width(5.dp))

                                            Column {
                                                Text(
                                                    text = "FUNÇÃO: ",
                                                    color = Color.White,
                                                    fontWeight = FontWeight(900),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 12.sp
                                                )

                                                Card(
                                                    modifier = Modifier
                                                        .height(55.dp)
                                                        .width(55.dp),
                                                    colors = CardDefaults.cardColors(RedProliseum)
                                                ) {
                                                    Image(
                                                        painter =
                                                        if ("${playerListJogadores.funcao}" == "0") painterResource(
                                                            id = R.drawable.icontoplane
                                                        )
                                                        else if ("${playerListJogadores.funcao}" == "1") painterResource(id = R.drawable.iconjungle)
                                                        else if ("${playerListJogadores.funcao}" == "2") painterResource(id = R.drawable.iconmidlane)
                                                        else if ("${playerListJogadores.funcao}" == "3") painterResource(id = R.drawable.iconsupport)
                                                        else if ("${playerListJogadores.funcao}" == "4") painterResource(id = R.drawable.iconadc)
                                                        else painter,
                                                        contentDescription = "",
                                                        modifier = Modifier.fillMaxSize(),
                                                        colorFilter = ColorFilter.tint(cinzaProliseumElos)

                                                    )
                                                }
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



//        fun GetListajogadoresFunction(
//            sharedViewModelTokenEId: SharedViewTokenEId,
//            sharedViewModelPerfilEditar: SharedViewModelPerfil,
//            sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
//            sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,
//            sharedViewModelNomeJogadorListaJogadores: SharedViewModelNomeJogadorListaJogadores,
//            sharedViewModelGetListaJogadores: SharedViewModelGetListaJogadores,
//            sharedViewModelGetListaJogadoresList: SharedViewModelGetListaJogadoresList,
//            sharedViewModelGetListaJogadoresInfoPerfil: SharedViewModelGetListaJogadoresInfoPerfil,
//            sharedViewModelGetListaJogadoresTimeAtual: SharedViewModelGetListaJogadoresTimeAtual,
//            sharedViewModelGetListaJogadoresDentroDeTime: SharedViewModelGetListaJogadoresDentroDeTime,
//            sharedViewModelGetListaJogadoresDentroDeTimeList: SharedViewModelGetListaJogadoresDentroDeTimeList,
//            sharedViewModelGetListaJogadoresPropostasList: SharedViewModelGetListaJogadoresPropostasList,
//            sharedViewModelGetListaJogadoresPropostasRecebidas: SharedViewModelGetListaJogadoresPropostasRecebidas,
//        ){
//            // Se o tempo de espera terminou, continue com a validação do token
//            // Restante do código aqui
//            val token = sharedViewModelTokenEId.token
//            // Restante do seu código de validação do token
//            Log.d("CarregarPerfilUsuarioScreen", "Token: $token")
//
//            if(token != null && token.isNotEmpty()){
//
////                val perPage by remember { mutableStateOf<Int?>(null) }
////                val page by remember { mutableStateOf<Int?>(null) }
////                val name by remember { mutableStateOf<String?>("") }
//
//                val perPage = 0
//                val page = 0
//                val name = ""
//
//
//                sharedViewModelNomeJogadorListaJogadores.perPage = perPage
//                sharedViewModelNomeJogadorListaJogadores.page = page
//                sharedViewModelNomeJogadorListaJogadores.name = name
//
//
//
//                val getInfoJogadoresService = RetrofitFactoryCadastro().getJogadoresService()
//
//                getInfoJogadoresService.getListajogadores(perPage, page, name).enqueue(object :
//                    Callback<ResponseGetListaJogadores> {
//                    override fun onResponse(call: Call<ResponseGetListaJogadores>, response: Response<ResponseGetListaJogadores>) {
//                        if (response.isSuccessful) {
//                            Log.d("PerfilUsuarioJogadorScreen", "Resposta bem-sucedida: ${response.code()}")
//                            val filtragemPorNomeData = response.body()
//
//                            val listaJogadores = filtragemPorNomeData?.players
//
//                            sharedViewModelGetListaJogadores.playerList = listaJogadores ?: emptyList()
//
//
//                            if(listaJogadores != null){
//                                for (infoJogadores in listaJogadores){
//                                    val idJogador = infoJogadores.id
//                                    val nicknameJogador = infoJogadores.nickname
//                                    val jogoJogador = infoJogadores.jogo
//                                    val funcaoJogador = infoJogadores.funcao
//                                    val eloJogador = infoJogadores.elo
//
//                                    testeid.value = infoJogadores.id
//
//                                    listaIds.add(idJogador)
//
//                                    sharedViewModelGetListaJogadoresList.id = infoJogadores.id
//                                    sharedViewModelGetListaJogadoresList.nickname = nicknameJogador
//                                    sharedViewModelGetListaJogadoresList.jogo = jogoJogador
//                                    sharedViewModelGetListaJogadoresList.funcao = funcaoJogador
//                                    sharedViewModelGetListaJogadoresList.elo = eloJogador
//
//                                    //Log.e("ID DE OUTRO JOGADOR 02", "aqui esta o id de outro jogador ${idJogador}")
//
//
//
//                                    val infoPerfilId = infoJogadores.perfil_id
//
//                                    val idInfoPerfilJogador = infoPerfilId?.id
//                                    val nomeUsuarioInfoPerfilJogador = infoPerfilId?.nome_usuario
//                                    val nomeCompletoInfoPerfilJogador = infoPerfilId?.nome_completo
//                                    val emailInfoPerfilJogador = infoPerfilId?.email
//                                    val senhaInfoPerfilJogador = infoPerfilId?.senha
//                                    val dataNascimentoInfoPerfilJogador = infoPerfilId?.data_nascimento
//                                    val generoInfoPerfilJogador = infoPerfilId?.genero
//                                    val nickNameInfoPerfilJogador = infoPerfilId?.nickname
//                                    val biografiaInfoPerfilJogador = infoPerfilId?.biografia
//
//                                    sharedViewModelGetListaJogadoresInfoPerfil.idInfoPerfilJogador = idInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.nomeUsuarioInfoPerfilJogador = nomeUsuarioInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.nomeCompletoInfoPerfilJogador = nomeCompletoInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.emailInfoPerfilJogador = emailInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.dataNascimentoInfoPerfilJogador = dataNascimentoInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.generoInfoPerfilJogador = generoInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.nickNameInfoPerfilJogador = nickNameInfoPerfilJogador
//                                    sharedViewModelGetListaJogadoresInfoPerfil.biografiaInfoPerfilJogador = biografiaInfoPerfilJogador
//
//                                    val infoTimeAtual = infoJogadores.time_atual
//
//                                    val idInfoTime = infoTimeAtual?.id
//                                    val nomeInfoTime = infoTimeAtual?.nome_time
//                                    val jogoInfoTime = infoTimeAtual?.jogo
//                                    val biografiaInfoTime = infoTimeAtual?.biografia
//
//                                    sharedViewModelGetListaJogadoresTimeAtual.idInfoTime = idInfoTime
//                                    sharedViewModelGetListaJogadoresTimeAtual.nomeInfoTime = nomeInfoTime
//                                    sharedViewModelGetListaJogadoresTimeAtual.jogoInfoTime = jogoInfoTime
//                                    sharedViewModelGetListaJogadoresTimeAtual.biografiaInfoTime = biografiaInfoTime
//
//
//                                    val infoJogadoresDentroTime = infoTimeAtual?.jogadores
//
//                                    if(infoJogadoresDentroTime != null){
//                                        for (infoJogadoresTime in infoJogadoresDentroTime){
//                                            val idInfoJogadoresDentroDoTime = infoJogadoresTime.id
//                                            val nickNameInfoJogadoresDentroDoTime = infoJogadoresTime.nickname
//                                            val jogoInfoJogadoresDentroDoTime = infoJogadoresTime.jogo
//                                            val funcaoInfoJogadoresDentroDoTime = infoJogadoresTime.funcao
//                                            val eloInfoJogadoresDentroDoTime = infoJogadoresTime.elo
//
//                                            sharedViewModelGetListaJogadoresDentroDeTimeList.id = idInfoJogadoresDentroDoTime
//                                            sharedViewModelGetListaJogadoresDentroDeTimeList.nickname = nickNameInfoJogadoresDentroDoTime
//                                            sharedViewModelGetListaJogadoresDentroDeTimeList.jogo = jogoInfoJogadoresDentroDoTime
//                                            sharedViewModelGetListaJogadoresDentroDeTimeList.funcao = funcaoInfoJogadoresDentroDoTime
//                                            sharedViewModelGetListaJogadoresDentroDeTimeList.elo = eloInfoJogadoresDentroDoTime
//                                        }
//                                    }
//
//                                    val infoPropostas = infoTimeAtual?.propostas
//
//                                    if (infoPropostas != null){
//                                        for (infoProposta in infoPropostas){
//                                            val idInfoProposta = infoProposta.id
//                                            val menssagemInfoProposta = infoProposta.menssagem
//
//                                            sharedViewModelGetListaJogadoresPropostasList.id = idInfoProposta
//                                            sharedViewModelGetListaJogadoresPropostasList.menssagem = menssagemInfoProposta
//
//                                        }
//                                    }
//                                }
//                            }
//
//                            Log.e("ID DE OUTRO JOGADOR 04", "aqui esta o id de outro jogador ${listaIds}")
//
//
//
//                            Log.d("INFORMAÇOES DE USUARIO 01", "Token: $token, Id: ${sharedViewModelPerfilEditar.id}, Nome de usuario: ${sharedViewModelPerfilEditar.nome_usuario}")
//                            Log.d("CarregarPerfilUsuarioScreen", "Resposta corpo bem-sucedida: ${response.code()}")
//
//
//
//                        } else {
//                            // Trate a resposta não bem-sucedida
//                            Log.d("PerfilUsuarioJogadorScreen", "Resposta não bem-sucedida: ${response.code()}")
//                            // Log de corpo da resposta (se necessário)
//                            Log.d(
//                                "PerfilUsuarioJogadorScreen",
//                                "Corpo da resposta: ${response.errorBody()?.string()}"
//                            )
//                        }
//                    }
//
//                    override fun onFailure(call: Call<ResponseGetListaJogadores>, t: Throwable) {
//                        // Trate o erro de falha na rede.
//                        Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
//                    }
//
//                })
//
//            } else{
//                Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
//
//            }
//        }

//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Spacer(modifier = Modifier.height(12.dp))
//
//            Button(onClick = {
////                GetListajogadoresFunction(
////                    sharedViewModelTokenEId = sharedViewModelTokenEId,
////                    sharedViewModelPerfilEditar = sharedViewModelPerfilEditar,
////                    sharedViewModelPerfilJogador = sharedViewModelPerfilJogador,
////                    sharedViewModelPerfilOrganizador = sharedViewModelPerfilOrganizador,
////                    sharedViewModelNomeJogadorListaJogadores = sharedViewModelNomeJogadorListaJogadores,
////                    sharedViewModelGetListaJogadores = sharedViewModelGetListaJogadores,
////                    sharedViewModelGetListaJogadoresList = sharedViewModelGetListaJogadoresList,
////                    sharedViewModelGetListaJogadoresInfoPerfil = sharedViewModelGetListaJogadoresInfoPerfil,
////                    sharedViewModelGetListaJogadoresTimeAtual = sharedViewModelGetListaJogadoresTimeAtual,
////                    sharedViewModelGetListaJogadoresDentroDeTime = sharedViewModelGetListaJogadoresDentroDeTime,
////                    sharedViewModelGetListaJogadoresDentroDeTimeList = sharedViewModelGetListaJogadoresDentroDeTimeList,
////                    sharedViewModelGetListaJogadoresPropostasList = sharedViewModelGetListaJogadoresPropostasList,
////                    sharedViewModelGetListaJogadoresPropostasRecebidas = sharedViewModelGetListaJogadoresPropostasRecebidas,
////                )
//
//
//
//                //Log.e("ID DE OUTRO JOGADOR 03", "aqui esta o id de outro jogador ${testeid.value}")
//
//
//
//
//            }
//            ) {
//
//
//            }
//
//            Spacer(modifier = Modifier.height(12.dp))
//
//        }
//
//        Spacer(modifier = Modifier.height(12.dp))

//        val playerListAAA = sharedViewModelGetListaJogadores.playerList
//
//        Log.e("Tamanho da Lista", "${playerListAAA?.size}")
//
//        LazyRow(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(80.dp)
//                .padding(start = 0.dp, top = 0.dp)
//            ,
//            verticalAlignment = Alignment.CenterVertically
//
//        ){
//            if (playerListAAA != null) {
//                items(playerListAAA.size) {index ->
//                    val playerDataList = playerListAAA[index]
//
//                    Log.e("ID JOGADORES NA ROW","${playerDataList.id}")
//
//                    listaIds.add(playerDataList.id)
//
//                    Log.e("ID JOGADORES NA ROW", "${playerDataList.id}")
//                    //jogos
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 20.dp, top = 20.dp)
//                        ,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Button(
//                            onClick = {
////                                val timeId = time.id // Obtenha o ID do time clicado
////                                val verificacao = true
////
////                                if (verificacao == true) {
////                                    verificarIdDoTime(sharedViewModelGetMyTeamsTime, sharedGetMyTeamsGeral, timeId)
////                                    sharedGetMyTeamsGeral.selectedTimeId = timeId
////                                    Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetMyTeamsGeral.selectedTimeId}")
////                                    onNavigate("perfil_time")
////                                }
//
//                            },
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .height(80.dp)
//                                .padding(start = 0.dp, top = 0.dp),
//                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
//                            colors = ButtonDefaults.buttonColors(RedProliseum)
//                        ) {
//                            Card(
//                                modifier = Modifier
//                                    .height(55.dp)
//                                    .width(55.dp),
//                                colors = CardDefaults.cardColors(RedProliseum)
//                            ) {
//                                Image(
//                                    painter =
//                                    if ("${playerDataList.jogo}" == "0") painterResource(
//                                        id = R.drawable.iconcsgo
//                                    )
//                                    else if ("${playerDataList.jogo}" == "1") painterResource(id = R.drawable.iconlol)
//                                    else if ("${playerDataList.jogo}" == "2") painterResource(id = R.drawable.iconvalorant)
//                                    else painter,
//                                    contentDescription = "",
//                                    modifier = Modifier.fillMaxSize(),
//                                    alignment = Alignment.Center,
//                                    colorFilter = ColorFilter.tint(AzulEscuroProliseum)
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(5.dp))
//
//                            Text(
//                                text = "${playerDataList.nickname}",
//                                color = Color.White,
//                                modifier = Modifier.padding(5.dp),
//                                fontWeight = FontWeight(600),
//                                fontFamily = customFontFamilyText,
//                                fontSize = 12.sp
//                            )
//                        }
//                    }
//                }
//            }
//        }











    }
}

