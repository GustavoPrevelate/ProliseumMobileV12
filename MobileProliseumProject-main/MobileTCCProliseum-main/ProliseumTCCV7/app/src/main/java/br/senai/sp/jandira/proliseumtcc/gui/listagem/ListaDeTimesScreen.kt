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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaTimes
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.model.getTime
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaDeTimesScreen (
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

    sharedViewModelPerfilEditarOutro: SharedViewModelPerfilOutro,
    sharedViewModelPerfilJogadorOutro: SharedViewModelPerfilJogadorOutro,
    sharedViewModelPerfilOrganizadorOutro: SharedViewModelPerfilOrganizadorOutro,

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

    // SharedViewModel GET TIME BY ID
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,

    sharedFiltragemListaTimes: SharedFiltragemListaTimes,

    onNavigate: (String) -> Unit
) {

//    // VARIAVEL PARA FILTRAGEM DE TIME
//    var nomeTimeFilter by rememberSaveable { mutableStateOf("") }

    var loading by remember { mutableStateOf(true) }

    val idUser = sharedViewModelUser.id

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

    var nomeTimePesquisado by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        // Espera por 3 segundos antes de continuar
        delay(3000)
        loading = false
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var exibirConteudo by remember { mutableStateOf(false) }

    var minhaListaDeTimes by remember {
        mutableStateOf(listOf<getTimeTeams>())
    }

    Log.e("TESTE SHARED VIEW", "TESTE DE COMPARTILHAMENTO COM TESTE VIEW MODEL: ${sharedGetTime.teams}")

    val token = sharedViewModelTokenEId.token

//    fun nomeTimeFiltradoFunction(
//        nomeTimeFiltradoString: String
//    ){
//        val nomeTimeFiltrado = filtragemTimes(
//            name = nomeTimeFiltradoString
//        )
//    }

    var filtragemNomeState by remember { mutableStateOf(sharedFiltragemListaTimes.name) }

    LaunchedEffect(sharedFiltragemListaTimes) {

        // Esta parte só será executada quando o composable for inicializado
        filtragemNomeState = sharedFiltragemListaTimes.name

        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    if(token != null && token.isNotEmpty()){

        val nomeTimeAposFiltragem = sharedFiltragemListaTimes.name


        val perfilTimeService = RetrofitFactoryCadastro().theGetTimeService()

        perfilTimeService.theGetTime(nomeTimeAposFiltragem).enqueue(object : Callback<getTime> {
            override fun onResponse(call: Call<getTime>, response: Response<getTime>) {
                if (response.isSuccessful) {
                    Log.d("GET TIME FILTER CERTO", "Resposta bem-sucedida: ${response.code()}")
                    val profileTimeResponseData = response.body()

                    Log.d("BODY", "$profileTimeResponseData")

                    val timeResponse = profileTimeResponseData?.teams



                    if (timeResponse != null) {
                        minhaListaDeTimes = timeResponse
                    }

                    sharedGetTime.teams = timeResponse

                    Log.d("BODY TIMES", "$timeResponse")



                    if(timeResponse != null){
                        for ( time in timeResponse){

                            val idTimeInfoTime = time.id
                            val nomeTimeInfoTime = time.nome_time
                            val jogoTimeInfoTime = time.jogo
                            val biografiaTimeInfoTime = time.biografia

                            sharedGetTimeTeams.id = time.id
                            sharedGetTimeTeams.nome_time = time.nome_time
                            sharedGetTimeTeams.jogo = time.jogo
                            sharedGetTimeTeams.biografia = time.biografia

                            val donoTime = time.dono

                            if(donoTime != null){
                                val idInfoDonoOrganizacaoTimeById = donoTime.id
                                val nomeUsuarioInfoDonoOrganizacaoTimeById = donoTime.nome_usuario
                                val nomeCompletoInfoDonoOrganizacaoTimeById = donoTime.nome_completo
                                val emailInfoDonoOrganizacaoTimeById = donoTime.email
                                val senhaInfoDonoOrganizacaoTimeById = donoTime.senha
                                val dataNascimentoInfoDonoOrganizacaoTimeById = donoTime.data_nascimento
                                val generoInfoDonoOrganizacaoTimeById = donoTime.genero
                                val nicknameInfoDonoOrganizacaoTimeById = donoTime.nickname
                                val biografiaInfoDonoOrganizacaoTimeById = donoTime.biografia

                                sharedGetTimeDono.id = donoTime.id
                                sharedGetTimeDono.nome_usuario = donoTime.nome_usuario
                                sharedGetTimeDono.nome_completo = donoTime.nome_completo
                                sharedGetTimeDono.email = donoTime.email
                                sharedGetTimeDono.senha = donoTime.senha
                                sharedGetTimeDono.data_nascimento = donoTime.data_nascimento
                                sharedGetTimeDono.genero = donoTime.genero
                                sharedGetTimeDono.nickname = donoTime.nickname
                                sharedGetTimeDono.biografia = donoTime.biografia
                            }


                            //sharedGetTimeTeams.jogadores = time.jogadores


                            Log.e("TIME JOGADORES", "Aqui esta como time.jogadores está sendo guardado${time.jogadores}")

                            val jogadoresTime = time.jogadores

                            if(jogadoresTime != null){
                                for(jogadoresTime in jogadoresTime) {
                                    val idInfoJogadoresTimeById = jogadoresTime.id
                                    val nickNameInfoJogadoresTimeById = jogadoresTime.nickname
                                    val jogoInfoJogadoresTimeById = jogadoresTime.jogo
                                    val funcaoInfoJogadoresTimeById = jogadoresTime.funcao
                                    val eloInfoJogadoresTimeById = jogadoresTime.elo

                                    sharedGetTimeTeamsJogadores.id
                                    sharedGetTimeTeamsJogadores.nickname
                                    sharedGetTimeTeamsJogadores.jogo
                                    sharedGetTimeTeamsJogadores.funcao
                                    sharedGetTimeTeamsJogadores.elo

                                    val perfil_idInfoJogadoresTime = jogadoresTime.perfil_id

                                    sharedGetTimeTeamsJogadores.perfil_id = perfil_idInfoJogadoresTime

                                    if(perfil_idInfoJogadoresTime != null){
                                        val idInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.id
                                        val nomeUsuarioInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.nome_usuario
                                        val nomeCompletoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.nome_completo
                                        val emailInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.email
                                        val senhaInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.senha
                                        val dataNascimentoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.data_nascimento
                                        val generoInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.genero
                                        val nicknameInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.nickname
                                        val biografiaInfoPerfil_idInfoJogadoresTimeById = perfil_idInfoJogadoresTime.biografia

                                        sharedGetTimeTeamsJogadoresPerfilId.id = perfil_idInfoJogadoresTime.id
                                        sharedGetTimeTeamsJogadoresPerfilId.nome_usuario = perfil_idInfoJogadoresTime.nome_usuario
                                        sharedGetTimeTeamsJogadoresPerfilId.nome_completo = perfil_idInfoJogadoresTime.nome_completo
                                        sharedGetTimeTeamsJogadoresPerfilId.email = perfil_idInfoJogadoresTime.email
                                        sharedGetTimeTeamsJogadoresPerfilId.senha = perfil_idInfoJogadoresTime.senha
                                        sharedGetTimeTeamsJogadoresPerfilId.data_nascimento = perfil_idInfoJogadoresTime.data_nascimento
                                        sharedGetTimeTeamsJogadoresPerfilId.genero = perfil_idInfoJogadoresTime.genero
                                        sharedGetTimeTeamsJogadoresPerfilId.nickname = perfil_idInfoJogadoresTime.nickname
                                        sharedGetTimeTeamsJogadoresPerfilId.biografia = perfil_idInfoJogadoresTime.biografia
                                    }
                                }

                            }

                            val propostasTime = time.propostas

                            if(propostasTime != null){
                                for(propostaTime in propostasTime){
                                    val idInfoPropostasTimeById = propostaTime.id
                                    val menssagemInfoPropostasTimeById = propostaTime.menssagem

                                    sharedGetTimeTeamsPropostas.id = propostaTime.id
                                    sharedGetTimeTeamsPropostas.menssagem = propostaTime.menssagem
                                }
                            }
                        }
                    }

                    val jogadorIdCompartilhado = sharedGetTimeDono.id // Obtenha o ID do time clicado

//                                    if(jogadorIdCompartilhado != null){
//                                        val verificacao = true
//
//                                        if (verificacao == true) {
//                                            verificarIdDoJogadorTimeById( sharedGetTimeByIdTeams, jogadorIdCompartilhado)
//                                            sharedGetTimeByIdTeams.selectedJogadorIdTimeById = jogadorIdCompartilhado
//                                            Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetMyTeamsGeral.selectedTimeId}")
//                                            onNavigate("carregar_informacoes_do_time_by_id")
//                                        }

//                                    }



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

            override fun onFailure(call: Call<getTime>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
            }

        })


    } else{
        Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
        // Lidar com o caso em que o token é nulo ou vazio
        // Por exemplo, você pode exibir uma mensagem de erro ou redirecionar o usuário para fazer login.
        // Ou então, pode simplesmente não fazer nada.
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
        Column(){

            Row(
                modifier = Modifier.padding(start = 20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(top = 20.dp)
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
                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = "TIMES",
                        fontFamily = customFontFamilyText,
                        fontSize = 25.sp,
                        fontWeight = FontWeight(900),
                        color = Color.White
                    )
                }


                Row(
                    modifier = Modifier.padding(top = 12.dp)
                ){
                    Spacer(modifier = Modifier.width(20.dp))

                    Button(
                        onClick = {
                            onNavigate("criar_time")
                        },
                        modifier = Modifier
                            .height(48.dp),
                        shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum)

                    ) {
                        Text(
                            text = "CRIAR TIME",
                            fontSize = 25.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            fontFamily = customFontFamilyText,
                            fontWeight = FontWeight(900),
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }

                Spacer(modifier = Modifier.height(20.dp))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        onNavigate("lista_times_meus_times")

                    },
                    modifier = Modifier
                        .width(190.dp)
                        .height(50.dp)
                        .padding(start = 0.dp, top = 0.dp),
                    shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(RedProliseum),
                ) {
                    Text(
                        text = "MEUS TIMES",
                        fontFamily = customFontFamilyText,
                        fontSize = 16.sp,
                        fontWeight = FontWeight(900),
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = {

                        sharedFiltragemListaTimes.name = filtragemNomeState

                        onNavigate("carregar_filtragem_lista_times")

                    },
                    modifier = Modifier
                        .width(190.dp)
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



                filtragemNomeState?.let {
                    OutlinedTextField(
                        value = it,
                        onValueChange = { filtragemNomeState = it },
                        modifier = Modifier

                            .fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "FILTRAGEM DE TIME",
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

        val token = sharedViewModelTokenEId.token

        Log.d("CarregarPerfilUsuarioScreen", "Token: $token")

        val listaIdsTimes = remember { mutableListOf<Int>() }

        if(minhaListaDeTimes != null){
            Column(
                modifier = Modifier
                    .padding(top = 180.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 50.dp),
                    content = {
                        items(minhaListaDeTimes.size){ index ->
                            val playerListTimes = minhaListaDeTimes[index]





                            val infoPerfilId = playerListTimes.id

                            Log.d("ID TIMES", "Number of ids: ${infoPerfilId}")

                            val idInfoTime = playerListTimes?.id ?: 0
                            val nomeTimeInfoTime = playerListTimes?.nome_time ?: ""
                            val biografiaTimeInfoTime = playerListTimes?.biografia ?: ""
                            val jogoTimeInfoTime = playerListTimes?.jogo ?: 0
                            val listaJogadoresTimeInfoTime = playerListTimes?.jogadores ?: null
                            val listaDonoTimeInfoTime = playerListTimes?.dono ?: null
                            val listaPropostasTimeInfoTime = playerListTimes?.propostas

//                        val listaJogadoresTimeInfoTime = playerListTimes?.jogadores
//                        val listaOrganizacaoTimeInfoTime = playerListTimes?.organizacao
//                        val listaPropostasTimeInfoTime = playerListTimes?.propostas

                            val idDonoTime = playerListTimes.dono?.id ?: 0
                            val nomeGerenciadorTime = playerListTimes.dono?.nickname ?: ""



                            listaIdsTimes.add(idInfoTime)

                            val imageRef = remember { mutableStateOf<StorageReference?>(null) }

                            if(idInfoTime != null && idInfoTime != 0){


                                val storage = Firebase.storage

                                if (playerListTimes != null) {

                                    if (idInfoTime != null && idInfoTime != 0) {
                                        // Utilize o ID do perfil aqui
                                        imageRef.value = storage.reference.child("team/$idInfoTime/profile")
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

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .height(250.dp)
                                    .padding(top = 20.dp),
                            ) {
                                Button(
                                    onClick = {

                                        // Verifique se listaJogadoresTimeInfoTime não é nulo antes de atribuir ao ViewModel
                                        listaJogadoresTimeInfoTime?.let { jogadoresList ->
                                            sharedGetTimeTeams.jogadores = jogadoresList
                                        }

//                                    listaDonoTimeInfoTime?.let { donoList ->
//                                        sharedGetTimeTeams.dono = donoList
//                                        sharedGetTimeDono.id = donoList.id
//                                    }



                                        val timeId = infoPerfilId // Obtenha o ID do time clicado
                                        val verificacao = true

                                        if (verificacao == true) {
                                            if (timeId != null) {
                                                verificarIdDoTimeFilter(
                                                    sharedGetTime,
                                                    timeId
                                                )
                                            }
                                            sharedGetTime.selectedTimeFilterId = timeId
                                            Log.e("SHAREDVIEW ID"," Aqui esta o id do time que ficou salvo no SharedViewModel${sharedGetTime.selectedTimeFilterId}")


                                            sharedGetTimeDono.id = idDonoTime
                                            Log.e("ID DONO ORIGINAL", "Id do dono do time na lista time${idDonoTime}")
                                            onNavigate("carregar_informacoes_lista_times")

                                        }

                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(250.dp)
                                        .padding(start = 0.dp, top = 0.dp),
                                    shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                    colors = ButtonDefaults.buttonColors(BlackTransparentProliseum),
                                ) {

                                    //Times
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        horizontalArrangement = Arrangement.Center,
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
                                                text = "${playerListTimes.nome_time}",
                                                color = Color.White,
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight(900),
                                                fontFamily = customFontFamilyText,
                                                fontSize = 20.sp
                                            )

                                            Card(
                                                modifier = Modifier
                                                    .height(75.dp)
                                                    .width(75.dp),
                                                colors = CardDefaults.cardColors(RedProliseum)
                                            ) {
                                                Image(
                                                    painter =
                                                    if ("${playerListTimes.jogo}" == "0") painterResource(
                                                        id = R.drawable.iconlol
                                                    )
                                                    else if ("${playerListTimes.jogo}" == "1") painterResource(id = R.drawable.iconlol)
                                                    else if ("${playerListTimes.jogo}" == "2") painterResource(id = R.drawable.iconlol)
                                                    else painter,
                                                    contentDescription = "",
                                                    modifier = Modifier.fillMaxSize(),
                                                    alignment = Alignment.Center,
                                                    colorFilter = ColorFilter.tint(AzulEscuroProliseum)
                                                )
                                            }

                                            Spacer(modifier = Modifier.height(5.dp))

//                            Text(
//                                text = "${playerListTimes.id}",
//                                color = Color.White,
//                                modifier = Modifier.padding(5.dp),
//                                fontWeight = FontWeight(600),
//                                fontFamily = customFontFamilyText,
//                                fontSize = 12.sp
//                            )
//
//                            Spacer(modifier = Modifier.width(5.dp))


                                            Text(
                                                text = "GERENCIADO POR ${nomeGerenciadorTime}",
                                                color = Color.White,
                                                modifier = Modifier.padding(5.dp),
                                                fontWeight = FontWeight(600),
                                                fontFamily = customFontFamilyText,
                                                fontSize = 16.sp
                                            )

                                        }


                                    }


                                }
                            }

                            Spacer(modifier = Modifier.height(25.dp))
                        }
                    }
                )
            }
        }
    }
}

fun verificarIdDoTimeFilter(
    sharedGetTime: SharedGetTime,
    timeId: Int // Adicione um novo parâmetro para o ID do time
) {
    val team = sharedGetTime.getTeamFilter(timeId) // Use o ID do time passado como argumento
    Log.e("ID TIME 01", "Aqui está o id do time escolhido $team")
}