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
import br.senai.sp.jandira.proliseumtcc.model.PutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.model.ResponsePutAtualizarMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemPostProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.BlackTransparentProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarMinhaPublicacaoJogadorScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelPerfilEditar: SharedViewModelPerfil,
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

    val jogadorRemunerado = remember { mutableStateOf(false) }

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

    var idMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.id) }
    var descricaoMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.descricao) }
    var jogoMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.jogo) }
    var funcaoMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.funcao) }
    var eloMinhaPublicacaoState by remember { mutableStateOf<Int?>(sharedGetMinhaPostagemPostProfile.elo) }
    var horaMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.hora) }
    var tipoMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.tipo) }
    var prosMinhaPublicacaoState by remember { mutableStateOf(sharedGetMinhaPostagemPostProfile.pros) }

    var selectedGeneroUser by remember { mutableStateOf<Genero?>(null) }


    LaunchedEffect(sharedGetMinhaPostagemPostProfile) {

        // Esta parte só será executada quando o composable for inicializado
        idMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.id
        descricaoMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.descricao
        jogoMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.jogo
        funcaoMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.funcao
        eloMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.elo
        horaMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.hora
        tipoMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.tipo
        prosMinhaPublicacaoState = sharedGetMinhaPostagemPostProfile.pros
        // Atribua outras variáveis de estado para outros campos da mesma maneira
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
                        onNavigate("home")
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
                text = stringResource(id = R.string.text_cadastro),
                fontFamily = customFontFamily,
                fontSize = 48.sp,
                textAlign = TextAlign.Center,
                color = Color.White

            )

            Spacer(modifier = Modifier.height(10.dp))

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
                    .padding(top = 300.dp)
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
                    }

                    Spacer(modifier = Modifier.height(20.dp))


                    Column {
                        ToggleButtonJogoUI{ jogo ->
                            selectedJogo = jogo
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "FUNCAO JOGO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }


                    Column {
                        ToggleButtonFuncaoLolUI{ funcao ->
                            selectedFuncaoLol = funcao
                        }

                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "FUNCAO JOGO:",
                            fontFamily = customFontFamilyText,
                            fontSize = 25.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }


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

                    Button(onClick = {

                        for (i in 1..2) {
                            atualizarPostagem(
                                sharedViewModelTokenEId = sharedViewModelTokenEId,
                                descricaoJogador = descricaoMinhaPublicacaoState,
                                jogoJogador = selectedJogo?.toRepresentationStringJogo(),
                                funcaoJogador =  selectedFuncaoLol?.toRepresentationStrinFuncao(),
                                eloJogador = selectedEloLol?.toRepresentationStringEloLol(),
                                horaJogador = selectedStartTimeString,
                                tipoPublicacao = jogadorRemunerado.value,
                                prosJogador = prosMinhaPublicacaoState
                            )

                            Log.e("DEFINITIVO HORARIO","Horario que foi selecionado ao clicar no botao ${selectedStartTimeString}")
                            Log.e("DESCRICAO 10","Descricao: ${descricaoPublicacao}")
                            Log.e("FUNCAO 10","Funcao: ${selectedFuncaoLol?.toRepresentationStrinFuncao()}")
                            Log.e("JOGO 10","Jogo: ${selectedJogo?.toRepresentationStringJogo()}")
                            Log.e("ELO 10","Elo: ${selectedEloLol?.toRepresentationStringEloLol()}")
                            Log.e("REMUNERACAO 10","Remuneração: ${jogadorRemunerado.value}")
                            Log.e("PROS 10","Pros: ${prosJogador}")

                            onNavigate("carregar_informacoes_minha_publicacao")
                        }
//


                    }) {

                    }
                }


            }

        }
    }
}

fun atualizarPostagem(
    sharedViewModelTokenEId: SharedViewTokenEId,
    descricaoJogador: String?,
    jogoJogador: String?,
    funcaoJogador: String?,
    eloJogador: String?,
    horaJogador: String?,
    tipoPublicacao: Boolean?,
    prosJogador: String?
){

    val token = sharedViewModelTokenEId.token

    val newPostagemJogador = PutAtualizarMinhaPostagem(
        descricao = descricaoJogador,
        jogo = jogoJogador,
        funcao = funcaoJogador,
        elo = eloJogador,
        hora = horaJogador,
        tipo = tipoPublicacao,
        pros = prosJogador
    )


    val atualizarPostagemService = RetrofitFactoryCadastro().putMinhaPostagemService()

    atualizarPostagemService.putMyPost("Bearer " + token, newPostagemJogador)
        .enqueue(object : Callback<ResponsePutAtualizarMinhaPostagem> {
            override fun onResponse(call: Call<ResponsePutAtualizarMinhaPostagem>, response: Response<ResponsePutAtualizarMinhaPostagem>) {
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

            override fun onFailure(call: Call<ResponsePutAtualizarMinhaPostagem>, t: Throwable) {

            }
        })

}