package br.senai.sp.jandira.proliseumtcc.gui.editar_perfil

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
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
import br.senai.sp.jandira.proliseumtcc.components.EloLol
import br.senai.sp.jandira.proliseumtcc.components.RedeSocial
import br.senai.sp.jandira.proliseumtcc.components.FuncaoLol
import br.senai.sp.jandira.proliseumtcc.components.Genero
import br.senai.sp.jandira.proliseumtcc.components.Jogo
import br.senai.sp.jandira.proliseumtcc.components.TimePickerComponent
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonEloLol
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.gui.postagem.getTimeString
import br.senai.sp.jandira.proliseumtcc.model.AtualizarPostagemTime
import br.senai.sp.jandira.proliseumtcc.model.AtualizarPostagemTimeResponse
import br.senai.sp.jandira.proliseumtcc.model.PutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.model.ResponsePutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemPostProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoTimes
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarMinhaPublicacaoTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    //SharedViewModel GET MINHA POSTAGEM
    sharedGetMinhaPostagem: SharedGetMinhaPostagem,
    sharedGetMinhaPostagemUser: SharedGetMinhaPostagemUser,
    sharedGetMinhaPostagemUserPropostas: SharedGetMinhaPostagemUserPropostas,
    sharedGetMinhaPostagemPostProfile: SharedGetMinhaPostagemPostProfile,

    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,

    sharedViewModelListaPublicacaoTimes: SharedViewModelListaPublicacaoTimes,

    sharedGetTimeListaPostagens: SharedGetTimeListaPostagens,
    sharedGetTimeListaPostagensPublicacao: SharedGetTimeListaPostagensPublicacao,
    sharedGetTimeListaPostagensPublicacaoDonoId: SharedGetTimeListaPostagensPublicacaoDonoId,
    sharedGetTimeListaPostagensPublicacaoTime: SharedGetTimeListaPostagensPublicacaoTime,
    sharedGetTimeListaPostagensPublicacaoTimeJogadores: SharedGetTimeListaPostagensPublicacaoTimeJogadores,
    sharedGetTimeListaPostagensPublicacaoTimePropostas: SharedGetTimeListaPostagensPublicacaoTimePropostas,
    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token

    Log.e("TOKEN JOGADOR","Olha aqui o token do usuario na hora de editar a publicacao: ${token}")
    // FONTE E TECLADO

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val keyboardController = LocalSoftwareKeyboardController.current

    var descricaoPublicacao by rememberSaveable { mutableStateOf("") }
    var prosJogador by rememberSaveable { mutableStateOf("") }

    var selectedFuncaoLol by remember { mutableStateOf<FuncaoLol?>(null) }
    var selectedJogo by remember { mutableStateOf<Jogo?>(null) }
    var selectedEloLol by remember { mutableStateOf<EloLol?>(null) }

    val tipoTime = remember { mutableStateOf(false) }

    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemErroInputsPerfil = rememberSaveable { mutableStateOf("") }

