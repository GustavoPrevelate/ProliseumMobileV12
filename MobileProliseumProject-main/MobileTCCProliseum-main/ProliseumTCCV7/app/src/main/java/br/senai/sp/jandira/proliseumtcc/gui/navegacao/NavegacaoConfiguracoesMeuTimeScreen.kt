package br.senai.sp.jandira.proliseumtcc.gui.navegacao

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTime
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NavegacaoConfiguracoesMeuTimeScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedViewModelUser: SharedViewModelUser,
    sharedGetTimeTeams: SharedGetTimeTeams,
    onNavigate: (String) -> Unit

) {
    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var camposPreenchidosCorretamente by rememberSaveable { mutableStateOf(true) }
    var mensagemSucessoInputsPerfil = rememberSaveable { mutableStateOf("") }



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
            horizontalAlignment = Alignment.Start,
        ) {

            Row(
                modifier = Modifier.padding(start = 20.dp, top = 20.dp)
            ) {
                Icon(
                    modifier = Modifier.clickable {
                        //rememberNavController.navigate("home")
                        onNavigate("perfil_outro_time")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "GERENCIAR TIME",
                    fontFamily = customFontFamilyText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onNavigate("minha_postagem_time")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_icon),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp),
                    tint = Color(255, 255, 255, 255)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "MINHA POSTAGEM",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {


                    val idUserVaiEntrarNoTime = sharedViewModelUser.id

                    val idDoTime = sharedGetTimeTeams.id

                    val token = sharedViewModelTokenEId.token

                    val entrarNoTimeService = RetrofitFactoryCadastro().EntrarNoMeuTimeService()

                    entrarNoTimeService.putTeamTimeJogador("Bearer " + token, idDoTime, idUserVaiEntrarNoTime)
                        .enqueue(object : Callback<AdicionarJogadorAoTime> {
                            override fun onResponse(call: Call<AdicionarJogadorAoTime>, response: Response<AdicionarJogadorAoTime>) {
                                if (response.isSuccessful) {

                                    Log.d(
                                        "ENTROU!",
                                        "ENTROU NO TIME COM SUCESSO: ${response.code()}"
                                    )



                                    camposPreenchidosCorretamente = false
                                    mensagemSucessoInputsPerfil.value = "ENTROU NO TIME COM SUCESSO!"

                                } else {

                                    Log.d(
                                        "NÃO ENTROU",
                                        "Falha ao entrar no time: ${response.code()}"
                                    )
                                    // Log do corpo da resposta (se necessário)
                                    Log.d(
                                        "BODY NÃO ENTROU",
                                        "Corpo da resposta: ${response.errorBody()?.string()}"
                                    )

                                    camposPreenchidosCorretamente = false
                                    mensagemSucessoInputsPerfil.value = "Você não pode entrar nesse time, você já está em um time!"
                                }
                            }

                            override fun onFailure(call: Call<AdicionarJogadorAoTime>, t: Throwable) {

                            }
                        })

                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.juntar_se_a_time),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "ENTRAR NO TIME",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onNavigate("editar_perfil_time")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.escrever),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "EDITAR PERFIL",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }

            Spacer(modifier = Modifier.height(20.dp))


        }

            LaunchedEffect(camposPreenchidosCorretamente) {
                if (!camposPreenchidosCorretamente) {
                    delay(5000)
                    camposPreenchidosCorretamente = true
                    onNavigate("lista_times")
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
                    Text(text = mensagemSucessoInputsPerfil.value)
                }
            }


    }
}