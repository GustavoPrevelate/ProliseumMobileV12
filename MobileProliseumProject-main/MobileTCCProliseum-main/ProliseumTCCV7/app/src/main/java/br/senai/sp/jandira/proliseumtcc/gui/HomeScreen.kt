package br.senai.sp.jandira.proliseumtcc.gui

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.Manifest.permission.READ_MEDIA_IMAGES
import android.Manifest.permission.READ_MEDIA_VIDEO
import android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED
import android.net.Uri
import android.os.Build
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.BottomNavigationScreeen
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTime
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.model.AGetTimeFilterByUser
import br.senai.sp.jandira.proliseumtcc.model.AGetTimeFilterByUserTeams
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeamsPropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoTimes
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.cinzaBemClaro
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

@Composable
fun HomeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedViewModelPerfil: SharedViewModelPerfil,
    sharedViewModelUser: SharedViewModelUser,
    sharedViewModelPerfilPropostas: SharedViewModelPerfilPropostas,
    sharedViewModelPerfilPropostasDe: SharedViewModelPerfilPropostasDe,
    sharedViewModelPerfilPropostasDeJogadores: SharedViewModelPerfilPropostasDeJogadores,
    sharedViewModelPerfilPropostasDePropostas: SharedViewModelPerfilPropostasDePropostas,
    sharedViewModelPerfilPropostasDeRedeSocial: SharedViewModelPerfilPropostasDeRedeSocial,
    sharedViewModelPerfilPropostasDeHighlights: SharedViewModelPerfilPropostasDeHighlights,

    sharedViewModelPlayerProfile: SharedViewModelPlayerProfile,
    sharedViewModelPlayerProfileTimeAtual: SharedViewModelPlayerProfileTimeAtual,
    sharedViewModelPlayerProfileTimeAtualJogadores: SharedViewModelPlayerProfileTimeAtualJogadores,
    sharedViewModelPlayerProfileTimeAtualPropostas: SharedViewModelPlayerProfileTimeAtualPropostas,

    //SharedViewModel GET MY TIME ATUALIZADO
    sharedAGetMyTime: SharedAGetMyTime,
    sharedAGetMyTimeTime: SharedAGetMyTimeTime,
    sharedAGetMyTimeTimeJogadores: SharedAGetMyTimeTimeJogadores,
    sharedAGetMyTimeTimePropostas: SharedAGetMyTimeTimePropostas,

    sharedAGetMyTimeUser: SharedAGetMyTimeUser,
    sharedAGetMyTimeUserHighlights: SharedAGetMyTimeUserHighlights,
    sharedAGetMyTimeUserPropostas: SharedAGetMyTimeUserPropostas,
    sharedAGetMyTimeUserRedeSocial: SharedAGetMyTimeUserRedeSocial,

    sharedViewModelListaPublicacaoTimes: SharedViewModelListaPublicacaoTimes,

    sharedGetTimeListaPostagens: SharedGetTimeListaPostagens,
    sharedGetTimeListaPostagensPublicacao: SharedGetTimeListaPostagensPublicacao,
    sharedGetTimeListaPostagensPublicacaoDonoId: SharedGetTimeListaPostagensPublicacaoDonoId,
    sharedGetTimeListaPostagensPublicacaoTime: SharedGetTimeListaPostagensPublicacaoTime,
    sharedGetTimeListaPostagensPublicacaoTimeJogadores: SharedGetTimeListaPostagensPublicacaoTimeJogadores,
    sharedGetTimeListaPostagensPublicacaoTimePropostas: SharedGetTimeListaPostagensPublicacaoTimePropostas,

    // SharedViewModel LISTA POSTAGEM JOGADORES

    sharedViewModelListaPublicacaoJogadores: SharedViewModelListaPublicacaoJogadores,

    sharedGetListaPostagens: SharedGetListaPostagens,
    sharedGetListaPostagensPublicacao: SharedGetListaPostagensPublicacao,
    sharedGetListaPostagensPublicacaoDonoId: SharedGetListaPostagensPublicacaoDonoId,

    //SharedViewModel GET MINHA POSTAGEM
    sharedGetMinhaPostagem: SharedGetMinhaPostagem,
    sharedGetMinhaPostagemUser: SharedGetMinhaPostagemUser,
    sharedGetMinhaPostagemUserPropostas: SharedGetMinhaPostagemUserPropostas,
    sharedGetMinhaPostagemPostProfile: SharedGetMinhaPostagemPostProfile,

    sharedAGetTimeFilterByUser: SharedAGetTimeFilterByUser,
    sharedAGetTimeFilterByUserTeams: SharedAGetTimeFilterByUserTeams,
    sharedAGetTimeFilterByUserTeamsJogadores: SharedAGetTimeFilterByUserTeamsJogadores,
    sharedAGetTimeFilterByUserTeamsPropostas: SharedAGetTimeFilterByUserTeamsPropostas,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token
    val idUser = sharedViewModelUser.id

    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var selectedNavigation by remember { mutableStateOf<Int?>(null) }

    val idUserPerfilForImage = sharedViewModelTokenEId.idUsuario

    val storage = Firebase.storage

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }

    if (idUserPerfilForImage != null && idUserPerfilForImage != 0) {
        imageRef.value = storage.reference.child("${idUserPerfilForImage}/profile")
    }

    Log.d("ID PERFIL USUARIO", "ID DO PERFIL DO USUARIO ${idUserPerfilForImage}")

    //    FIREBASE
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    if (imageRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uri = imageRef.value!!.downloadUrl.await()
                imageUri = uri

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uri}")
            } catch (e: Exception) {

                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

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

    var validacaoMyTimeFiltersByUser by remember {
        mutableStateOf(listOf<AGetTimeFilterByUserTeams>())
    }

    val aGetFilterTimeByUserService = RetrofitFactoryCadastro().aGetFilterTimesByUserService()

    aGetFilterTimeByUserService.getFilterTimesByUser("Bearer " + token, idUser).enqueue(object : Callback<AGetTimeFilterByUser> {
        override fun onResponse(call: Call<AGetTimeFilterByUser>, response: Response<AGetTimeFilterByUser>) {
            if (response.isSuccessful) {
                val aGetTimeFilterByUser = response.body()

                val teamsAGetTimeFilterByUser = aGetTimeFilterByUser?.teams

                if(teamsAGetTimeFilterByUser != null){
                    validacaoMyTimeFiltersByUser = teamsAGetTimeFilterByUser
                }

                sharedAGetTimeFilterByUser.teams = aGetTimeFilterByUser?.teams

                if(teamsAGetTimeFilterByUser != null){
                    for(listTeamsAGetTimeFilterByUser in teamsAGetTimeFilterByUser){
                        val idListTeamsAGetTimeFilterByUser = listTeamsAGetTimeFilterByUser.id
                        val nomeTimeListTeamsAGetTimeFilterByUser = listTeamsAGetTimeFilterByUser.nome_time
                        val jogoListTeamsAGetTimeFilterByUser = listTeamsAGetTimeFilterByUser.jogo
                        val biografiaListTeamsAGetTimeFilterByUser = listTeamsAGetTimeFilterByUser.biografia

                        sharedAGetTimeFilterByUserTeams.id = listTeamsAGetTimeFilterByUser.id
                        sharedAGetTimeFilterByUserTeams.nome_time = listTeamsAGetTimeFilterByUser.nome_time
                        sharedAGetTimeFilterByUserTeams.jogo = listTeamsAGetTimeFilterByUser.jogo
                        sharedAGetTimeFilterByUserTeams.biografia = listTeamsAGetTimeFilterByUser.biografia


                        val jogadoresListTeamsAGetTimeFilterByUser = listTeamsAGetTimeFilterByUser.jogadores
                        val propostasListTeamsAGetTimeFilterByUser = listTeamsAGetTimeFilterByUser.propostas

                        sharedAGetTimeFilterByUserTeams.jogadores = listTeamsAGetTimeFilterByUser.jogadores
                        sharedAGetTimeFilterByUserTeams.propostas = listTeamsAGetTimeFilterByUser.propostas

                        if(jogadoresListTeamsAGetTimeFilterByUser != null){
                            for (listJogadoresListTeamsAGetTimeFilterByUser in jogadoresListTeamsAGetTimeFilterByUser){
                                val idListJogadoresListTeamsAGetTimeFilterByUser = listJogadoresListTeamsAGetTimeFilterByUser.id
                                val nicknameListJogadoresListTeamsAGetTimeFilterByUser = listJogadoresListTeamsAGetTimeFilterByUser.nickname
                                val jogoListJogadoresListTeamsAGetTimeFilterByUser = listJogadoresListTeamsAGetTimeFilterByUser.jogo
                                val funcaoListJogadoresListTeamsAGetTimeFilterByUser = listJogadoresListTeamsAGetTimeFilterByUser.funcao
                                val eloListJogadoresListTeamsAGetTimeFilterByUser = listJogadoresListTeamsAGetTimeFilterByUser.elo

                                sharedAGetTimeFilterByUserTeamsJogadores.id = listJogadoresListTeamsAGetTimeFilterByUser.id
                                sharedAGetTimeFilterByUserTeamsJogadores.nickname = listJogadoresListTeamsAGetTimeFilterByUser.nickname
                                sharedAGetTimeFilterByUserTeamsJogadores.jogo = listJogadoresListTeamsAGetTimeFilterByUser.jogo
                                sharedAGetTimeFilterByUserTeamsJogadores.funcao = listJogadoresListTeamsAGetTimeFilterByUser.funcao
                                sharedAGetTimeFilterByUserTeamsJogadores.elo = listJogadoresListTeamsAGetTimeFilterByUser.elo
                            }
                        }

                        if(propostasListTeamsAGetTimeFilterByUser != null){
                            for(listPropostasListTeamsAGetTimeFilterByUser in propostasListTeamsAGetTimeFilterByUser){
                                val idListPropostasListTeamsAGetTimeFilterByUser = listPropostasListTeamsAGetTimeFilterByUser.id
                                val menssagemListPropostasListTeamsAGetTimeFilterByUser = listPropostasListTeamsAGetTimeFilterByUser.menssagem

                                sharedAGetTimeFilterByUserTeamsPropostas.id = listPropostasListTeamsAGetTimeFilterByUser.id
                                sharedAGetTimeFilterByUserTeamsPropostas.menssagem = listPropostasListTeamsAGetTimeFilterByUser.menssagem

                            }
                        }
                    }
                }


            }

        }

        override fun onFailure(call: Call<AGetTimeFilterByUser>, t: Throwable) {
            Log.e("CarregarInformacoesPerfilOrganizacaoScreen", "Erro na chamada da API: ${t.message}")
        }


    })



    val verificarSeUsuarioPossuiTimeAtualizado = validacaoMyTimeFiltersByUser

    val verificarSeUsuarioTemPerfilDeJogador = sharedViewModelPerfil.playerProfile

    Log.e("OLHA A VERIFICACAO","VERIFICACAO PARA VER SE O CARA POSSUI TIME ${verificarSeUsuarioPossuiTimeAtualizado}")



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(AzulEscuroProliseum, AzulEscuroProliseum),
                    startY = 700f,
                    endY = 1200f
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            //rememberNavController.navigate("navigation_proliseum")
                            onNavigate("navigation_proliseum")
                        },
                    painter = painterResource(id = R.drawable.menu),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color(255, 255, 255, 255)
                )

                Spacer(modifier = Modifier.padding(150.dp))

                Box(contentAlignment = Alignment.BottomEnd) {
                    Card(
                        modifier = Modifier
                            .height(40.dp)
                            .width(40.dp),
                        shape = CircleShape
                    ) {
                    if (idUserPerfilForImage != null && idUserPerfilForImage != 0) {

                    // Exiba a imagem no formato circular
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                //rememberNavController.navigate("perfil_usuario_jogador")
                                onNavigate("perfil_usuario_jogador")
                            }
                            .background(Color.Gray, CircleShape) // Define um shape circular
                    ) {
                        AsyncImage(
                            model = imageUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }

                } else {
                    // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento
                    Text("Carregando imagem...")
                }
                    }
                }
            }

        }

        if(verificarSeUsuarioTemPerfilDeJogador == null && verificarSeUsuarioPossuiTimeAtualizado.isEmpty()){
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .width(390.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    BlackTransparentProliseum, BlackTransparentProliseum
                                )
                            )
                        )
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "SEJA UM JOGADOR",
                                fontFamily = customFontFamily,
                                fontWeight = FontWeight(900),
                                fontSize = 32.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White

                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "Para procura por time é necessario criar um perfil de jogador",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White

                            )
                        }

                        Button(
                            onClick = {
                                onNavigate("cadastro_usuario_jogador")
                            },
                            modifier = Modifier
                                .height(48.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "CRIE UM PERFIL AQUI!",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }

                    }

                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "OU",
                        fontFamily = customFontFamily,
                        fontWeight = FontWeight(900),
                        fontSize = 48.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White

                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .height(250.dp)
                        .width(390.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    BlackTransparentProliseum, BlackTransparentProliseum
                                )
                            )
                        )
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "CRIE UM TIME",
                                fontFamily = customFontFamily,
                                fontWeight = FontWeight(900),
                                fontSize = 32.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White

                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "Criando um time, você poderá buscar por jogadores que desejar",
                                fontWeight = FontWeight(600),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White

                            )
                        }

                        Button(
                            onClick = {
                                onNavigate("criar_time")
                            },
                            modifier = Modifier
                                .height(48.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "CRIE UM TIME AQUI!",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }

                    }

                }
            }

        } else {



            if(verificarSeUsuarioTemPerfilDeJogador != null){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 70.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    Box(
                        modifier = Modifier
                            .height(280.dp)
                            .width(390.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        BlackTransparentProliseum, BlackTransparentProliseum
                                    )
                                )
                            )
                    ) {

                        var publicacoesTimes by remember {
                            mutableStateOf(listOf<GetTimePostagemListPublicacao>())
                        }

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

                        val listaIdsPerfisTimes = remember { mutableListOf<Int>() }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(cinzaBemClaro)
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ){
                            Text(
                                text = "ALGUMAS VAGAS NO SEU PERFIL:",
                                fontWeight = FontWeight(900),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White

                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = {
                                    onNavigate("lista_times")
                                },
                                modifier = Modifier
                                    .height(48.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(RedProliseum)

                            ) {
                                Text(
                                    text = "VEJA MAIS",
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontFamily = customFontFamilyText,
                                    fontWeight = FontWeight(900),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier.padding(top = 60.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
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
                                                .height(450.dp)
                                                .padding(top = 20.dp),
                                        ) {
                                            Button(
                                                onClick = {

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

                                                    }

                                                    Spacer(modifier = Modifier.height(10.dp))


                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(200.dp),
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




                                                }

                                            }
                                        }
                                    }
                                }


                            )


                        }





                    }
                }
            }
            //////////////////////////////////////////////
            //////////////////////////////////////////////
            //////////////////////////////////////////////
            //////////////////////////////////////////////
            //////////////////////////////////////////////
            //////////////////////////////////////////////

            if(verificarSeUsuarioPossuiTimeAtualizado.isNotEmpty()){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 270.dp)
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Box(
                        modifier = Modifier
                            .height(280.dp)
                            .width(390.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        BlackTransparentProliseum, BlackTransparentProliseum
                                    )
                                )
                            )
                    ) {

                        var publicacoesJogadores by remember {
                            mutableStateOf(listOf<GetPostagemListPublicacao>())
                        }

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



                            val getListaPublicacoesJogadores = RetrofitFactoryCadastro().getPostagemListService()

                            getListaPublicacoesJogadores.postagemJogadorService(tipo, perPage, page, hora, elo, funcao).enqueue(object :
                                Callback<GetPostagemList> {
                                override fun onResponse(call: Call<GetPostagemList>, response: Response<GetPostagemList>) {
                                    if (response.isSuccessful) {
                                        Log.d("SUCESSO LISTA POSTAGENS", "Resposta bem-sucedida na lista de postagens: ${response.code()}")

                                        val listaPublicacoesJogadoresResponse = response.body()

                                        Log.d("BODY LISTA POSTAGENS", "Resposta bem-sucedida na lista de postagens: ${response.body()}")

                                        val publicacoesList = listaPublicacoesJogadoresResponse?.post

                                        if (publicacoesList != null) {
                                            publicacoesJogadores = publicacoesList
                                        }

                                        sharedGetListaPostagens.post = publicacoesList

                                        if(publicacoesList != null){
                                            for(listPublicacoes in publicacoesList){
                                                val idPublicao = listPublicacoes.id
                                                val descricaoPublicao = listPublicacoes.descricao
                                                val jogoPublicao = listPublicacoes.jogo
                                                val funcaoPublicao = listPublicacoes.funcao
                                                val eloPublicao = listPublicacoes.elo
                                                val horaPublicao = listPublicacoes.hora
                                                val tipoPublicao = listPublicacoes.tipo
                                                val prosPublicao = listPublicacoes.pros

                                                sharedGetListaPostagensPublicacao.id = listPublicacoes.id
                                                sharedGetListaPostagensPublicacao.descricao = listPublicacoes.descricao
                                                sharedGetListaPostagensPublicacao.jogo = listPublicacoes.jogo
                                                sharedGetListaPostagensPublicacao.funcao = listPublicacoes.funcao
                                                sharedGetListaPostagensPublicacao.elo = listPublicacoes.elo
                                                sharedGetListaPostagensPublicacao.hora = listPublicacoes.hora
                                                sharedGetListaPostagensPublicacao.tipo = listPublicacoes.tipo
                                                sharedGetListaPostagensPublicacao.pros = listPublicacoes.pros





                                                val listaInformacoesDonoId = listPublicacoes.dono_id

                                                sharedGetListaPostagensPublicacao.dono_id = listaInformacoesDonoId

                                                if(listaInformacoesDonoId != null){
                                                    val idlistaInformacoesDonoId = listaInformacoesDonoId.id
                                                    val nomeUsuariolistaInformacoesDonoId = listaInformacoesDonoId.nome_usuario
                                                    val nomeCompletolistaInformacoesDonoId = listaInformacoesDonoId.nome_completo
                                                    val emaillistaInformacoesDonoId = listaInformacoesDonoId.email
                                                    val dataNascimentolistaInformacoesDonoId = listaInformacoesDonoId.data_nascimento
                                                    val generolistaInformacoesDonoId = listaInformacoesDonoId.genero
                                                    val nicknamelistaInformacoesDonoId = listaInformacoesDonoId.nickname
                                                    val biografialistaInformacoesDonoId = listaInformacoesDonoId.biografia

                                                    sharedGetListaPostagensPublicacaoDonoId.id = listaInformacoesDonoId.id
                                                    sharedGetListaPostagensPublicacaoDonoId.nome_usuario = listaInformacoesDonoId.nome_usuario
                                                    sharedGetListaPostagensPublicacaoDonoId.nome_completo = listaInformacoesDonoId.nome_completo
                                                    sharedGetListaPostagensPublicacaoDonoId.email = listaInformacoesDonoId.email
                                                    sharedGetListaPostagensPublicacaoDonoId.data_nascimento = listaInformacoesDonoId.data_nascimento
                                                    sharedGetListaPostagensPublicacaoDonoId.genero = listaInformacoesDonoId.genero
                                                    sharedGetListaPostagensPublicacaoDonoId.nickname = listaInformacoesDonoId.nickname
                                                    sharedGetListaPostagensPublicacaoDonoId.biografia = listaInformacoesDonoId.biografia
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

                                override fun onFailure(call: Call<GetPostagemList>, t: Throwable) {
                                    // Trate o erro de falha na rede.
                                    Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
                                }

                            })

                        } else{
                            Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")

                        }

                        val listaIdsPerfisJogadores = remember { mutableListOf<Int>() }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(cinzaBemClaro)
                            ,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Top
                        ){
                            Text(
                                text = "ALGUNS JOGADORES DISPONIVEIS:",
                                fontWeight = FontWeight(900),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White

                            )

                            Spacer(modifier = Modifier.height(20.dp))

                            Button(
                                onClick = {
                                    onNavigate("lista_de_jogadores")
                                },
                                modifier = Modifier
                                    .height(48.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(RedProliseum)

                            ) {
                                Text(
                                    text = "VEJA MAIS",
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    fontFamily = customFontFamilyText,
                                    fontWeight = FontWeight(900),
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Column(
                            modifier = Modifier.padding(top = 60.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {


                            Spacer(modifier = Modifier.height(20.dp))

                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = 20.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                content = {
                                    items(publicacoesJogadores.size){ index ->
                                        val infoPublicacao = publicacoesJogadores[index]

                                        val donoDaPublicacao = infoPublicacao.dono_id

                                        val idInfoPublicacao = infoPublicacao?.id ?: 0
                                        val descricaoInfoPublicacao = infoPublicacao?.descricao ?: ""
                                        val jogoInfoPublicacao = infoPublicacao?.jogo ?: ""
                                        val funcaoInfoPublicacao = infoPublicacao?.funcao ?: ""
                                        val eloInfoPublicacao = infoPublicacao?.elo ?: ""
                                        val horaInfoPublicacao = infoPublicacao?.hora ?: 0
                                        val tipoInfoPublicacao = infoPublicacao?.tipo ?: false
                                        val prosInfoPublicacao = infoPublicacao?.pros ?: ""


                                        val idDonoPublicacaoJogador = donoDaPublicacao?.id ?: 0
                                        val nomeUsuarioDonoPublicacaoJogador = donoDaPublicacao?.nome_usuario ?: ""
                                        val nomeCompletoDonoPublicacaoJogador = donoDaPublicacao?.nome_completo ?: ""
                                        val emailDonoPublicacaoJogador = donoDaPublicacao?.email ?: ""
                                        val dataNascimentoDonoPublicacaoJogador = donoDaPublicacao?.data_nascimento ?: ""
                                        val generoDonoPublicacaoJogador = donoDaPublicacao?.genero ?: 0
                                        val nicknameDonoPublicacaoJogador = donoDaPublicacao?.nickname ?: ""
                                        val biografiaDonoPublicacaoJogador = donoDaPublicacao?.biografia ?: ""


                                        listaIdsPerfisJogadores.add(idDonoPublicacaoJogador)

                                        val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                                        if(idDonoPublicacaoJogador != null && idDonoPublicacaoJogador != 0){


                                            val storage = Firebase.storage

                                            if (publicacoesJogadores != null && publicacoesJogadores.isNotEmpty()) {

                                                if (idDonoPublicacaoJogador != null && idDonoPublicacaoJogador != 0) {
                                                    // Utilize o ID do perfil aqui
                                                    imageRef.value = storage.reference.child("$idDonoPublicacaoJogador/profile")
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
                                                            .height(80.dp)
                                                            .fillMaxWidth()
                                                    ){


                                                        Text(
                                                            text = "${infoPublicacao.descricao}",
                                                            color = Color.White,
                                                            modifier = Modifier.padding(5.dp),
                                                            fontWeight = FontWeight(600),
                                                            fontFamily = customFontFamilyText,
                                                            fontSize = 14.sp
                                                        )

                                                    }

                                                    Spacer(modifier = Modifier.height(10.dp))


                                                    Row(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(200.dp),
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
                                                                    if ("${infoPublicacao.elo}" == "0") painterResource(id = R.drawable.unranked_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "1") painterResource(id = R.drawable.iron_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "2") painterResource(id = R.drawable.bronze_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "3") painterResource(id = R.drawable.silver_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "4") painterResource(id = R.drawable.gold_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "5") painterResource(id = R.drawable.platinum_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "6") painterResource(id = R.drawable.emerald_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "7") painterResource(id = R.drawable.diamond_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "8") painterResource(id = R.drawable.master_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "9") painterResource(id = R.drawable.grandmaster_proliseum_elo)
                                                                    else if ("${infoPublicacao.elo}" == "10") painterResource(id = R.drawable.challenger_proliseum_elo)
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
                                                                    if ("${infoPublicacao.funcao}" == "0") painterResource(
                                                                        id = R.drawable.icontoplane
                                                                    )
                                                                    else if ("${infoPublicacao.funcao}" == "1") painterResource(id = R.drawable.iconjungle)
                                                                    else if ("${infoPublicacao.funcao}" == "2") painterResource(id = R.drawable.iconmidlane)
                                                                    else if ("${infoPublicacao.funcao}" == "3") painterResource(id = R.drawable.iconsupport)
                                                                    else if ("${infoPublicacao.funcao}" == "4") painterResource(id = R.drawable.iconadc)
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
                                                                text = "${infoPublicacao.hora}",
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
                                                                text = "${infoPublicacao.pros}",
                                                                color = Color.White,
                                                                modifier = Modifier.padding(5.dp),
                                                                fontWeight = FontWeight(600),
                                                                fontFamily = customFontFamilyText,
                                                                fontSize = 14.sp
                                                            )
                                                        }

                                                    }




                                                }

                                            }
                                        }
                                    }
                                }
                            )
                        }


                    }



                }
            }





        }



        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom)
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(3.dp)
                        .background(
                            RedProliseum,
                            shape = RoundedCornerShape(70.dp, 70.dp, 70.dp, 70.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly) {}

                Spacer(modifier = Modifier.height(5.dp))

                Row {
                    BottomNavigationScreeen(onNavigate) { navigation ->
                        selectedNavigation = navigation
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAposLoginScreenPreview() {
    ProliseumTCCTheme{

    }
}