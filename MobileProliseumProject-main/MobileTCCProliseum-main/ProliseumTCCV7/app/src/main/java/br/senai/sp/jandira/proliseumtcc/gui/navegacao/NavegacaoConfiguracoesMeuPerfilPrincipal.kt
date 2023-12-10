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
import androidx.compose.runtime.remember
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
import br.senai.sp.jandira.proliseumtcc.firebase.StorageUtil
import br.senai.sp.jandira.proliseumtcc.model.GenericResponse
import br.senai.sp.jandira.proliseumtcc.model.PerfilUsuario
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.SairDoTimeResponse
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.PerfilUsuarioService
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseFirstGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetRedeSocialDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun NavegacaoConfiguracoesMeuPerfilPrincipal(
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
    // Define a família da fonte personalizada
    val customFontFamily = FontFamily(
        Font(R.font.font_title) // Substitua pelo nome da fonte personalizada
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    val informacoesUserPlayerProfile = sharedViewModelPerfil.playerProfile

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
                        onNavigate("perfil_usuario_jogador")
                    },
                    painter = painterResource(id = R.drawable.arrow_back_32),
                    contentDescription = stringResource(id = R.string.button_sair),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "GERENCIAR MEU PERFIL",
                    fontFamily = customFontFamilyText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight(900),
                    color = Color.White
                )
            }


            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onNavigate("lista_de_publicacoes_jogadores")
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
                    onNavigate("editar_perfil_usuario_padrao_1")
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

            Spacer(modifier = Modifier.height(10.dp))

            if(informacoesUserPlayerProfile == null){
                Button(
                    onClick = {
                        onNavigate("cadastro_usuario_jogador")
                    },
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(73.dp),
                    colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

                ) {
                    Image(
                        painter = painterResource(id = R.drawable.proplayer),
                        contentDescription = stringResource(id = R.string.button_proximo),
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 20.dp))
                    Text(
                        text = stringResource(id = R.string.jogador),
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontFamily = customFontFamilyText,
                        fontWeight = FontWeight(900),
                    )
                }


                Spacer(modifier = Modifier.height(10.dp))
            } else if(informacoesUserPlayerProfile != null){
                Log.e("JA TEM DADOS JOGADOR", "Você ja tem perfil de jogador!")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    onNavigate("editar_perfil_jogador_1")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.editar_jogo_icon),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "EDITAR INFORMAÇÕES DE JOGADOR",
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
                    onNavigate("criar_rede_social")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.redes_sociais),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "CRIAR REDE SOCIAL",
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
                    onNavigate("deletar_rede_social")
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.botao_deletar_icon),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "DELETAR REDE SOCIAL",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            fun sairDoTimeFunction(
                sharedViewModelTokenEId: SharedViewTokenEId
            ){

                val token = sharedViewModelTokenEId.token

                // Obtenha o serviço Retrofit para editar o perfil do usuário
                val sairDoTimeService = RetrofitFactoryCadastro().sairDoTimeService()

                // Realize a chamada de API para editar o perfil
                sairDoTimeService.sairDoTime( "Bearer " + token )
                    .enqueue(object : Callback<SairDoTimeResponse> {
                        override fun onResponse(
                            call: Call<SairDoTimeResponse>,
                            response: Response<SairDoTimeResponse>
                        ) {
                            if (response.isSuccessful) {
                                Log.d(
                                    "SairDoTimeScreen",
                                    "SairDoTimeScreen, Saiu do time com sucesso: ${response.code()}"
                                )


                                camposPreenchidosCorretamente = false
                                mensagemSucessoInputsPerfil.value = "Saiu do time com sucesso!"


                            } else {

                                camposPreenchidosCorretamente = false
                                mensagemSucessoInputsPerfil.value = "Falha ao sair do time!"

                                Log.d(
                                    "SairDoTimeScreen",
                                    "SairDoTimeScreen, Falha ao tentar sair  do time: ${response.code()}"
                                )

                                Log.d(
                                    "SairDoTimeScreen",
                                    "SairDoTimeScreen, Corpo da resposta: ${response.errorBody()?.string()}"
                                )
                            }
                        }

                        override fun onFailure(call: Call<SairDoTimeResponse>, t: Throwable) {
                            // Trate o erro de falha na rede.
                            Log.d("DeletarPublicacaoTimeScreen", "Erro de rede: ${t.message}")
                        }
                    })
            }


            Button(
                onClick = {
                    sairDoTimeFunction(
                        sharedViewModelTokenEId
                    )


                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(73.dp),
                colors = ButtonDefaults.buttonColors(AzulEscuroProliseum)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.sair_do_time),
                    contentDescription = stringResource(id = R.string.button_proximo),
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    text = "SAIR DO TIME",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontFamily = customFontFamilyText,
                    fontWeight = FontWeight(900),
                )
            }

            Spacer(modifier = Modifier.height(10.dp))


        }

        LaunchedEffect(camposPreenchidosCorretamente) {
            if (!camposPreenchidosCorretamente) {
                delay(3000)
                camposPreenchidosCorretamente = true
                onNavigate("carregar_informacoes_perfil_usuario")
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


