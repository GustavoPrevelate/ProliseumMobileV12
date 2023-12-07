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
import br.senai.sp.jandira.proliseumtcc.gui.postagem.deletarPublicacaoJogador
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
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
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun ListaDePropostasRecebidasParaJogadoresScreen(
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

    sharedPropostasRecebidas: SharedPropostasRecebidas,
    sharedPropostasRecebidasGeral: SharedPropostasRecebidasGeral,
    sharedPropostasRecebidasGeralDe: SharedPropostasRecebidasGeralDe,
    sharedPropostasRecebidasGeralDeJogadores: SharedPropostasRecebidasGeralDeJogadores,
    sharedPropostasRecebidasGeralDePropostas: SharedPropostasRecebidasGeralDePropostas,
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

    var propostasJogadores by remember {
        mutableStateOf(listOf<PropostasRecebidasGeral>())
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


        val getPropostasRecebidasService = RetrofitFactoryCadastro().getPropostasRecebidasService()

        getPropostasRecebidasService.getPropostasRecebidas("Bearer " + token).enqueue(object :
            Callback<PropostasRecebidas> {
            override fun onResponse(call: Call<PropostasRecebidas>, response: Response<PropostasRecebidas>) {
                if (response.isSuccessful) {
                    Log.d("SUCESSO LISTA PROPOSTAS RECEBIDAS", "Resposta bem-sucedida na lista de propostas recebidas: ${response.code()}")

                    val listaPropostasRecebidas = response.body()

                    Log.d("BODY PROPOSTAS RECEBIDAS", "Resposta bem-sucedida na lista de propostas recebidas: ${response.body()}")

                    val propostasList = listaPropostasRecebidas?.propostas

                    if (propostasList != null) {
                        propostasJogadores = propostasList
                    }

                    sharedPropostasRecebidas.propostas = propostasList

                    if(propostasList != null){
                        for(listPropostasRecebidas in propostasList){
                            val idListaPropostasParaPublicacoesJogadores = listPropostasRecebidas.id
                            val menssagemListaPropostasParaPublicacoesJogadores = listPropostasRecebidas.menssagem

                            sharedPropostasRecebidasGeral.id = listPropostasRecebidas.id
                            sharedPropostasRecebidasGeral.menssagem = listPropostasRecebidas.menssagem

                            val deListaPropostasParaPublicacoesJogadores = listPropostasRecebidas.de

                            sharedPropostasRecebidasGeral.de = listPropostasRecebidas.de

                            if(deListaPropostasParaPublicacoesJogadores != null){
                                val idDeInfoListaDePropostasParaPulicacoesJogadores = deListaPropostasParaPublicacoesJogadores.id
                                val nomeTimeDeInfoListaDePropostasParaPulicacoesJogadores = deListaPropostasParaPublicacoesJogadores.nome_time
                                val jogoDeInfoListaDePropostasParaPulicacoesJogadores = deListaPropostasParaPublicacoesJogadores.jogo
                                val biografiaDeInfoListaDePropostasParaPulicacoesJogadores = deListaPropostasParaPublicacoesJogadores.biografia

                                sharedPropostasRecebidasGeralDe.id = deListaPropostasParaPublicacoesJogadores.id
                                sharedPropostasRecebidasGeralDe.nome_time = deListaPropostasParaPublicacoesJogadores.nome_time
                                sharedPropostasRecebidasGeralDe.jogo= deListaPropostasParaPublicacoesJogadores.jogo
                                sharedPropostasRecebidasGeralDe.biografia = deListaPropostasParaPublicacoesJogadores.biografia

                                val jogadoresDeInfoListaDePropostasParaPulicacoesJogadores = deListaPropostasParaPublicacoesJogadores.jogadores
                                val propostasDeInfoListaDePropostasParaPulicacoesJogadores = deListaPropostasParaPublicacoesJogadores.propostas

                                sharedPropostasRecebidasGeralDe.jogadores = deListaPropostasParaPublicacoesJogadores.jogadores
                                sharedPropostasRecebidasGeralDe.propostas = deListaPropostasParaPublicacoesJogadores.propostas

                                if(jogadoresDeInfoListaDePropostasParaPulicacoesJogadores != null){
                                    for(listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores in jogadoresDeInfoListaDePropostasParaPulicacoesJogadores){
                                        val idlistaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.id
                                        val nicknamelistaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.nickname
                                        val jogolistaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.jogo
                                        val funcaolistaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.funcao
                                        val elolistaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.elo

                                        sharedPropostasRecebidasGeralDeJogadores.id = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.id
                                        sharedPropostasRecebidasGeralDeJogadores.nickname = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.nickname
                                        sharedPropostasRecebidasGeralDeJogadores.jogo = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.jogo
                                        sharedPropostasRecebidasGeralDeJogadores.funcao = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.funcao
                                        sharedPropostasRecebidasGeralDeJogadores.elo = listaJogadoresDeInfoListaDePropostasParaPulicacoesJogadores.elo
                                    }
                                }

                                if(propostasDeInfoListaDePropostasParaPulicacoesJogadores != null){
                                    for( listaPropostasDeInfoListaDePropostasParaPulicacoesJogadores in propostasDeInfoListaDePropostasParaPulicacoesJogadores){
                                        val idListaPropostasDeInfoListaDePropostasParaPulicacoesJogadores = listaPropostasDeInfoListaDePropostasParaPulicacoesJogadores.id
                                        val menssagemListaPropostasDeInfoListaDePropostasParaPulicacoesJogadores = listaPropostasDeInfoListaDePropostasParaPulicacoesJogadores.menssagem

                                        sharedPropostasRecebidasGeralDePropostas.id = listaPropostasDeInfoListaDePropostasParaPulicacoesJogadores.id
                                        sharedPropostasRecebidasGeralDePropostas.menssagem = listaPropostasDeInfoListaDePropostasParaPulicacoesJogadores.menssagem
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

            override fun onFailure(call: Call<PropostasRecebidas>, t: Throwable) {
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
                    onNavigate("minha_postagem")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "PROPOSTAS RECEBIDAS",
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
                    items(propostasJogadores.size){ index ->
                        val infoPropostasRecebidas = propostasJogadores[index]

                        val idDaProposta = infoPropostasRecebidas.id
                        val menssagemDaProposta = infoPropostasRecebidas.menssagem
                        val donoDaProposta = infoPropostasRecebidas.de


                        val idPropostasRecebidas = donoDaProposta?.id ?: 0
                        val nomeTimePropostasRecebidas = donoDaProposta?.nome_time ?: ""
                        val jogoPropostasRecebidas = donoDaProposta?.jogo ?: 0
                        val biografiaPropostasRecebidas = donoDaProposta?.biografia ?: ""


                        listaIdsPerfisJogadores.add(idPropostasRecebidas)

                        val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                        if(idPropostasRecebidas != null && idPropostasRecebidas != 0){


                            val storage = Firebase.storage

                            if (propostasJogadores != null && propostasJogadores.isNotEmpty()) {

                                if (idPropostasRecebidas != null && idPropostasRecebidas != 0) {
                                    // Utilize o ID do perfil aqui
                                    imageRef.value = storage.reference.child("team/$idPropostasRecebidas/profile")
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
                                .height(450.dp)
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
                                    .height(550.dp)
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
                                        ) {
                                            if (donoDaProposta != null) {
                                                Text(
                                                    text = "${donoDaProposta.nome_time}",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 22.sp
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(20.dp))

                                        Text(
                                            text = "${infoPropostasRecebidas.menssagem}",
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(600),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 14.sp
                                        )

                                        Spacer(modifier = Modifier.height(20.dp))


                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(50.dp),
                                        ) {
                                            Button(
                                                onClick = {
                                                    //onNavigate("editar_minha_publicacao_jogador")
                                                },
                                                modifier = Modifier
                                                    .width(170.dp),
                                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                                colors = ButtonDefaults.buttonColors(RedProliseum),
                                            ) {
                                                Text(
                                                    text = "ACEITAR PROPOSTA",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 8.sp
                                                )
                                            }


                                            Spacer(modifier = Modifier.width(25.dp))

                                            Button(
                                                onClick = {

                                                    //onNavigate("navigation_proliseum")
                                                },
                                                modifier = Modifier
                                                    .width(170.dp),
                                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                                colors = ButtonDefaults.buttonColors(RedProliseum),
                                            ) {
                                                Text(
                                                    text = "RECUSAR PROPOSTA",
                                                    color = Color.White,
                                                    modifier = Modifier.padding(5.dp),
                                                    fontWeight = FontWeight(600),
                                                    fontFamily = customFontFamilyText,
                                                    fontSize = 8.sp
                                                )
                                            }

                                        }

                                    }

                                    Spacer(modifier = Modifier.height(20.dp))










                                }

                            }
                        }
                    }
                }
            )
        }
    }

}