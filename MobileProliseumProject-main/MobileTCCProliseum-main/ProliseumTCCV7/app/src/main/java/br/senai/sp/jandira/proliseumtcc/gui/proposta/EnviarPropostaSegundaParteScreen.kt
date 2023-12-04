package br.senai.sp.jandira.proliseumtcc.gui.proposta

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import br.senai.sp.jandira.proliseumtcc.components.RedeSocial
import br.senai.sp.jandira.proliseumtcc.firebase.StorageTeamUtil
import br.senai.sp.jandira.proliseumtcc.gui.listagem.verificarIdDoTimeFilter
import br.senai.sp.jandira.proliseumtcc.model.BodyPostProposta
import br.senai.sp.jandira.proliseumtcc.model.DeletarTimeResponse
import br.senai.sp.jandira.proliseumtcc.model.PostProposta
import br.senai.sp.jandira.proliseumtcc.model.getTime
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams
import br.senai.sp.jandira.proliseumtcc.model.infoAtualizarTime
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdDoUsuario
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeTeamsJogadoresPerfilId
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnviarPropostaSegundaParteScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    sharedViewModelPerfil: SharedViewModelPerfil,
    sharedViewModelUser: SharedViewModelUser,
    sharedViewModelPerfilPropostas: SharedViewModelPerfilPropostas,
    sharedViewModelPerfilPropostasDe: SharedViewModelPerfilPropostasDe,
    sharedViewModelPerfilPropostasDeJogadores: SharedViewModelPerfilPropostasDeJogadores,
    sharedViewModelPerfilPropostasDePropostas: SharedViewModelPerfilPropostasDePropostas,


    // SharedViewModel GET TIME BY ID
    sharedGetTime: SharedGetTime,
    sharedGetTimeTeams: SharedGetTimeTeams,
    sharedGetTimeTeamsJogadores: SharedGetTimeTeamsJogadores,
    sharedGetTimeTeamsJogadoresPerfilId: SharedGetTimeTeamsJogadoresPerfilId,
    sharedGetTimeDono: SharedGetTimeDono,
    sharedGetTimeTeamsPropostas: SharedGetTimeTeamsPropostas,

    sharedGetProfileByIdDoUsuario: SharedGetProfileByIdDoUsuario,
    sharedGetProfileByIdUser: SharedGetProfileByIdUser,
    sharedGetProfileByIdUserRedeSocial: SharedGetProfileByIdUserRedeSocial,
    sharedGetProfileByIdUserHighlights: SharedGetProfileByIdUserHighlights,

    sharedGetProfileByIdPlayerProfile: SharedGetProfileByIdPlayerProfile,
    sharedGetProfileByIdPlayerProfileTimeAtual: SharedGetProfileByIdPlayerProfileTimeAtual,
    sharedGetProfileByIdPlayerProfileTimeAtualJogadores: SharedGetProfileByIdPlayerProfileTimeAtualJogadores,
    sharedGetProfileByIdPlayerProfileTimeAtualPropostas: SharedGetProfileByIdPlayerProfileTimeAtualPropostas,

    onNavigate: (String) -> Unit
) {

    val token = sharedViewModelTokenEId.token

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

    val customFontFamily = FontFamily(
        Font(R.font.font_title)
    )
    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    var minhaListaDeTimes by remember {
        mutableStateOf(listOf<getTimeTeams>())
    }

    val idUser = sharedViewModelUser.id

    var idTimeEscolhido by remember { mutableStateOf(sharedGetTimeTeams.id) }
    var idUserSharedState by remember { mutableStateOf(sharedGetProfileByIdUser.id) }

    Log.e("ID DO TIME","ID DO TIME ESCOLHIDO: ${idTimeEscolhido}")
    Log.e("ID DO JOGADOR","ID DO JOGADOR ESCOLHIDO: ${idUserSharedState}")


    var menssagemState by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(sharedGetProfileByIdUser, sharedGetTimeTeams) {

        idUserSharedState = sharedGetProfileByIdUser.id
        idTimeEscolhido = sharedGetTimeTeams.id

    }



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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            // Botão de retorno
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
                    tint = Color.White
                )
            }
        }

        // Conteúdo do formulário
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp), // Ajuste o valor do topo para centralizar verticalmente
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(
                                BlackTransparentProliseum,
                                BlackTransparentProliseum
                            )
                        ),
                        shape = RoundedCornerShape(50.dp, 50.dp, 0.dp, 0.dp)
                    )
                    .padding(40.dp)

            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Menssagem:",
                            fontFamily = customFontFamilyText,
                            fontSize = 24.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    //var nomeOrganizacaoState by remember { mutableStateOf("") }


                    OutlinedTextField(
                        value = menssagemState,
                        onValueChange = { newNomeTime -> menssagemState = newNomeTime },
                        modifier = Modifier
                            .width(370.dp)
                            .height(250.dp),
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        label = {
                            Text(
                                text = "Nome do time:",
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
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){


                        fun criarProposta(
                            sharedViewModelTokenEId: SharedViewTokenEId,
                            menssagemState: String,
                        ) {
                            val token = sharedViewModelTokenEId.token

                            // Criar uma instância da classe EditarPerfilUsuario com os dados a serem atualizados
                            val editarPerfilTimeData = BodyPostProposta(
                                menssage = menssagemState,
                            )


                            // Obtenha o serviço Retrofit para editar o perfil do usuário
                            val postPropostaService = RetrofitFactoryCadastro().postPropostaService()

                            // Realize a chamada de API para editar o perfil
                            postPropostaService.criarProposta("Bearer " + token, idTimeEscolhido, idUserSharedState, editarPerfilTimeData)
                                    .enqueue(object : Callback<PostProposta> {
                                        override fun onResponse(
                                            call: Call<PostProposta>,
                                            response: Response<PostProposta>
                                        ) {
                                            if (response.isSuccessful) {
                                                Log.d(
                                                    "EnviarPropostaSegundaParteScreen",
                                                    "Proposta enviada com sucesso: ${response.code()}"
                                                )
                                                // Trate a resposta bem-sucedida, se necessário
                                            } else {
                                                // Trate a resposta não bem-sucedida
                                                Log.d(
                                                    "EnviarPropostaSegundaParteScreen",
                                                    "Falha ao enviar proposta para um usuário: ${response.code()}"
                                                )
                                                // Log do corpo da resposta (se necessário)
                                                Log.d(
                                                    "EnviarPropostaSegundaParteScreen",
                                                    "Corpo da resposta: ${response.errorBody()?.string()}"
                                                )
                                            }
                                        }

                                        override fun onFailure(call: Call<PostProposta>, t: Throwable) {
                                            // Trate o erro de falha na rede.
                                            Log.d("EnviarPropostaSegundaParteScreen", "Erro de rede: ${t.message}")
                                        }
                                    })

                        }



                        Button(
                            onClick = {
                                if( token != null && idTimeEscolhido != null && idUserSharedState != null){

                                    criarProposta(
                                        sharedViewModelTokenEId = sharedViewModelTokenEId,
                                        menssagemState = menssagemState
                                    )

                                    onNavigate("carregar_informacoes_perfil_usuario")

                                }

                            },
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(73.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum)

                        ) {
                            Text(
                                text = "SALVAR",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                color = Color.White,
                                fontFamily = customFontFamilyText,
                                fontWeight = FontWeight(900),
                            )
                        }


                    }

                }
            }
        }


    }

}