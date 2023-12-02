package br.senai.sp.jandira.proliseumtcc.gui.minhas_redes_sociais

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
fun DeletarRedeSocialScreen(
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

    var redesSociaisList by remember {
        mutableStateOf(listOf<ResponseGetRedeSocial>())
    }

    val token = sharedViewModelTokenEId.token

    val idUser = sharedViewModelUser.id

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

    val nickNameUser = sharedViewModelUser.nickname

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

    val imageRef = remember { mutableStateOf<StorageReference?>(null) }

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageRef.value = storage.reference.child("${idUser}/profile")
        }

    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

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
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("home")
                        onNavigate("navegacao_configuracoes_meu_perfil_principal")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.padding(5.dp))

                Button(
                    onClick = {
                        onNavigate("perfil_usuario_jogador")
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                )
                {
                    Card(
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp),

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

                    Spacer(modifier = Modifier.padding(10.dp))

                    Text(
                        text = "${nickNameUser}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight(600),
                        color = Color.White
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.5.dp)
                    .background(Color.Red)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize(),
            ) {

            if(redesSociaisList != null){

                val matchingRedesSociais = redesSociaisList.filter { it.dono?.id == idUser }

                if(matchingRedesSociais.isNotEmpty()){
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.Start,
                        content = {
                            items(redesSociaisList.size){ index ->
                                val redesSociais = redesSociaisList[index]

                                fun deletarRedeSocialFunction(
                                    sharedViewModelTokenEId: SharedViewTokenEId,
                                ){

                                    val token = sharedViewModelTokenEId.token


                                    val deleteRedeSocialService = RetrofitFactoryCadastro().deletarRedeSocialService()

                                    deleteRedeSocialService.deleteRedeSocial("Bearer " + token, redesSociais.id)
                                        .enqueue(object : Callback<GenericResponse> {
                                            override fun onResponse(call: Call<GenericResponse>, response: Response<GenericResponse>) {
                                                if (response.isSuccessful) {

                                                    val notificacaoDeletadaBody = response.body()

                                                    val notificacaoDeletada = notificacaoDeletadaBody?.response

                                                    Log.d("REDE SOCIAL!", "REDE SOCIAL deletada? ${notificacaoDeletada}")



                                                } else {
                                                    // Trate a resposta não bem-sucedida
                                                    Log.d("RedeSocialScreen CODE", "Resposta não bem-sucedida: ${response.code()}")
                                                    // Log de corpo da resposta (se necessário)
                                                    Log.d(
                                                        "RedeSocialScreen BODY",
                                                        "Corpo da resposta: ${response.errorBody()?.string()}"
                                                    )
                                                }
                                            }

                                            override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                                                // Trate o erro de falha na rede.
                                                Log.d("RedeSocialScreen ERROR", "Erro de rede: ${t.message}")
                                            }

                                        })
                                }


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
                                                    containerColor = Color.Black,
                                                    contentColor = Color.Black,
                                                    disabledContainerColor = Color.Transparent,
                                                    disabledContentColor = Color.Transparent
                                                )
                                            ) {

                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth(),
//                                                    horizontalArrangement = Arrangement.Center,
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
                                                        fontSize = 16.sp
                                                    )


                                                }
                                            }
                                            Icon(
                                                painter = painterResource(id = R.drawable.close),
                                                contentDescription = "Botão no canto superior direito",
                                                modifier = Modifier
                                                    .clickable {
                                                        deletarRedeSocialFunction(
                                                            sharedViewModelTokenEId
                                                        )

                                                        onNavigate("carregar_tela_redes_sociais")


                                                    }
                                                    .size(30.dp),
                                                tint = Color.White
                                            )

                                        }
                                    }

                                    Spacer(modifier = Modifier.width(20.dp))
                                }
                            }
                        }
                    )
                }
            } }
        }
    }
}