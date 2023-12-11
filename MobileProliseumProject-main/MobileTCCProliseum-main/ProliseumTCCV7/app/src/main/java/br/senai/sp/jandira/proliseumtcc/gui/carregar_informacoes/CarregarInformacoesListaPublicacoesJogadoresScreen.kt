package br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.service.primeira_sprint.RetrofitFactoryCadastro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemPostProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CarregarInformacoesListaPublicacoesJogadoresScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,

    //SharedViewModel GET MINHA POSTAGEM
    sharedGetMinhaPostagem: SharedGetMinhaPostagem,
    sharedGetMinhaPostagemUser: SharedGetMinhaPostagemUser,
    sharedGetMinhaPostagemUserPropostas: SharedGetMinhaPostagemUserPropostas,
    sharedGetMinhaPostagemPostProfile: SharedGetMinhaPostagemPostProfile,
    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 0.5 segundos antes de continuar
        delay(1)
        loading = false
    }

    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    // DESIGN DA TELA
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
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_proliseum),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "CARREGANDO",
                            fontFamily = customFontFamilyText,
                            fontSize = 32.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                        ,
                        color = RedProliseum
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_proliseum),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "CARREGANDO",
                            fontFamily = customFontFamilyText,
                            fontSize = 32.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                        ,
                        color = RedProliseum
                    )
                }
                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    val token = sharedViewModelTokenEId.token

                    if(token != null && token.isNotEmpty()){

                        val getMinhaPostagemService = RetrofitFactoryCadastro().getMinhaPostagemService()

                        getMinhaPostagemService.getMyPost("Bearer " + token).enqueue(object :
                            Callback<GetMinhaPostagem> {
                            override fun onResponse(call: Call<GetMinhaPostagem>, response: Response<GetMinhaPostagem>) {
                                if (response.isSuccessful) {

                                    Log.d("GET TIME BY ID CERTO", "Resposta bem-sucedida: ${response.code()}")
                                    val minhaPostagemResponse = response.body()

                                    val userMinhaPostagem = minhaPostagemResponse?.user
                                    val postProfileMinhaPostagem = minhaPostagemResponse?.postProfile

                                    sharedGetMinhaPostagem.user =  minhaPostagemResponse?.user
                                    sharedGetMinhaPostagem.postProfile = minhaPostagemResponse?.postProfile

                                    if(userMinhaPostagem != null){
                                        val idMinhaPostagem = userMinhaPostagem.id
                                        val nomeUsuarioMinhaPostagem  = userMinhaPostagem.nome_usuario
                                        val nomeCompletoMinhaPostagem  = userMinhaPostagem.nome_completo
                                        val emailMinhaPostagem  = userMinhaPostagem.email
                                        val dataNascimentoMinhaPostagem  = userMinhaPostagem.data_nascimento
                                        val generoMinhaPostagem  = userMinhaPostagem.genero
                                        val nicknameMinhaPostagem  = userMinhaPostagem.nickname
                                        val biografiaMinhaPostagem  = userMinhaPostagem.biografia

                                        sharedGetMinhaPostagemUser.id = userMinhaPostagem.id
                                        sharedGetMinhaPostagemUser.nome_usuario = userMinhaPostagem.nome_usuario
                                        sharedGetMinhaPostagemUser.nome_completo = userMinhaPostagem.nome_completo
                                        sharedGetMinhaPostagemUser.email = userMinhaPostagem.email
                                        sharedGetMinhaPostagemUser.data_nascimento = userMinhaPostagem.data_nascimento
                                        sharedGetMinhaPostagemUser.genero = userMinhaPostagem.genero
                                        sharedGetMinhaPostagemUser.nickname = userMinhaPostagem.nickname
                                        sharedGetMinhaPostagemUser.biografia = userMinhaPostagem.biografia





                                        val propostasMinhaPostagem  = userMinhaPostagem.propostas

                                        sharedGetMinhaPostagemUser.propostas = userMinhaPostagem.propostas

                                        if(propostasMinhaPostagem != null){
                                            for(propostas in propostasMinhaPostagem){
                                                val idPropostas = propostas.id
                                                val menssagemPropostas = propostas.menssagem

                                                sharedGetMinhaPostagemUserPropostas.id = propostas.id
                                                sharedGetMinhaPostagemUserPropostas.menssagem = propostas.menssagem
                                            }
                                        }

                                    }

                                    if(postProfileMinhaPostagem != null){
                                        val idPostProfile = postProfileMinhaPostagem.id
                                        val descricaoPostProfile = postProfileMinhaPostagem.descricao
                                        val jogoPostProfile = postProfileMinhaPostagem.jogo
                                        val funcaoPostProfile = postProfileMinhaPostagem.funcao
                                        val eloPostProfile = postProfileMinhaPostagem.elo
                                        val horaPostProfile = postProfileMinhaPostagem.hora
                                        val tipoPostProfile = postProfileMinhaPostagem.tipo
                                        val prosPostProfile = postProfileMinhaPostagem.pros

                                        sharedGetMinhaPostagemPostProfile.id = postProfileMinhaPostagem.id
                                        sharedGetMinhaPostagemPostProfile.descricao = postProfileMinhaPostagem.descricao
                                        sharedGetMinhaPostagemPostProfile.jogo = postProfileMinhaPostagem.jogo
                                        sharedGetMinhaPostagemPostProfile.funcao = postProfileMinhaPostagem.funcao
                                        sharedGetMinhaPostagemPostProfile.elo = postProfileMinhaPostagem.elo
                                        sharedGetMinhaPostagemPostProfile.hora = postProfileMinhaPostagem.hora
                                        sharedGetMinhaPostagemPostProfile.tipo = postProfileMinhaPostagem.tipo
                                        sharedGetMinhaPostagemPostProfile.pros = postProfileMinhaPostagem.pros
                                    }

                                    onNavigate("lista_de_publicacoes_jogadores")



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

                            override fun onFailure(call: Call<GetMinhaPostagem>, t: Throwable) {
                                // Trate o erro de falha na rede.
                                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
                            }

                        })


                    } else{
                        Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    }



                } else{
                    Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.logo_proliseum),
                                contentDescription = "",
                                modifier = Modifier.size(80.dp)
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Text(
                                text = "CARREGANDO",
                                fontFamily = customFontFamilyText,
                                fontSize = 32.sp,
                                fontWeight = FontWeight(900),
                                color = Color.White
                            )
                        }

                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(300.dp)
                                .width(300.dp)
                            ,
                            color = RedProliseum
                        )
                    }
                }
            }
        }
    }


}

