package br.senai.sp.jandira.proliseumtcc.gui.perfis

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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.graphics.ColorFilter
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
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.Notificacao
import br.senai.sp.jandira.proliseumtcc.model.NotificacaoProposta
import br.senai.sp.jandira.proliseumtcc.model.ResponseFirstGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoHighlightsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewNotificacaoProposta
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseFirstGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetRedeSocialDono
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
fun PerfilUsuarioPadraoScreen(
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

    sharedViewModelPerfilJogador: SharedViewModelPerfilJogador,
    sharedViewModelPerfilOrganizador: SharedViewModelPerfilOrganizador,

    sharedResponsePostRedeSocial: SharedResponsePostRedeSocial,
    sharedResponsePostRedeSocialDono: SharedResponsePostRedeSocialDono,
    sharedResponsePostRedeSocialDonoHighlights: SharedResponsePostRedeSocialDonoHighlights,
    sharedResponsePostRedeSocialDonoHighlightsDono: SharedResponsePostRedeSocialDonoHighlightsDono,
    sharedResponsePostRedeSocialDonoPropostas: SharedResponsePostRedeSocialDonoPropostas,
    sharedResponsePostRedeSocialDonoPropostasDe: SharedResponsePostRedeSocialDonoPropostasDe,
    sharedResponsePostRedeSocialDonoPropostasDeJogadores: SharedResponsePostRedeSocialDonoPropostasDeJogadores,
    sharedResponsePostRedeSocialDonoPropostasDePropostas: SharedResponsePostRedeSocialDonoPropostasDePropostas,
    sharedResponsePostRedeSocialDonoRedeSocial: SharedResponsePostRedeSocialDonoRedeSocial,

    sharedViewResponseFirstGetRedeSocial: SharedViewResponseFirstGetRedeSocial,
    sharedViewResponseGetRedeSocial: SharedViewResponseGetRedeSocial,
    sharedViewResponseGetRedeSocialDono: SharedViewResponseGetRedeSocialDono,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageOrgRef = remember { mutableStateOf<StorageReference?>(null) }
    val imageCapaRef = remember { mutableStateOf<StorageReference?>(null) }



    val idUser = sharedViewModelUser.id
    val nomeUser = sharedViewModelUser.nome_usuario
    val fullNomeUser = sharedViewModelUser.nome_completo
    val dataNascimentoUser = sharedViewModelUser.data_nascimento
    val emailUser = sharedViewModelUser.email
    val nickNameUser = sharedViewModelUser.nickname
    val biografiaUser = sharedViewModelUser.biografia
    val generoPerfilUser = sharedViewModelUser.genero

    val idUsuarioJogadorPerfilUser = sharedViewModelPlayerProfile.id
    val nickNamejogadorPerfilUser = sharedViewModelPlayerProfile.nickname
    val jogoJogadorPerfilUser = sharedViewModelPlayerProfile.jogo
    val funcaoJogadorPerfilUser = sharedViewModelPlayerProfile.funcao
    val eloJogadorPerfilUser = sharedViewModelPlayerProfile.elo

    val timeAtualPerfilUser = sharedViewModelPlayerProfile.time_atual


    val nomeTimeAtualUserPadrao = sharedViewModelPlayerProfileTimeAtual.nome_time


    val orgProfile = sharedViewModelPerfilOrganizador.orgProfile
    val nomeOrganizacao = sharedViewModelPerfilOrganizador.nome_organizacao
    val biografiaOrganizacao = sharedViewModelPerfilOrganizador.biografia

    val dadosJogador = sharedViewModelPerfil.playerProfile


    var redesSociaisList by remember {
        mutableStateOf(listOf<ResponseGetRedeSocial>())
    }

    val idDonoRedeSocial = sharedResponsePostRedeSocialDono.id

    val redeSocialService = RetrofitFactoryCadastro().getRedeSocialService()

    redeSocialService.getRedeSocial("Bearer " + token).enqueue(object :
        Callback<ResponseFirstGetRedeSocial> {
        override fun onResponse(call: Call<ResponseFirstGetRedeSocial>, response: Response<ResponseFirstGetRedeSocial>) {
            if (response.isSuccessful) {

                val listaRedesSociais = response.body()

                val RedesSociaisList = listaRedesSociais?.redeSocial


                sharedViewResponseFirstGetRedeSocial.redeSocial = RedesSociaisList



                if (RedesSociaisList != null) {
                    redesSociaisList = RedesSociaisList
                }

                if(RedesSociaisList != null){
                    for(redesSociais in RedesSociaisList){
                        val idRedeSocial = redesSociais.id
                        val linkRedeSocial = redesSociais.link
                        val tipoRedeSocial = redesSociais.tipo

                        sharedViewResponseGetRedeSocial.id = redesSociais.id
                        sharedViewResponseGetRedeSocial.link = redesSociais.link
                        sharedViewResponseGetRedeSocial.tipo = redesSociais.tipo


                        val donoRedeSocial = redesSociais.dono

                        sharedViewResponseGetRedeSocial.dono = redesSociais.dono


                        if(donoRedeSocial != null){
                            donoRedeSocial.id
                            sharedViewResponseGetRedeSocialDono.id = donoRedeSocial.id
                            sharedViewResponseGetRedeSocialDono.nome_usuario = donoRedeSocial.nome_usuario
                            sharedViewResponseGetRedeSocialDono.nome_completo = donoRedeSocial.nome_completo
                            sharedViewResponseGetRedeSocialDono.email = donoRedeSocial.email
                            sharedViewResponseGetRedeSocialDono.data_nascimento = donoRedeSocial.data_nascimento
                            sharedViewResponseGetRedeSocialDono.genero = donoRedeSocial.genero
                            sharedViewResponseGetRedeSocialDono.nickname = donoRedeSocial.nickname
                            sharedViewResponseGetRedeSocialDono.biografia = donoRedeSocial.biografia
                        }

                    }
                }

            } else {
                // Trate a resposta não bem-sucedida
                Log.d("LISTA REDE SOCIAIS CODE", "Resposta não bem-sucedida: ${response.code()}")
                // Log de corpo da resposta (se necessário)
                Log.d(
                    "BODY Lista Rede Sociais",
                    "Corpo da resposta: ${response.errorBody()?.string()}"
                )
            }
        }

        override fun onFailure(call: Call<ResponseFirstGetRedeSocial>, t: Throwable) {
            // Trate o erro de falha na rede.
            Log.d("NotificacaoScreen ERROR", "Erro de rede: ${t.message}")
        }

    })

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageRef.value = storage.reference.child("${idUser}/profile")
        }

        if (idUser != null && idUser != 0) {
            imageOrgRef.value = storage.reference.child("${idUser}/orgprofile")
        }

        if (idUser != null && idUser != 0) {
            imageCapaRef.value = storage.reference.child("${idUser}/capa")
        }

    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

    //    FIREBASE

    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var imageOrgUri by remember { mutableStateOf<Uri?>(null) }
    var imageCapaUri by remember { mutableStateOf<Uri?>(null) }





    if (imageOrgRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriOrg = imageOrgRef.value!!.downloadUrl.await()
                imageOrgUri = uriOrg

                Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriOrg}")

            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }

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

    if (imageCapaRef.value != null) { // Verifique a referência do Firebase
        LaunchedEffect(Unit) {
            try {
                val uriCapa = imageCapaRef.value!!.downloadUrl.await()
                imageCapaUri = uriCapa


                Log.e("URI CAPA DO USUARIO 02", "URI da imagem do usuario ${uriCapa}")
            } catch (e: Exception) {
                // Trate os erros, se houver algum
                Log.e("DEBUG", "Erro ao buscar imagem: $e")
            }
        }
    }


    // FIREBASE
    Log.e("URL IMAGEM DO USUARIO 03", "Id do URL da imagem do usuario ${idUser}")
    Log.e("URI IMAGEM DO USUARIO 03", "URI da imagem do usuario ${imageUri}")
    Log.e("URI CAPA DO USUARIO 03", "URI da imagem do usuario ${imageCapaUri}")


    Column {
        Text(text = "${nomeUser}")
        Text(text = "${emailUser}")
        Text(text = "${biografiaUser}")
        Text(text = "${generoPerfilUser}")
    }

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
                .fillMaxWidth()
                .height(200.dp)

        ) {

            if (idUser != null && idUser != 0) {
                // Exiba a imagem se a URI estiver definida
                AsyncImage(
                    model = imageCapaUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            } else {
                // Caso a URI não esteja definida, você pode mostrar uma mensagem ou um indicador de carregamento

            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                modifier = Modifier.clickable {
                    //rememberNavController.navigate("home")
                    onNavigate("home")
                                              },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Button(
                onClick = {
                    //rememberNavController.navigate("editar_perfil_jogador_part_1")
                    onNavigate("navegacao_configuracoes_meu_perfil_principal")
                },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {

                Text(
                    text = "GERENCIAR PERFIL",
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(600),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(3.dp))

                Icon(
                    painter = painterResource(id = R.drawable.editar_perfis_icon),
                    contentDescription = "Gerenciar",
                    modifier = Modifier.size(20.dp)
                )
            }
        }


        // Imagem Perfil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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

        Column(
            modifier = Modifier.padding(top = 250.dp),
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "${nickNameUser}",
                    fontSize = 28.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            if(dadosJogador != null){
                //jogos
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Card(
                        modifier = Modifier
                            .height(85.dp)
                            .width(85.dp),
                        colors = CardDefaults.cardColors(RedProliseum)
                    ) {
                        Image(
                            painter =
                            if ("${jogoJogadorPerfilUser}" == "0") painterResource(
                                id = R.drawable.iconlol
                            )
                            else if ("${jogoJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iconlol)
                            else if ("${jogoJogadorPerfilUser}" == "2") painterResource(id = R.drawable.iconlol)
                            else painter,
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.Center,
                            colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))

                    Card(
                        modifier = Modifier
                            .height(85.dp)
                            .width(85.dp),
                        colors = CardDefaults.cardColors(RedProliseum)
                    ) {
                        Image(
                            painter = if ("${funcaoJogadorPerfilUser}" == "0") painterResource(
                                id = R.drawable.icontoplane
                            )
                            else if ("${funcaoJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iconjungle)
                            else if ("${funcaoJogadorPerfilUser}" == "2") painterResource(id = R.drawable.iconmidlane)
                            else if ("${funcaoJogadorPerfilUser}" == "3") painterResource(id = R.drawable.iconsupport)
                            else if ("${funcaoJogadorPerfilUser}" == "4") painterResource(id = R.drawable.iconadc)
                            else painter,
                            contentDescription = "",
                            modifier = Modifier.fillMaxSize(),
                            alignment = Alignment.Center,
                            colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                        )
                    }
                }
            } else if(dadosJogador == null){
                Log.e("SEM PERFIL JOGADOR", "Sem dados de perfil de jogador ${dadosJogador}")
            }

            Spacer(modifier = Modifier.height(20.dp))

            if(redesSociaisList != null){

                val matchingRedesSociais = redesSociaisList.filter { it.dono?.id == idUser }

                if(matchingRedesSociais.isNotEmpty()){
                    LazyRow(
                        content = {
                            items(redesSociaisList.size){ index ->
                                val redesSociais = redesSociaisList[index]

                                Spacer(modifier = Modifier.width(5.dp))

                                if(
                                    redesSociais.dono?.id == idUser
                                ){
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .height(90.dp),
                                    ) {
                                        Box(
                                            contentAlignment = Alignment.TopEnd
                                        ) {
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(60.dp)
                                                    .padding(start = 0.dp, top = 0.dp),

                                                colors = CardColors(
                                                    containerColor = Color.Transparent,
                                                    contentColor = Color.Transparent,
                                                    disabledContainerColor = Color.Transparent,
                                                    disabledContentColor = Color.Transparent
                                                )
                                            ) {

                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth(),
                                                    horizontalArrangement = Arrangement.Center,
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {


                                                    Card(
                                                        modifier = Modifier
                                                            .height(60.dp)
                                                            .width(60.dp),
                                                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                                        colors = CardDefaults.cardColors(RedProliseum)
                                                    ) {
                                                        Image(
                                                            painter =
                                                            if ("${redesSociais.tipo}" == "0") painterResource(
                                                                id = R.drawable.discord_logo_branco
                                                            )
                                                            else if ("${redesSociais.tipo}" == "1") painterResource(id = R.drawable.twitterx_logo_branco)
                                                            else if ("${redesSociais.tipo}" == "2") painterResource(id = R.drawable.facebook_logo_branco)
                                                            else if ("${redesSociais.tipo}" == "3") painterResource(id = R.drawable.instagram_logo_branco)
                                                            else if ("${redesSociais.tipo}" == "4") painterResource(id = R.drawable.youtube_logo_branco)
                                                            else if ("${redesSociais.tipo}" == "5") painterResource(id = R.drawable.twitch_lgoo_branco)
                                                            else painter,
                                                            contentDescription = "",
                                                            modifier = Modifier.fillMaxSize(),

                                                            )
                                                    }

                                                    Spacer(modifier = Modifier.width(5.dp))

                                                    Text(
                                                        text = "${redesSociais.link}",
                                                        color = Color.White,
                                                        modifier = Modifier.padding(5.dp),
                                                        fontWeight = FontWeight(600),
                                                        fontFamily = customFontFamilyText,
                                                        fontSize = 22.sp
                                                    )


                                                }
                                            }
//                                        Icon(
//                                            painter = painterResource(id = R.drawable.close),
//                                            contentDescription = "Botão no canto superior direito",
//                                            modifier = Modifier
//                                                .clickable {
//                                                    deletarNotificacao(
//                                                        sharedViewModelTokenEId,
//                                                        sharedViewNotificacaoProposta
//                                                    )
//
//                                                    onNavigate("carregar_tela_notificacoes")
//
//
//                                                }
//                                                .size(30.dp),
//                                            tint = Color.White
//                                        )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(20.dp))
                                }
                            }
                        }
                    )
                } else {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    ){
                        Button(
                            onClick = {
                                onNavigate("criar_rede_social")

                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(start = 0.dp, top = 0.dp),
                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum),
                        ){
                            Text(
                                text = "Criar rede social",
                                color = Color.White,
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight(600),
                                fontFamily = customFontFamilyText,
                                fontSize = 18.sp
                            )
                        }
                    }
                }

            }

        }

        Column {

            Column(
                modifier = Modifier
                    .padding(top = 500.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                //Biografia
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                Brush
                                    .horizontalGradient(
                                        listOf(
                                            BlackTransparentProliseum,
                                            BlackTransparentProliseum
                                        )
                                    ), shape = RoundedCornerShape(16.dp)
                            )
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "${biografiaUser}",
                            fontSize = 16.sp,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(400),
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                // linha
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(Color.Red)
                )

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceAround,

                    )
                {
                    if(timeAtualPerfilUser != null){

                        val imageTimeRef = remember { mutableStateOf<StorageReference?>(null) }

                        if(idUser != null && idUser != 0){


                            val storage = Firebase.storage

                            if (idUser != null && idUser != 0) {
                                imageTimeRef.value = storage.reference.child("team/${idUser}/profile")
                            }

                        } else{
                            Log.e("TOKEN NULO", "Token do usuario esta nulo")
                            Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
                        }

                        var imageTimeUri by remember { mutableStateOf<Uri?>(null) }

                        if (imageTimeRef.value != null) { // Verifique a referência do Firebase
                            LaunchedEffect(Unit) {
                                try {
                                    val uriTime = imageTimeRef.value!!.downloadUrl.await()
                                    imageTimeUri = uriTime

                                    Log.e("URI IMAGEM DO USUARIO 02", "URI da imagem do usuario ${uriTime}")

                                } catch (e: Exception) {
                                    // Trate os erros, se houver algum
                                    Log.e("DEBUG", "Erro ao buscar imagem: $e")
                                }
                            }
                        }


                        Column() {

                            Column(

                            ) {
                                Text(
                                    text = "${nomeTimeAtualUserPadrao}",
                                    fontSize = 15.sp,
                                    color = Color.White,
                                    fontFamily = customFontFamilyText,
                                    fontWeight = FontWeight(900),
                                )

                                Spacer(modifier = Modifier.height( 5.dp))
                            }


                            Box(
                                contentAlignment = Alignment.BottomEnd
                            ) {



                                Card(
                                    modifier = Modifier
                                        .clickable {
                                            onNavigate("home")
                                        }
                                        .height(100.dp)
                                        .width(100.dp),

                                    shape = CircleShape
                                ) {

                                    if (idUser != null && idUser != 0) {
                                        // Exiba a imagem se a URI estiver definida
                                        AsyncImage(
                                            model = imageTimeUri,
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



                    } else if(timeAtualPerfilUser == null){
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.label_atualmente),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.brasao),
                                contentDescription = ""
                            )
                            Text(
                                text = stringResource(id = R.string.label_fa),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(400),
                            )
                        }
                    }

                    if(dadosJogador != null){
                        Column(
                            modifier = Modifier

                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.elo),
                                fontSize = 15.sp,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                            Image(
                                painter = if ("${eloJogadorPerfilUser}" == "0") painterResource(id = R.drawable.unranked_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "1") painterResource(id = R.drawable.iron_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "2") painterResource(id = R.drawable.bronze_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "3") painterResource(id = R.drawable.silver_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "4") painterResource(id = R.drawable.gold_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "5") painterResource(id = R.drawable.platinum_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "6") painterResource(id = R.drawable.emerald_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "7") painterResource(id = R.drawable.diamond_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "8") painterResource(id = R.drawable.master_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "9") painterResource(id = R.drawable.grandmaster_proliseum_elo)
                                else if ("${eloJogadorPerfilUser}" == "10") painterResource(id = R.drawable.challenger_proliseum_elo)
                                else painter,
                                contentDescription = "",
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    } else if(dadosJogador == null){
                        Log.e("SEM DADOS JOGADOR", "Sem dados do perfil de jogador para exibir Elo ${dadosJogador}")
                    }

//                        Column(
//                            modifier = Modifier
//                                .padding(10.dp),
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Text(
//                                text = stringResource(id = R.string.label_trofeu),
//                                fontSize = 15.sp,
//                                color = Color.White,
//                                fontFamily = customFontFamilyText,
//                                fontWeight = FontWeight(900),
//                            )
//                            Image(
//                                painter = painterResource(id = R.drawable.trofeu_padrao),
//                                contentDescription = "",
//                                modifier = Modifier.size(80.dp)
//                            )
//                        }



                }

                Spacer(modifier = Modifier.height(5.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(0.5.dp)
                        .background(Color.Red)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(
                        onClick = {

                            onNavigate("lista_meus_high_lights")

                        },
                        modifier = Modifier
                            .width(390.dp)
                            .height(50.dp)
                            .padding(start = 0.dp, top = 0.dp),
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum),
                    ){
                        Text(
                            text = "HIGHLIGHTS!",
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

            }
        }

    }
}


