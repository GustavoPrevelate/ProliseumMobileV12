package br.senai.sp.jandira.proliseumtcc.gui.postagem

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
import br.senai.sp.jandira.proliseumtcc.components.FuncaoLol
import br.senai.sp.jandira.proliseumtcc.components.Jogo
import br.senai.sp.jandira.proliseumtcc.components.TimePickerComponent
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonEloLol
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonFuncaoLolUI
import br.senai.sp.jandira.proliseumtcc.components.ToggleButtonJogoUI
import br.senai.sp.jandira.proliseumtcc.model.CriarPostagemTime
import br.senai.sp.jandira.proliseumtcc.model.CriarPostagemTimeResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CriarPostagemTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,
    onNavigate: (String) -> Unit
){

    val token = sharedViewModelTokenEId.token

    Log.e("TOKEN JOGADOR","Olha aqui o token do usuario na hora de criar a publicacao: ${token}")
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

    val tipoTime = remember { mutableStateOf(true) }

    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemErroInputsPerfil = rememberSaveable { mutableStateOf("") }

//    var selectedStartTime by remember { mutableStateOf(LocalTime.now()) }
//    var selectedEndTime by remember { mutableStateOf(LocalTime.now()) }

    // Use o LocalTime como valor inicial no remember
    var selectedStartTime by remember { mutableStateOf(LocalTime.now()) }

    // Use a função getTimeString para obter a String formatada
    var selectedStartTimeString by remember(selectedStartTime) {
        mutableStateOf(getTimeStringTime(selectedStartTime))
    }


    var idTimeEscolhido by remember { mutableStateOf(sharedGetTimeTeams.id) }

    Log.e("ID DO TIME","ID DO TIME ESCOLHIDO: ${idTimeEscolhido}")


    LaunchedEffect(sharedGetTimeTeams) {

        idTimeEscolhido = sharedGetTimeTeams.id

    }


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
                        onNavigate("escolher_time_para_criar_postagem_time")
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
                text = "CRIAR POSTAGEM",
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




                    OutlinedTextField(
                        value = descricaoPublicacao,
                        onValueChange = { descricaoPublicacao = it },
                        modifier = Modifier
                            .height(200.dp)
                            .width(320.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "DESCRICAO PUBLICACAO",
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
                            selectedStartTimeString = getTimeStringTime(it)
                        }
                    )

                    Log.e("HORARIO SELECIONADO", "Horario selecionado ${selectedStartTimeString}")
                    Log.e("HORARIO SELECIONADO 2", "Horario selecionado 2 ${selectedStartTime}")



//                    Spacer(modifier = Modifier.height(20.dp))
//
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(start = 32.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//
//                        Checkbox(
//                            checked = jogadorRemunerado.value,
//                            onCheckedChange = { jogadorRemunerado.value = it },
//                            modifier = Modifier
//                                .scale(scale = 1.6f)
//                                .size(40.dp)
//                                .padding(16.dp),
//                            colors = CheckboxDefaults.colors(checkedColor = Color(0, 255, 165, 255)),
//                        )
//                        Spacer(modifier = Modifier.width(10.dp))
//                        Text(
//                            text = "Remuneração?",
//                            color = Color.White,
//                            fontFamily = customFontFamilyText,
//                            fontWeight = FontWeight(600),
//                            fontSize = 22.sp
//                        )
//                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = prosJogador,
                        onValueChange = {  prosJogador = it },
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

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {

                            criarPostagemTime(
                            sharedViewModelTokenEId = sharedViewModelTokenEId,
                            descricaoTime = descricaoPublicacao,
                            jogoTime = selectedJogo?.toRepresentationStringJogo(),
                            funcaoTime =  selectedFuncaoLol?.toRepresentationStrinFuncao(),
                            eloTime = selectedEloLol?.toRepresentationStringEloLol(),
                            horaTime = selectedStartTimeString,
                            tipoPublicacaoTime = tipoTime.value,
                            prosTime = prosJogador,
                            timeId = idTimeEscolhido
                        )

                        Log.e("DEFINITIVO HORARIO","Horario que foi selecionado ao clicar no botao ${selectedStartTimeString}")
                        Log.e("DESCRICAO 10","Descricao: ${descricaoPublicacao}")
                        Log.e("FUNCAO 10","Funcao: ${selectedFuncaoLol?.toRepresentationStrinFuncao()}")
                        Log.e("JOGO 10","Jogo: ${selectedJogo?.toRepresentationStringJogo()}")
                        Log.e("ELO 10","Elo: ${selectedEloLol?.toRepresentationStringEloLol()}")
                        Log.e("REMUNERACAO 10","Remuneração: ${tipoTime.value}")
                        Log.e("PROS 10","Pros: ${prosJogador}")

                        onNavigate("navigation_proliseum")

                        },
                        modifier = Modifier
                            .width(370.dp)
                            .height(50.dp)
                            .padding(start = 0.dp, top = 0.dp),
                        shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                        colors = ButtonDefaults.buttonColors(RedProliseum),

                    ) {
                        Text(
                            text = "CRIAR PUBLICAÇÃO",
                            fontFamily = customFontFamilyText,
                            fontSize = 22.sp,
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

fun getTimeStringTime(time: LocalTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return time.format(formatter)
}

fun criarPostagemTime(
    sharedViewModelTokenEId: SharedViewTokenEId,
    descricaoTime: String?,
    jogoTime: String?,
    funcaoTime: String?,
    eloTime: String?,
    horaTime: String?,
    tipoPublicacaoTime: Boolean?,
    prosTime: String?,
    timeId: Int?,
){

    val token = sharedViewModelTokenEId.token

    val newCriarPostagemTime = CriarPostagemTime(
        descricao = descricaoTime,
        jogo = jogoTime,
        funcao = funcaoTime,
        elo = eloTime,
        hora = horaTime,
        tipo = tipoPublicacaoTime,
        pros = prosTime,
        time = timeId
    )


    val criarPostagemTimeService = RetrofitFactoryCadastro().criarPostagemTimeService()

    criarPostagemTimeService.postCriarPostagemTime("Bearer " + token, newCriarPostagemTime)
        .enqueue(object : Callback<CriarPostagemTimeResponse> {
            override fun onResponse(call: Call<CriarPostagemTimeResponse>, response: Response<CriarPostagemTimeResponse>) {
                if (response.isSuccessful) {

                    Log.d(
                        "SUCESSO RAPAZ",
                        "Perfil de usuário atualizado com sucesso: ${response.code()}"
                    )

                } else {

                    Log.d(
                        "OLHA O CODE",
                        "Falha ao atualizar o perfil do usuário: ${response.code()}"
                    )
                    // Log do corpo da resposta (se necessário)
                    Log.d(
                        "ERRO FILHAO",
                        "Corpo da resposta: ${response.errorBody()?.string()}"
                    )
                }
            }

            override fun onFailure(call: Call<CriarPostagemTimeResponse>, t: Throwable) {

            }
        })

}

