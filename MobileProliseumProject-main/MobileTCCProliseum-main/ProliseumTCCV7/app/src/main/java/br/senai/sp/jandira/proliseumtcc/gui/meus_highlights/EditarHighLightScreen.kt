package br.senai.sp.jandira.proliseumtcc.gui.meus_highlights

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.components.Genero
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonGeneroUI
import br.senai.sp.jandira.proliseumtcc.firebase.StorageHightLightUtil
import br.senai.sp.jandira.proliseumtcc.firebase.StorageUtil
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.DateInputSamplePerfilUser
import br.senai.sp.jandira.proliseumtcc.model.BodyTituloHighLight
import br.senai.sp.jandira.proliseumtcc.model.EditarPerfilUsuario
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.ResponseFirstGetHighLights
import br.senai.sp.jandira.proliseumtcc.model.ResponseHighLight
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewNotificacaoProposta
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseFirstGetHighLights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetHighLights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetHighLightsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarHighLightScreen(
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

    sharedViewModelImageUri: SharedViewModelImageUri,

    sharedViewResponseFirstGetHighLights: SharedViewResponseFirstGetHighLights,
    sharedViewResponseGetHighLights: SharedViewResponseGetHighLights,
    sharedViewResponseGetHighLightsDono: SharedViewResponseGetHighLightsDono,
    onNavigate: (String) -> Unit
) {

    //EDITAR FOTO DE PERFIL JOGADOR
    var editarFotoPerfilJogadorUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var launcherEditarFotoPerfilJogador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        editarFotoPerfilJogadorUri = it
    }
    var painterFotoPerfilJogador = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(editarFotoPerfilJogadorUri)
            .build()
    )


    //EDITAR FOTO DE CAPA DE PERFIL JOGADOR
    var editarFotoCapaPerfilJogadorUri by remember {
        mutableStateOf<Uri?>(null)
    }
    var launcherEditarFotoCapaPerfilJogador = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        editarFotoCapaPerfilJogadorUri = it
    }
    var painterEditarFotoCapaPerfilJogador = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(editarFotoCapaPerfilJogadorUri)
            .build()
    )

    //FONTE E CONTEXTO
    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val context = LocalContext.current


    var idHighLightState by remember { mutableStateOf(sharedViewResponseGetHighLights.id) }
    var tituloHighLight by remember { mutableStateOf(sharedViewResponseGetHighLights.titulo) }

    var idUserSharedState by remember { mutableStateOf(sharedViewModelUser.id) }
    var userNameUserSharedState by remember { mutableStateOf(sharedViewModelUser.nome_usuario) }
    var fullNameSharedState by remember { mutableStateOf(sharedViewModelUser.nome_completo) }
    var dataNascimentoSharedState by remember { mutableStateOf(sharedViewModelUser.data_nascimento) }
    var generoUserSharedState by remember { mutableStateOf<Int?>(sharedViewModelUser.genero) }
    var nickNameUserSharedState by remember { mutableStateOf(sharedViewModelUser.nickname) }
    var biografiaUserSharedState by remember { mutableStateOf(sharedViewModelUser.biografia) }

    var selectedGeneroUser by remember { mutableStateOf<Genero?>(null) }


    LaunchedEffect(sharedViewModelUser, sharedViewResponseGetHighLights) {

        // Esta parte só será executada quando o composable for inicializado
        idUserSharedState = sharedViewModelUser.id
        userNameUserSharedState = sharedViewModelUser.nome_usuario
        fullNameSharedState = sharedViewModelUser.nome_completo
        dataNascimentoSharedState = sharedViewModelUser.data_nascimento
        generoUserSharedState = sharedViewModelUser.genero
        nickNameUserSharedState = sharedViewModelUser.nickname
        biografiaUserSharedState = sharedViewModelUser.biografia

        idHighLightState = sharedViewResponseGetHighLights.id
        tituloHighLight = sharedViewResponseGetHighLights.titulo
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    //FOTO DE PERFIL

    var uri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    var painterPhotoPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uri)
            .build()
    )

    //FOTO CAPA DE PERFIL
    var uriCapa by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoCapaPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uriCapa = it
        }
    )

    var painterPhotoCapaPerfil = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(uriCapa)
            .build()
    )

    val uriImage = sharedViewModelImageUri.imageUri
    val uriImageCapa = sharedViewModelImageUri.imageCapaUri


    val outroContexto = LocalContext.current

    // DESIGN DA TELA
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum,
                        AzulEscuroProliseum
                    )
                )
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("perfil_usuario_jogador")
                        onNavigate("navegacao_configuracoes_meu_perfil_principal")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color(255, 255, 255, 255)
                )
            }

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Text(
                text = "CONFIGURAR HIGHLIGHT",
                fontFamily = customFontFamily,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 190.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 30.dp),
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        tituloHighLight?.let {
                            OutlinedTextField(
                                value = it,
                                onValueChange = { newTituloHighLight ->
                                    tituloHighLight = newTituloHighLight
                                },
                                modifier = Modifier

                                    .width(350.dp),
                                shape = RoundedCornerShape(16.dp),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                label = {
                                    Text(
                                        text = "Titulo do Highlight:",
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

                    Column(
                        modifier = Modifier
                            .padding(start = 30.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "FOTO HIGHLIGHT:",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    //FOTO HIGHLIGHT
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Box(contentAlignment = Alignment.BottomEnd) {

                            Card(
                                modifier = Modifier
                                    .height(200.dp)
                                    .width(350.dp)
                                    .clickable {
                                        singlePhotoCapaPicker.launch(
                                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                        )
                                        var message = "nada"
                                        Log.i(
                                            "PROLISEUM",
                                            "URI: ${uriCapa?.path ?: message} "
                                        )
                                    },
                                shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp)
                            ) {
                                Image(
                                    painter =
                                    if (uriCapa == null)
                                        painterResource(id = R.drawable.capa_perfil_usuario)
                                    else painterPhotoCapaPerfil,
                                    contentDescription = "",
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            Icon(
                                painter = painterResource(id = R.drawable.add_circle),
                                contentDescription = "",
                                tint = RedProliseum
                            )

                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ){

                        Spacer(modifier = Modifier.width(10.dp))

                        fun deletarHighlight(
                            sharedViewModelTokenEId: SharedViewTokenEId,
                        ){

                            val token = sharedViewModelTokenEId.token


                            val deletarHighLightService = RetrofitFactoryCadastro().deletarHighLightService()

                            deletarHighLightService.deletarHighlight("Bearer " + token, idHighLightState).enqueue(object :
                                Callback<GenericResponse> {
                                override fun onResponse(call: Call<GenericResponse>, response: Response<GenericResponse>) {
                                    if (response.isSuccessful) {

                                        val highlightDeletadoBody = response.body()

                                        val highLightDeletado = highlightDeletadoBody?.response

                                        Log.d("HIGHLIGHT!", "HIGHLIGHT deletado? ${highLightDeletado}")



                                    } else {
                                        // Trate a resposta não bem-sucedida
                                        Log.d("HighlightScreen CODE", "Resposta não bem-sucedida: ${response.code()}")
                                        // Log de corpo da resposta (se necessário)
                                        Log.d(
                                            "HighlightScreen BODY",
                                            "Corpo da resposta: ${response.errorBody()?.string()}"
                                        )
                                    }
                                }

                                override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                                    // Trate o erro de falha na rede.
                                    Log.d("HighlightScreen ERROR", "Erro de rede: ${t.message}")
                                }

                            })
                        }

                        Button(
                            onClick = {

                                for (i in 1..2) {
                                    tituloHighLight?.let {
                                        idHighLightState?.let { it1 ->
                                            AtualizarHighLight(
                                                sharedViewModelTokenEId = sharedViewModelTokenEId,
                                                sharedViewResponseGetHighLights = sharedViewResponseGetHighLights,
                                                tituloHighlight = it,
                                                id = it1
                                            )
                                        }
                                    }


                                    Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
                                    onNavigate("carregar_informacoes_perfil_usuario")

                                    if (idHighLightState != null && idHighLightState != 0) {
                                        uri?.let {
                                            StorageHightLightUtil.uploadToHighLightStorage(
                                                uri = it,
                                                context = outroContexto,
                                                type = "post",
                                                id = "${idHighLightState}"
                                            )
                                        }

                                        Log.i(
                                            "URI IMAGEM 06",
                                            "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uri}"
                                        )

                                        uriCapa?.let {
                                            StorageHightLightUtil.uploadToHighLightStorage(
                                                uri = it,
                                                context = outroContexto,
                                                type = "post",
                                                id = "${idHighLightState}"
                                            )
                                        }

                                        Log.i(
                                            "URI CAPA 06",
                                            "Aqui esta a URI da imagem na CadastroUsuarioPadraoScreen ${uriCapa}"
                                        )
                                    }

                                    Log.i(
                                        "GENERO 02",
                                        "Aqui esta o genero de perfil na EditarPerfilJogadorPart1Screen ${generoUserSharedState}"
                                    )
                                }
                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(180.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "SALVAR",
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))


                        Button(
                            onClick = {

                                    deletarHighlight(
                                        sharedViewModelTokenEId = sharedViewModelTokenEId
                                    )


                                    Log.i("JSON ACEITO", "Estrutura de JSON Correta!")
                                    onNavigate("carregar_informacoes_perfil_usuario")


                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .width(180.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "DELETAR",
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                onNavigate("lista_meus_high_lights")
                            },
                            modifier = Modifier
                                .width(390.dp)
                                .height(48.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "FECHAR",
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }
                    }


                    Spacer(modifier = Modifier.height(40.dp))

                }
            }
        }
    }
}

fun AtualizarHighLight(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewResponseGetHighLights: SharedViewResponseGetHighLights,
    tituloHighlight: String,
    id: Int
) {
    val token = sharedViewModelTokenEId.token




    // Criar uma instância da classe EditarPerfilUsuario com os dados a serem atualizados
    val bodyTituloHighlight = BodyTituloHighLight(
        titulo = tituloHighlight
    )

    // Obtenha o serviço Retrofit para editar o perfil do usuário
    val atualizarHighLightService = RetrofitFactoryCadastro().atualizarHighlightService()

    // Realize a chamada de API para editar o perfil
    atualizarHighLightService.atualizarHighLight("Bearer " + token, id, bodyTituloHighlight)
        .enqueue(object : Callback<ResponseHighLight> {
            override fun onResponse(
                call: Call<ResponseHighLight>,
                response: Response<ResponseHighLight>
            ) {
                if (response.isSuccessful) {
                    Log.d(
                        "EditarHighLightScreen",
                        "Highlight atualizado com sucesso: ${response.code()}"
                    )

                    Log.d(
                        "EditarHighLightScreen",
                        "Highlight body: ${response.body()}"
                    )
                    // Trate a resposta bem-sucedida, se necessário
                } else {
                    // Trate a resposta não bem-sucedida
                    Log.d(
                        "EditarHighLightScreen",
                        "Falha ao atualizar o Highlight: ${response.code()}"
                    )
                    // Log do corpo da resposta (se necessário)
                    Log.d(
                        "EditarHighLightScreen",
                        "Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<ResponseHighLight>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("EditarHighLightScreen", "Erro de rede: ${t.message}")
            }
        })
}