package br.senai.sp.jandira.proliseumtcc.gui.postagem

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
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
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
fun MinhaPostagemScreen(
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

    //SharedViewModel GET MINHA POSTAGEM
    sharedGetMinhaPostagem: SharedGetMinhaPostagem,
    sharedGetMinhaPostagemUser: SharedGetMinhaPostagemUser,
    sharedGetMinhaPostagemUserPropostas: SharedGetMinhaPostagemUserPropostas,
    sharedGetMinhaPostagemPostProfile: SharedGetMinhaPostagemPostProfile,
    onNavigate: (String) -> Unit
) {
    val token = sharedViewModelTokenEId.token
    Log.d("TESTE TOKEN", "Token: $token")

    val idUsuario = sharedViewModelUser.id

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

    val idUser = sharedGetMinhaPostagemUser.id
    val nomeUsuarioUser = sharedGetMinhaPostagemUser.nome_usuario
    val nomeCompletoUser = sharedGetMinhaPostagemUser.nome_completo
    val emailUser = sharedGetMinhaPostagemUser.email
    val dataNascimentoUser = sharedGetMinhaPostagemUser.data_nascimento
    val generoUser = sharedGetMinhaPostagemUser.genero
    val nicknameUser = sharedGetMinhaPostagemUser.nickname
    val biografiaUser = sharedGetMinhaPostagemUser.biografia

    val idMinhaPublicacao = sharedGetMinhaPostagemPostProfile.id
    val descricaoMinhaPublicacao = sharedGetMinhaPostagemPostProfile.descricao
    val jogoMinhaPublicacao = sharedGetMinhaPostagemPostProfile.jogo
    val funcaoMinhaPublicacao = sharedGetMinhaPostagemPostProfile.funcao
    val eloMinhaPublicacao = sharedGetMinhaPostagemPostProfile.elo
    val horaMinhaPublicacao = sharedGetMinhaPostagemPostProfile.hora
    val tipoMinhaPublicacao = sharedGetMinhaPostagemPostProfile.tipo
    val prosMinhaPublicacao = sharedGetMinhaPostagemPostProfile.pros


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
                    onNavigate("lista_de_publicacoes_jogadores")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "MINHA PUBLICACAO",
                fontFamily = customFontFamilyText,
                fontSize = 25.sp,
                fontWeight = FontWeight(900),
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(70.dp))

        Column(
            modifier = Modifier
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val imageRef = remember { mutableStateOf<StorageReference?>(null) }

            if(idUsuario != null && idUsuario != 0){


                val storage = Firebase.storage

                if (idUsuario != null && idUsuario != 0) {
                    // Utilize o ID do perfil aqui
                    imageRef.value = storage.reference.child("$idUsuario/profile")
                } else {
                    Log.e("ID DO PERFIL INVÁLIDO", "O ID do perfil é nulo ou igual a zero")
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
                    .height(650.dp)
                    .padding(top = 20.dp),
            ) {
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
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

                                    if (idUsuario != null && idUsuario != 0) {
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


                        Spacer(modifier = Modifier.height(10.dp))

                        Column(
                            modifier = Modifier
                                .height(80.dp)
                                .fillMaxWidth()
                        ){


                            Text(
                                text = "${descricaoMinhaPublicacao}",
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
                                        if ("${eloMinhaPublicacao}" == "0") painterResource(id = R.drawable.unranked_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "1") painterResource(id = R.drawable.iron_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "2") painterResource(id = R.drawable.bronze_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "3") painterResource(id = R.drawable.silver_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "4") painterResource(id = R.drawable.gold_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "5") painterResource(id = R.drawable.platinum_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "6") painterResource(id = R.drawable.emerald_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "7") painterResource(id = R.drawable.diamond_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "8") painterResource(id = R.drawable.master_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "9") painterResource(id = R.drawable.grandmaster_proliseum_elo)
                                        else if ("${eloMinhaPublicacao}" == "10") painterResource(id = R.drawable.challenger_proliseum_elo)
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
                                        if ("${funcaoMinhaPublicacao}" == "0") painterResource(
                                            id = R.drawable.icontoplane
                                        )
                                        else if ("${funcaoMinhaPublicacao}" == "1") painterResource(id = R.drawable.iconjungle)
                                        else if ("${funcaoMinhaPublicacao}" == "2") painterResource(id = R.drawable.iconmidlane)
                                        else if ("${funcaoMinhaPublicacao}" == "3") painterResource(id = R.drawable.iconsupport)
                                        else if ("${funcaoMinhaPublicacao}" == "4") painterResource(id = R.drawable.iconadc)
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
                                    text = "${horaMinhaPublicacao}",
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
                                    text = "${prosMinhaPublicacao}",
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = {
                                onNavigate("editar_minha_publicacao_jogador")
                            },
                            modifier = Modifier
                                .width(170.dp)
                                .height(70.dp),
                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum),
                        ) {
                            Text(
                                text = "EDITAR",
                                color = Color.White,
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight(600),
                                fontFamily = customFontFamilyText,
                                fontSize = 20.sp
                            )
                        }
                    }


                    Spacer(modifier = Modifier.width(25.dp))

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        Button(
                            onClick = {
                                deletarPublicacaoJogador(
                                    sharedViewModelTokenEId = sharedViewModelTokenEId
                                )

                                onNavigate("navigation_proliseum")
                            },
                            modifier = Modifier
                                .width(170.dp)
                                .height(70.dp),
                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum),
                        ) {
                            Text(
                                text = "APAGAR",
                                color = Color.White,
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight(600),
                                fontFamily = customFontFamilyText,
                                fontSize = 20.sp
                            )
                        }
                    }

                }


                Row(
                    modifier = Modifier.fillMaxWidth().height(80.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            onNavigate("lista_propostas_recebidas_para_jogadores")
                        },
                        modifier = Modifier
                            .width(370.dp)
                            .height(70.dp),
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum),
                    ) {
                        Text(
                            text = "PROPOSTAS RECEBIDAS",
                            color = Color.White,
                            modifier = Modifier.padding(5.dp),
                            fontWeight = FontWeight(600),
                            fontFamily = customFontFamilyText,
                            fontSize = 20.sp
                        )
                    }
                }

            }
        }
    }
}

fun deletarPublicacaoJogador(
    sharedViewModelTokenEId: SharedViewTokenEId
){

    val token = sharedViewModelTokenEId.token

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val apagarPublicacaoJogadorService = RetrofitFactoryCadastro().apagarPublicacaoJogadorService()

    // Realize a chamada de API para editar o perfil
    apagarPublicacaoJogadorService.deletarMinhaPostagem("Bearer " + token)
        .enqueue(object : Callback<GenericResponse> {
            override fun onResponse(
                call: Call<GenericResponse>,
                response: Response<GenericResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d(
                        "DeletarPublicacaoJogadorScreen",
                        "DeletarPublicacaoJogadorScreen, Publicação apagada com sucesso: ${response.code()}"
                    )
                    // Trate a resposta bem-sucedida, se necessário
                } else {
                    // Trate a resposta não bem-sucedida
                    Log.d(
                        "DeletarPublicacaoJogadorScreen",
                        "DeletarPublicacaoJogadorScreen, Falha ao tentar apagar a publição do jogador: ${response.code()}"
                    )
                    // Log do corpo da resposta (se necessário)
                    Log.d(
                        "DeletarPublicacaoJogadorScreen",
                        "DeletarPublicacaoJogadorScreen, Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("DeletarPublicacaoJogadorScreen", "Erro de rede: ${t.message}")
            }
        })
}