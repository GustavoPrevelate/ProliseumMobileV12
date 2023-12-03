package br.senai.sp.jandira.proliseumtcc.gui.meus_highlights

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.ResponseFirstGetHighLights
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetHighLights
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacaoDonoId
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseFirstGetHighLights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetHighLights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetHighLightsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.outroBlackTransparente
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

@Composable
fun ListaMeusHighLightsScreen(
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


    sharedViewModelListaPublicacaoJogadores: SharedViewModelListaPublicacaoJogadores,

    sharedGetListaPostagens: SharedGetListaPostagens,
    sharedGetListaPostagensPublicacao: SharedGetListaPostagensPublicacao,
    sharedGetListaPostagensPublicacaoDonoId: SharedGetListaPostagensPublicacaoDonoId,

    sharedViewResponseFirstGetHighLights: SharedViewResponseFirstGetHighLights,
    sharedViewResponseGetHighLights: SharedViewResponseGetHighLights,
    sharedViewResponseGetHighLightsDono: SharedViewResponseGetHighLightsDono,
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

    var highLights by remember {
        mutableStateOf(listOf<ResponseGetHighLights>())
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
        val tipo = 0


        sharedViewModelListaPublicacaoJogadores.perPage = perPage
        sharedViewModelListaPublicacaoJogadores.page = page
        sharedViewModelListaPublicacaoJogadores.hora = hora
        sharedViewModelListaPublicacaoJogadores.elo = elo
        sharedViewModelListaPublicacaoJogadores.funcao = funcao
        sharedViewModelListaPublicacaoJogadores.tipo = tipo



        val getHighlightsService = RetrofitFactoryCadastro().getHighLightsService()

        getHighlightsService.getHighLights("Bearer " + token).enqueue(object :
            Callback<ResponseFirstGetHighLights> {
            override fun onResponse(call: Call<ResponseFirstGetHighLights>, response: Response<ResponseFirstGetHighLights>) {
                if (response.isSuccessful) {
                    Log.d("SUCESSO LISTA HIGHLIGHTS", "Resposta bem-sucedida na lista de highlights: ${response.code()}")

                    val listaHighLightsResponse = response.body()

                    Log.d("BODY LISTA HIGHLIGHTS", "Resposta bem-sucedida na lista de highlights: ${response.body()}")

                    val highLightsList = listaHighLightsResponse?.highlight

                    if (highLightsList != null) {
                        highLights = highLightsList
                    }

                    sharedViewResponseFirstGetHighLights.highlight = highLightsList

                    if(highLightsList != null){
                        for(listHighLights in highLightsList){

                            val idHighLight = listHighLights.id
                            val tituloHighLight = listHighLights.titulo

                            sharedViewResponseGetHighLights.id = listHighLights.id
                            sharedViewResponseGetHighLights.titulo = listHighLights.titulo

                            val donoHighLight = listHighLights.dono

                            sharedViewResponseGetHighLights.dono = listHighLights.dono

                            if(donoHighLight != null){
                                val idDonoHighLights = donoHighLight.id
                                val nomeUsuarioDonoHighLights = donoHighLight.nome_usuario
                                val nomeCompletoDonoHighLights = donoHighLight.nome_completo
                                val emailDonoHighLights = donoHighLight.email
                                val dataNascimentoDonoHighLights = donoHighLight.data_nascimento
                                val generoDonoHighLights = donoHighLight.genero
                                val nickNameDonoHighLights = donoHighLight.nickname
                                val biografiaDonoHighLights = donoHighLight.biografia


                                sharedViewResponseGetHighLightsDono.id = donoHighLight.id
                                sharedViewResponseGetHighLightsDono.nome_usuario = donoHighLight.nome_usuario
                                sharedViewResponseGetHighLightsDono.nome_completo = donoHighLight.nome_completo
                                sharedViewResponseGetHighLightsDono.email = donoHighLight.email
                                sharedViewResponseGetHighLightsDono.data_nascimento = donoHighLight.data_nascimento
                                sharedViewResponseGetHighLightsDono.genero = donoHighLight.genero
                                sharedViewResponseGetHighLightsDono.nickname = donoHighLight.nickname
                                sharedViewResponseGetHighLightsDono.biografia = donoHighLight.biografia
                            }
                        }
                    }

                } else {
                    // Trate a resposta não bem-sucedida
                    Log.d("HighLightsScreen", "Resposta não bem-sucedida: ${response.code()}")
                    // Log de corpo da resposta (se necessário)
                    Log.d(
                        "HighLightsScreen",
                        "Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseFirstGetHighLights>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("HighLightsScreen", "Erro de rede: ${t.message}")
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

        Row(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        ) {
            Icon(
                modifier = Modifier.clickable {
                    //rememberNavController.navigate("home")
                    onNavigate("perfil_usuario_jogador")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "HIGHLIGHTS",
                fontFamily = customFontFamilyText,
                fontSize = 25.sp,
                fontWeight = FontWeight(900),
                color = Color.White
            )
        }




        val listaIdsPerfisDonosHightLights = remember { mutableListOf<Int>() }

        Column(
            modifier = Modifier
                .padding(top = 60.dp)
            ,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        onNavigate("home")
                    },
                    modifier = Modifier
                        .width(250.dp)
                        .height(50.dp)
                        .padding(start = 0.dp, top = 0.dp),
                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                    colors = ButtonDefaults.buttonColors(RedProliseum),
                ) {
                    Text(
                        text = "CRIAR HIGHLIGHT",
                        color = Color.White,
                        modifier = Modifier.padding(5.dp),
                        fontWeight = FontWeight(600),
                        fontFamily = customFontFamilyText,
                        fontSize = 22.sp
                    )
                }


            }

            Spacer(modifier = Modifier.height(20.dp))

            if(highLights != null){

                val matchingHighLights = highLights.filter { it.dono?.id == idUser }

                if(matchingHighLights.isNotEmpty()){

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            items(highLights.size){ index ->
                                val infoHighLights = highLights[index]

                                val donoDaPublicacao = infoHighLights.dono

                                val idInfoHighLights = infoHighLights?.id ?: 0
                                val tituloInfoHighLights = infoHighLights?.titulo ?: ""


                                val idDonoHighLights = donoDaPublicacao?.id ?: 0
                                val nomeUsuarioDonoHighLights = donoDaPublicacao?.nome_usuario ?: ""
                                val nomeCompletoDonoHighLights = donoDaPublicacao?.nome_completo ?: ""
                                val emailDonoHighLights = donoDaPublicacao?.email ?: ""
                                val dataNascimentoDonoHighLights = donoDaPublicacao?.data_nascimento ?: ""
                                val generoDonoHighLights = donoDaPublicacao?.genero ?: 0
                                val nicknameDonoHighLights = donoDaPublicacao?.nickname ?: ""
                                val biografiaDonoHighLights = donoDaPublicacao?.biografia ?: ""


                                listaIdsPerfisDonosHightLights.add(idInfoHighLights)

                                val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                                if(idDonoHighLights != null && idDonoHighLights != 0){


                                    val storage = Firebase.storage

                                    if (highLights != null && highLights.isNotEmpty()) {

                                        if (idInfoHighLights != null && idInfoHighLights != 0) {
                                            // Utilize o ID do perfil aqui
                                            imageRef.value = storage.reference.child("post/$idInfoHighLights")
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


                                if(
                                    infoHighLights.dono?.id == idUser
                                ){
                                    //jogos
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .height(450.dp)
                                    ) {

                                        Spacer(modifier = Modifier.height(20.dp))

                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ){
                                            Text(
                                                text = "${infoHighLights.titulo}",
                                                color = Color.White,
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight(600),
                                                fontFamily = customFontFamilyText,
                                                fontSize = 22.sp
                                            )

                                            Spacer(modifier = Modifier.width(20.dp))

                                            Image(

                                                painter = painterResource(id = R.drawable.pincel_edit),
                                                contentDescription = stringResource(id = R.string.button_sair),
                                                modifier = Modifier
                                                    .clickable {
                                                        sharedViewResponseGetHighLights.id = idInfoHighLights
                                                        sharedViewResponseGetHighLights.titulo = tituloInfoHighLights

                                                        onNavigate("editar_high_lights")
                                                    }
                                                    .size(30.dp)
                                            )


                                        }

                                        Spacer(modifier = Modifier.height(5.dp))


                                        Box(

                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(550.dp)
                                                .padding(start = 0.dp, top = 0.dp)
                                                .background(
                                                    BlackTransparentProliseum,
                                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                                                )
                                        ) {

                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(start = 20.dp ,top = 20.dp, end = 20.dp, bottom = 20.dp),
                                            ) {




                                                Box(contentAlignment = Alignment.BottomEnd) {
                                                    Card(
                                                        modifier = Modifier
                                                            .fillMaxSize(),
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
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(30.dp))
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}