//    var selectedStartTime by remember { mutableStateOf(LocalTime.now()) }
//    var selectedEndTime by remember { mutableStateOf(LocalTime.now()) }

    // Use o LocalTime como valor inicial no remember
    var selectedStartTime by remember { mutableStateOf(LocalTime.now()) }

    // Use a função getTimeString para obter a String formatada
    var selectedStartTimeString by remember(selectedStartTime) {
        mutableStateOf(getTimeString(selectedStartTime))
    }

    var idTimeEscolhido by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacaoTime.id) }

    var idMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.id) }
    var descricaoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.descricao) }
    var jogoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.jogo) }
    var funcaoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.funcao) }
    var eloMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.elo) }
    var horaMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.hora) }
    var tipoMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.tipo) }
    var prosMinhaPublicacaoState by remember { mutableStateOf(sharedGetTimeListaPostagensPublicacao.pros) }

    var selectedGeneroUser by remember { mutableStateOf<Genero?>(null) }


    LaunchedEffect(sharedGetTimeListaPostagensPublicacao, sharedGetTimeListaPostagensPublicacaoTime) {

        // Esta parte só será executada quando o composable for inicializado
        idMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.id
        descricaoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.descricao
        jogoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.jogo
        funcaoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.funcao
        eloMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.elo
        horaMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.hora
        tipoMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.tipo
        prosMinhaPublicacaoState = sharedGetTimeListaPostagensPublicacao.pros

        idTimeEscolhido = sharedGetTimeListaPostagensPublicacaoTime.id
        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    Log.e("id do time", "ID DO TIME ESCOLHIDO ${idTimeEscolhido}")
    Log.e("ID DA PUBLICACAO DA PUBLICACAO ", "ID DA PUBLICACAO ${idMinhaPublicacaoState}")
    Log.e("DESCRICAO DA PUBLICACAO ", "DESCRICAO DA PUBLICACAO ${descricaoMinhaPublicacaoState}")
    Log.e("JOGO DA PUBLICACAO", "JOGO DA PUBLICACAO ${jogoMinhaPublicacaoState}")
    Log.e("FUNCAO DA PUBLICACAO", "FUNCAO DA PUBLICACAO ${funcaoMinhaPublicacaoState}")
    Log.e("ELO DA PUBLICACAO", "ELO DA PUBLICACAO ${eloMinhaPublicacaoState}")
    Log.e("HORA DA PUBLICACAO", "HORA DA PUBLICACAO ${horaMinhaPublicacaoState}")
    Log.e("TIPO DA PUBLICACAO", "TIPODA PUBLICACAO ${tipoMinhaPublicacaoState}")
    Log.e("PROS DA PUBLICACAO", "PROS DA PUBLICACAO ${prosMinhaPublicacaoState}")



    //DESIGN DA TELA
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
                        onNavigate("escolher_time_para_visualizar_postagem_time")
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
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Image(
                painter = painterResource(id = R.drawable.logocadastro),
                contentDescription = ""
            )
            Text(
                text = "EDITAR PUBLICAÇÃO",
                fontFamily = customFontFamily,
                fontSize = 36.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )

            Spacer(modifier = Modifier.height(10.dp))



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
                    .fillMaxHeight()
                    .padding(top = 200.dp)
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
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {



                    descricaoMinhaPublicacaoState?.let {
                        OutlinedTextField(
                            value = it,
                            onValueChange = { descricaoMinhaPublicacaoState = it },
                            modifier = Modifier
                                .height(200.dp)
                                .width(320.dp),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(
                                    text = "DESCRICAO PUBLICAÇÃO",
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

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp)
                    ) {
                        Text(
                            text = "JOGO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))


                    Column {
                        ToggleButtonJogoUI{ jogo ->
                            selectedJogo = jogo
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp)
                    ) {
                        Text(
                            text = "FUNÇÃO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))


                    Column {
                        ToggleButtonFuncaoLolUI{ funcao ->
                            selectedFuncaoLol = funcao
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 40.dp)
                    ) {
                        Text(
                            text = "ELO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))


                    Column {
                        ToggleButtonEloLol{ eloLol ->
                            selectedEloLol = eloLol
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    TimePickerComponent(
                        selectedStartTime = selectedStartTime,
                        onStartTimeSelected = {
                            selectedStartTime = it
                            selectedStartTimeString = getTimeString(it)
                        }
                    )

                    Log.e("HORARIO SELECIONADO", "Horario selecionado ${selectedStartTimeString}")
                    Log.e("HORARIO SELECIONADO 2", "Horario selecionado 2 ${selectedStartTime}")



                    Spacer(modifier = Modifier.height(20.dp))

                    prosMinhaPublicacaoState?.let {
                        OutlinedTextField(
                            value = it,
                            onValueChange = {  prosMinhaPublicacaoState = it },
                            modifier = Modifier
                                .height(200.dp)
                                .width(320.dp),
                            shape = RoundedCornerShape(16.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            label = {
                                Text(
                                    text = "PROS:",
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

                    Spacer(modifier = Modifier.height(20.dp))

                    fun atualizarPostagemTime(
                        sharedViewModelTokenEId: SharedViewTokenEId,
                        descricaoTime: String?,
                        jogoTime: String?,
                        funcaoTime: String?,
                        eloTime: String?,
                        horaTime: String?,
                        tipoPublicacaoTime: Boolean?,
                        prosTime: String?,
                        timeId: Int,
                    ){

                        val token = sharedViewModelTokenEId.token

                        val newPostagemTime = AtualizarPostagemTime(
                            descricao = descricaoTime,
                            jogo = jogoTime,
                            funcao = funcaoTime,
                            elo = eloTime,
                            hora = horaTime,
                            tipo = tipoPublicacaoTime,
                            pros = prosTime,
                            time = timeId
                        )


                        val atualizarPostagemTimeService = RetrofitFactoryCadastro().atualizarPostagemTimeService()

                        idTimeEscolhido?.let {
                            atualizarPostagemTimeService.atualizarPostagemTime("Bearer " + token,
                                it, newPostagemTime)
                                .enqueue(object : Callback<AtualizarPostagemTimeResponse> {
                                    override fun onResponse(call: Call<AtualizarPostagemTimeResponse>, response: Response<AtualizarPostagemTimeResponse>) {
                                        if (response.isSuccessful) {

                                            Log.d(
                                                "SUCESSO",
                                                "Postagem de time atualizado com sucesso: ${response.code()}"
                                            )


                                        } else {

                                            Log.d(
                                                "CODE",
                                                "Falha ao atualizar a postagem de time: ${response.code()}"
                                            )
                                            // Log do corpo da resposta (se necessário)
                                            Log.d(
                                                "ERRO",
                                                "Corpo da resposta: ${response.errorBody()?.string()}"
                                            )
                                        }
                                    }

                                    override fun onFailure(call: Call<AtualizarPostagemTimeResponse>, t: Throwable) {

                                    }
                                })
                        }

                    }

                    Button(
                        onClick = {

                            for (i in 1..2) {
                                idTimeEscolhido?.let {
                                    atualizarPostagemTime(
                                        sharedViewModelTokenEId = sharedViewModelTokenEId,
                                        descricaoTime = descricaoMinhaPublicacaoState,
                                        jogoTime = selectedJogo?.toRepresentationStringJogo(),
                                        funcaoTime =  selectedFuncaoLol?.toRepresentationStrinFuncao(),
                                        eloTime = selectedEloLol?.toRepresentationStringEloLol(),
                                        horaTime = selectedStartTimeString,
                                        tipoPublicacaoTime = tipoTime.value,
                                        prosTime = prosMinhaPublicacaoState,
                                        timeId = it
                                    )
                                }
                            }

                            Log.e("DEFINITIVO HORARIO","Horario que foi selecionado ao clicar no botao ${selectedStartTimeString}")
                            Log.e("DESCRICAO 10","Descricao: ${descricaoPublicacao}")
                            Log.e("FUNCAO 10","Funcao: ${selectedFuncaoLol?.toRepresentationStrinFuncao()}")
                            Log.e("JOGO 10","Jogo: ${selectedJogo?.toRepresentationStringJogo()}")
                            Log.e("ELO 10","Elo: ${selectedEloLol?.toRepresentationStringEloLol()}")
                            Log.e("REMUNERACAO 10","Remuneração: ${tipoTime.value}")
                            Log.e("PROS 10","Pros: ${prosJogador}")

                            onNavigate("lista_de_publicacoes_times")

//


                    },
                        modifier = Modifier
                            .width(370.dp)
                            .height(50.dp)
                            .padding(start = 0.dp, top = 0.dp),
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum),
                    ) {
                        Text(
                            text = "EDITAR PUBLICAÇÃO",
                            fontFamily = customFontFamilyText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(30.dp))
                }


            }

        }

        //PopUp
        LaunchedEffect(camposPreenchidosCorretamente) {
            if (!camposPreenchidosCorretamente) {
                delay(15000)
                camposPreenchidosCorretamente = true
            }
        }

        AnimatedVisibility(
            visible = !camposPreenchidosCorretamente,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it })
        ) {
            Snackbar(
                modifier = Modifier.padding(top = 16.dp),
                action = {}
            ) {
                Text(text = mensagemErroInputsPerfil.value)
            }
        }
    }
}

