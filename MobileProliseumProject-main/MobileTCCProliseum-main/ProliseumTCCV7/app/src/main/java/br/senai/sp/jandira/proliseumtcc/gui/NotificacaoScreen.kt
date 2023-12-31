package br.senai.sp.jandira.proliseumtcc.gui

import android.net.Uri
import android.util.Log
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
import androidx.compose.material3.CardColors
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
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewNotificacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewNotificacaoProposta
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NotificacaoScreen(
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

    sharedViewNotificacao: SharedViewNotificacao,
    sharedViewNotificacaoProposta: SharedViewNotificacaoProposta,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token
    Log.d("PerfilUsuarioJogadorScreen", "Token: $token")

    val idUser = sharedViewModelUser.id

    var contador by remember { mutableStateOf(0) }



    val imageRef = remember { mutableStateOf<StorageReference?>(null) }

    val nickNameUser = sharedViewModelUser.nickname

    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    if(idUser != null && idUser != 0){


        val storage = Firebase.storage

        if (idUser != null && idUser != 0) {
            imageRef.value = storage.reference.child("${idUser}/profile")
        }


    } else{
        Log.e("TOKEN NULO", "Token do usuario esta nulo")
        Log.e("ERRO", "As informaçoes do usuario nao foram carregadas")
    }

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

    val qualquerCoisa = ""

    var notificacaoProposta by remember {
        mutableStateOf(listOf<NotificacaoProposta>())
    }


    val notificacaoService = RetrofitFactoryCadastro().notificacaoService()

    notificacaoService.getNotificacao("Bearer " + token).enqueue(object :
        Callback<Notificacao> {
        override fun onResponse(call: Call<Notificacao>, response: Response<Notificacao>) {
            if (response.isSuccessful) {

                val listaNotificacoes = response.body()

                val notificacoesList = listaNotificacoes?.notifications

                sharedViewNotificacao.notifications = notificacoesList

                if (notificacoesList != null) {
                    notificacaoProposta = notificacoesList
                }

                if(notificacoesList != null){
                    for(notificacao in notificacoesList){
                        val idNotificacao = notificacao.id
                        val menssagemNotificacao = notificacao.menssagem
                        val tituloNotificacao = notificacao.titulo

                        sharedViewNotificacaoProposta.id = notificacao.id
                        sharedViewNotificacaoProposta.menssagem = notificacao.menssagem
                        sharedViewNotificacaoProposta.titulo = notificacao.titulo

                    }
                }

            } else {
                // Trate a resposta não bem-sucedida
                Log.d("NotificacaoScreen CODE", "Resposta não bem-sucedida: ${response.code()}")
                // Log de corpo da resposta (se necessário)
                Log.d(
                    "NotificacaoScreen BODY",
                    "Corpo da resposta: ${response.errorBody()?.string()}"
                )
            }
        }

        override fun onFailure(call: Call<Notificacao>, t: Throwable) {
            // Trate o erro de falha na rede.
            Log.d("NotificacaoScreen ERROR", "Erro de rede: ${t.message}")
        }

    })


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
                        onNavigate("home")
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
                            .height(50.dp)
                            .width(50.dp),

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

                Spacer(modifier = Modifier.padding(5.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.5.dp)
                    .background(Color.Red)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                content = {
                    items(notificacaoProposta.size){ index ->
                        val infoNotificacao = notificacaoProposta[index]

                        val idNotificacao = infoNotificacao?.id ?: 0
                        val menssagemNotificacao = infoNotificacao?.menssagem ?: ""
                        val tituloNotificacao = infoNotificacao?.titulo ?: ""

                        fun deletarNotificacao(
                            sharedViewModelTokenEId: SharedViewTokenEId,
                            sharedViewNotificacaoProposta: SharedViewNotificacaoProposta,
                        ){

                            val token = sharedViewModelTokenEId.token


                            val deleteNotificacaoService = RetrofitFactoryCadastro().deletarNotificacaoService()

                            deleteNotificacaoService.deleteNotificacao("Bearer " + token, infoNotificacao.id).enqueue(object :
                                Callback<GenericResponse> {
                                override fun onResponse(call: Call<GenericResponse>, response: Response<GenericResponse>) {
                                    if (response.isSuccessful) {

                                        val notificacaoDeletadaBody = response.body()

                                        val notificacaoDeletada = notificacaoDeletadaBody?.response

                                        Log.d("NOTIFICAÇÃO!", "Notificação deletada? ${notificacaoDeletada}")



                                    } else {
                                        // Trate a resposta não bem-sucedida
                                        Log.d("NotificacaoScreen CODE", "Resposta não bem-sucedida: ${response.code()}")
                                        // Log de corpo da resposta (se necessário)
                                        Log.d(
                                            "NotificacaoScreen BODY",
                                            "Corpo da resposta: ${response.errorBody()?.string()}"
                                        )
                                    }
                                }

                                override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                                    // Trate o erro de falha na rede.
                                    Log.d("NotificacaoScreen ERROR", "Erro de rede: ${t.message}")
                                }

                            })
                        }


                        //jogos
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(150.dp)
                                .padding(top = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                contentAlignment = Alignment.TopEnd
                            ) {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(150.dp)
                                    .padding(start = 0.dp, top = 0.dp),
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                colors = CardColors(
                                    containerColor = Color.Black,
                                    contentColor = Color.Black,
                                    disabledContainerColor = Color.White,
                                    disabledContentColor = Color.White
                                )
                            ) {

                                    // Conteúdo do seu Card aqui

                                    // Box para o ícone no canto superior direito





                                Column(
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    Spacer(modifier = Modifier.height(5.dp))

//                                Row(
//                                    modifier = Modifier
//                                        .height(70.dp)
//                                ) {
//                                    Text(
//                                        text = "${infoNotificacao.id}",
//                                        color = Color.White,
//                                        modifier = Modifier.padding(5.dp),
//                                        fontWeight = FontWeight(600),
//                                        fontFamily = customFontFamilyText,
//                                        fontSize = 14.sp
//                                    )
//                                }

                                    Spacer(modifier = Modifier.height(5.dp))

                                    Row(
                                        modifier = Modifier
                                            .height(50.dp)
                                    ) {
                                        Text(
                                            text = "${infoNotificacao.titulo}",
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(600),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 22.sp
                                        )
                                    }

                                    Row(
                                        modifier = Modifier
                                            .height(70.dp)
                                    ) {
                                        Text(
                                            text = "${infoNotificacao.menssagem}",
                                            color = Color.White,
                                            modifier = Modifier.padding(5.dp),
                                            fontWeight = FontWeight(600),
                                            fontFamily = customFontFamilyText,
                                            fontSize = 16.sp
                                        )
                                    }


                                }
                            }
                                Icon(
                                    painter = painterResource(id = R.drawable.close),
                                    contentDescription = "Botão no canto superior direito",
                                    modifier = Modifier
                                        .clickable {
                                            deletarNotificacao(
                                                sharedViewModelTokenEId,
                                                sharedViewNotificacaoProposta
                                            )

                                            onNavigate("carregar_tela_notificacoes")


                                        }
                                        .size(30.dp),
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            )

        }
    }
}
