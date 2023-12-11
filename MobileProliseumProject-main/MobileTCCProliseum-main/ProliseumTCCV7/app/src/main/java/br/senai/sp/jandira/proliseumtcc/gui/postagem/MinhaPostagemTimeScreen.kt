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
import androidx.compose.foundation.lazy.LazyColumn
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
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemList
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacao
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoTimes
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
fun MinhaPostagemTimeScreen(
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

    val idMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.id
    val descricaoMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.descricao
    val jogoMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.jogo
    val funcaoMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.funcao
    val eloMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.elo
    val horaMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.hora
    val tipoMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.tipo
    val prosMinhaPublicacao = sharedGetTimeListaPostagensPublicacao.pros


    var
            idTimeEscolhido by remember { mutableStateOf(sharedGetTimeTeams.id) }

    Log.e("ID DO TIME","ID DO TIME ESCOLHIDO: ${idTimeEscolhido}")


    LaunchedEffect(sharedGetTimeTeams) {

        idTimeEscolhido = sharedGetTimeTeams.id

    }


    var publicacoesTimes by remember {
        mutableStateOf(listOf<GetTimePostagemListPublicacao>())
    }

    if(token != null && token.isNotEmpty()){

//                val perPage by remember { mutableStateOf<Int?>(null) }
//                val page by remember { mutableStateOf<Int?>(null) }
//                val name by remember { mutableStateOf<String?>("") }

        val perPage = null
        val page = null
        val hora = null
        val elo = null
        val funcao = null
        val tipo = 1


        sharedViewModelListaPublicacaoTimes.perPage = perPage
        sharedViewModelListaPublicacaoTimes.page = page
        sharedViewModelListaPublicacaoTimes.hora = hora
        sharedViewModelListaPublicacaoTimes.elo = elo
        sharedViewModelListaPublicacaoTimes.funcao = funcao
        sharedViewModelListaPublicacaoTimes.tipo = tipo



        val getListaPublicacoesTimes = RetrofitFactoryCadastro().getTimePostagemListService()

        getListaPublicacoesTimes.postagemTimeService(tipo, perPage, page, hora, elo, funcao).enqueue(object :
            Callback<GetTimePostagemList> {
            override fun onResponse(call: Call<GetTimePostagemList>, response: Response<GetTimePostagemList>) {
                if (response.isSuccessful) {
                    Log.d("SUCESSO LISTA POSTAGENS", "Resposta bem-sucedida na lista de postagens: ${response.code()}")

                    val listaPublicacoesTimesResponse = response.body()

                    Log.d("BODY LISTA POSTAGENS", "Resposta bem-sucedida na lista de postagens: ${response.body()}")

                    val publicacoesTimesList = listaPublicacoesTimesResponse?.post

                    if (publicacoesTimesList != null) {
                        publicacoesTimes = publicacoesTimesList
                    }

                    sharedGetTimeListaPostagens.post = publicacoesTimesList

                    if(publicacoesTimesList != null){
                        for(listPublicacoes in publicacoesTimesList){
                            val idPublicao = listPublicacoes.id
                            val descricaoPublicao = listPublicacoes.descricao
                            val jogoPublicao = listPublicacoes.jogo
                            val funcaoPublicao = listPublicacoes.funcao
                            val eloPublicao = listPublicacoes.elo
                            val horaPublicao = listPublicacoes.hora
                            val tipoPublicao = listPublicacoes.tipo
                            val prosPublicao = listPublicacoes.pros

                            sharedGetTimeListaPostagensPublicacao.id = listPublicacoes.id
                            sharedGetTimeListaPostagensPublicacao.descricao = listPublicacoes.descricao
                            sharedGetTimeListaPostagensPublicacao.jogo = listPublicacoes.jogo
                            sharedGetTimeListaPostagensPublicacao.funcao = listPublicacoes.funcao
                            sharedGetTimeListaPostagensPublicacao.elo = listPublicacoes.elo
                            sharedGetTimeListaPostagensPublicacao.hora = listPublicacoes.hora
                            sharedGetTimeListaPostagensPublicacao.tipo = listPublicacoes.tipo
                            sharedGetTimeListaPostagensPublicacao.pros = listPublicacoes.pros

                            val listaInformacoesDonoId = listPublicacoes.dono_id
                            val listaInformacoesTime = listPublicacoes.time

                            sharedGetTimeListaPostagensPublicacao.dono_id = listaInformacoesDonoId
                            sharedGetTimeListaPostagensPublicacao.time = listPublicacoes.time


                            if(listaInformacoesDonoId != null){
                                val idlistaInformacoesDonoId = listaInformacoesDonoId.id
                                val nomeUsuariolistaInformacoesDonoId = listaInformacoesDonoId.nome_usuario
                                val nomeCompletolistaInformacoesDonoId = listaInformacoesDonoId.nome_completo
                                val emaillistaInformacoesDonoId = listaInformacoesDonoId.email
                                val dataNascimentolistaInformacoesDonoId = listaInformacoesDonoId.data_nascimento
                                val generolistaInformacoesDonoId = listaInformacoesDonoId.genero
                                val nicknamelistaInformacoesDonoId = listaInformacoesDonoId.nickname
                                val biografialistaInformacoesDonoId = listaInformacoesDonoId.biografia

                                sharedGetTimeListaPostagensPublicacaoDonoId.id = listaInformacoesDonoId.id
                                sharedGetTimeListaPostagensPublicacaoDonoId.nome_usuario = listaInformacoesDonoId.nome_usuario
                                sharedGetTimeListaPostagensPublicacaoDonoId.nome_completo = listaInformacoesDonoId.nome_completo
                                sharedGetTimeListaPostagensPublicacaoDonoId.email = listaInformacoesDonoId.email
                                sharedGetTimeListaPostagensPublicacaoDonoId.data_nascimento = listaInformacoesDonoId.data_nascimento
                                sharedGetTimeListaPostagensPublicacaoDonoId.genero = listaInformacoesDonoId.genero
                                sharedGetTimeListaPostagensPublicacaoDonoId.nickname = listaInformacoesDonoId.nickname
                                sharedGetTimeListaPostagensPublicacaoDonoId.biografia = listaInformacoesDonoId.biografia
                            }

                            if(listaInformacoesTime != null){
                                val idListaInformacoesTime = listaInformacoesTime.id
                                val nomeTimeListaInformacoesTime = listaInformacoesTime.nome_time
                                val jogoListaInformacoesTime = listaInformacoesTime.jogo
                                val biografiaListaInformacoesTime = listaInformacoesTime.biografia

                                sharedGetTimeListaPostagensPublicacaoTime.id = listaInformacoesTime.id
                                sharedGetTimeListaPostagensPublicacaoTime.nome_time = listaInformacoesTime.nome_time
                                sharedGetTimeListaPostagensPublicacaoTime.jogo = listaInformacoesTime.jogo
                                sharedGetTimeListaPostagensPublicacaoTime.biografia = listaInformacoesTime.biografia

                                val jogadoresListaInformacoesTime = listaInformacoesTime.jogadores
                                val propostasListaInformacoesTime = listaInformacoesTime.propostas

                                sharedGetTimeListaPostagensPublicacaoTime.jogadores = listaInformacoesTime.jogadores
                                sharedGetTimeListaPostagensPublicacaoTime.propostas = listaInformacoesTime.propostas

                                if(jogadoresListaInformacoesTime != null){
                                    for(jogadoresListaInformacoesTimePublicaoTime in jogadoresListaInformacoesTime){
                                        val idJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.id
                                        val nicknameJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.nickname
                                        val jogoJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.jogo
                                        val funcaoJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.funcao
                                        val eloJogadoresListaInformacoesTimePublicaoTime = jogadoresListaInformacoesTimePublicaoTime.elo

                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.id = jogadoresListaInformacoesTimePublicaoTime.id
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.nickname = jogadoresListaInformacoesTimePublicaoTime.nickname
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.jogo = jogadoresListaInformacoesTimePublicaoTime.jogo
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.funcao = jogadoresListaInformacoesTimePublicaoTime.funcao
                                        sharedGetTimeListaPostagensPublicacaoTimeJogadores.elo = jogadoresListaInformacoesTimePublicaoTime.elo
                                    }
                                }

                                if(propostasListaInformacoesTime != null){
                                    for(propostasListaInformacoesTimePublicacaoTime in propostasListaInformacoesTime){
                                        val idPropostasListaInformacoesTimePublicacaoTime = propostasListaInformacoesTimePublicacaoTime.id
                                        val menssagemPropostasListaInformacoesTimePublicacaoTime = propostasListaInformacoesTimePublicacaoTime.menssagem

                                        sharedGetTimeListaPostagensPublicacaoTimePropostas.id = propostasListaInformacoesTimePublicacaoTime.id
                                        sharedGetTimeListaPostagensPublicacaoTimePropostas.menssagem = propostasListaInformacoesTimePublicacaoTime.menssagem
                                    }
                                }
                            }
                        }
                    }

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

            override fun onFailure(call: Call<GetTimePostagemList>, t: Throwable) {
                // Trate o erro de falha na rede.
                Log.d("PerfilUsuarioJogadorScreen", "Erro de rede: ${t.message}")
            }

        })

    } else{
        Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")

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

        Row(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp)
        ) {
            Icon(
                modifier = Modifier.clickable {
                    //rememberNavController.navigate("home")
                    onNavigate("escolher_time_para_visualizar_postagem_time")
                },
                painter = painterResource(id = R.drawable.arrow_back_32),
                contentDescription = stringResource(id = R.string.button_sair),
                tint = Color.White
            )
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "MINHA PUBLICAÇÃO",
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

            if(idTimeEscolhido != null && idTimeEscolhido != 0){


                val storage = Firebase.storage

                if (idTimeEscolhido != null && idTimeEscolhido != 0) {
                    // Utilize o ID do perfil aqui
                    imageRef.value = storage.reference.child("team/$idTimeEscolhido/profile")
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


            if(publicacoesTimes != null){

                val matchingTimeEscolhido = publicacoesTimes.filter { it.time?.id == idTimeEscolhido }

                if(matchingTimeEscolhido.isNotEmpty()){
                    //jogos
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .height(650.dp)
                            .padding(top = 20.dp),
                        content = {
                            items(publicacoesTimes.size){ index ->
                                val infoPublicacaoTime = publicacoesTimes[index]

                                val donoDaPublicacao = infoPublicacaoTime.dono_id ?: null
                                val timePublicacao = infoPublicacaoTime.time

                                val idInfoPublicacao = infoPublicacaoTime?.id ?: 0
                                val descricaoInfoPublicacao = infoPublicacaoTime?.descricao ?: ""
                                val jogoInfoPublicacao = infoPublicacaoTime?.jogo ?: ""
                                val funcaoInfoPublicacao = infoPublicacaoTime?.funcao ?: ""
                                val eloInfoPublicacao = infoPublicacaoTime?.elo ?: ""
                                val horaInfoPublicacao = infoPublicacaoTime?.hora ?: 0
                                val tipoInfoPublicacao = infoPublicacaoTime?.tipo ?: true
                                val prosInfoPublicacao = infoPublicacaoTime?.pros ?: ""


                                val idDonoPublicacaoJogador = donoDaPublicacao?.id ?: 0
                                val nomeUsuarioDonoPublicacaoJogador = donoDaPublicacao?.nome_usuario ?: ""
                                val nomeCompletoDonoPublicacaoJogador = donoDaPublicacao?.nome_completo ?: ""
                                val emailDonoPublicacaoJogador = donoDaPublicacao?.email ?: ""
                                val dataNascimentoDonoPublicacaoJogador = donoDaPublicacao?.data_nascimento ?: ""
                                val generoDonoPublicacaoJogador = donoDaPublicacao?.genero ?: 0
                                val nicknameDonoPublicacaoJogador = donoDaPublicacao?.nickname ?: ""
                                val biografiaDonoPublicacaoJogador = donoDaPublicacao?.biografia ?: ""

                                val idTimePublicaoTime = timePublicacao?.id ?: 0
                                val nomeTimeTimePublicaoTime = timePublicacao?.nome_time ?: ""
                                val jogoTimePublicaoTime = timePublicacao?.jogo ?: 0
                                val biografiaTimePublicaoTime = timePublicacao?.biografia ?: ""


                                if(
                                    infoPublicacaoTime.time?.id == idTimeEscolhido
                                ){
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
                                                    text = "${descricaoInfoPublicacao}",
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
                                                            if ("${eloInfoPublicacao}" == "0") painterResource(id = R.drawable.unranked_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "1") painterResource(id = R.drawable.iron_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "2") painterResource(id = R.drawable.bronze_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "3") painterResource(id = R.drawable.silver_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "4") painterResource(id = R.drawable.gold_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "5") painterResource(id = R.drawable.platinum_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "6") painterResource(id = R.drawable.emerald_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "7") painterResource(id = R.drawable.diamond_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "8") painterResource(id = R.drawable.master_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "9") painterResource(id = R.drawable.grandmaster_proliseum_elo)
                                                            else if ("${eloInfoPublicacao}" == "10") painterResource(id = R.drawable.challenger_proliseum_elo)
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
                                                            if ("${funcaoInfoPublicacao}" == "0") painterResource(
                                                                id = R.drawable.icontoplane
                                                            )
                                                            else if ("${funcaoInfoPublicacao}" == "1") painterResource(id = R.drawable.iconjungle)
                                                            else if ("${funcaoInfoPublicacao}" == "2") painterResource(id = R.drawable.iconmidlane)
                                                            else if ("${funcaoInfoPublicacao}" == "3") painterResource(id = R.drawable.iconsupport)
                                                            else if ("${funcaoInfoPublicacao}" == "4") painterResource(id = R.drawable.iconadc)
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
                                                        text = "${horaInfoPublicacao}",
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
                                                        text = "${prosInfoPublicacao}",
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

                                        fun deletarPublicacaoTime(
                                            sharedViewModelTokenEId: SharedViewTokenEId
                                        ){

                                            val token = sharedViewModelTokenEId.token

                                            // Obtenha o serviço Retrofit para editar o perfil do usuário
                                            val apagarPublicacaoTimeService = RetrofitFactoryCadastro().apagarPublicacaoTimeService()

                                            // Realize a chamada de API para editar o perfil
                                            apagarPublicacaoTimeService.deletarMinhaPostagemTime( "Bearer " + token, idTimeEscolhido )
                                                .enqueue(object : Callback<GenericResponse> {
                                                    override fun onResponse(
                                                        call: Call<GenericResponse>,
                                                        response: Response<GenericResponse>
                                                    ) {
                                                        if (response.isSuccessful) {
                                                            Log.d(
                                                                "DeletarPublicacaoTimeScreen",
                                                                "DeletarPublicacaoTimeScreen, Publicação do time apagada com sucesso: ${response.code()}"
                                                            )
                                                            // Trate a resposta bem-sucedida, se necessário
                                                        } else {
                                                            // Trate a resposta não bem-sucedida
                                                            Log.d(
                                                                "DeletarPublicacaoTimeScreen",
                                                                "DeletarPublicacaoTimeScreen, Falha ao tentar apagar a publição do time: ${response.code()}"
                                                            )
                                                            // Log do corpo da resposta (se necessário)
                                                            Log.d(
                                                                "DeletarPublicacaoTimeScreen",
                                                                "DeletarPublicacaoTimeScreen, Corpo da resposta: ${response.errorBody()?.string()}"
                                                            )
                                                        }
                                                    }

                                                    override fun onFailure(call: Call<GenericResponse>, t: Throwable) {
                                                        // Trate o erro de falha na rede.
                                                        Log.d("DeletarPublicacaoTimeScreen", "Erro de rede: ${t.message}")
                                                    }
                                                })
                                        }


                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.Center
                                        ){
                                            Button(
                                                onClick = {

                                                    sharedGetTimeListaPostagensPublicacao.id = idInfoPublicacao
                                                    sharedGetTimeListaPostagensPublicacao.descricao = descricaoInfoPublicacao
                                                    sharedGetTimeListaPostagensPublicacao.jogo = jogoInfoPublicacao
                                                    sharedGetTimeListaPostagensPublicacao.funcao = funcaoInfoPublicacao
                                                    sharedGetTimeListaPostagensPublicacao.elo = eloInfoPublicacao
                                                    sharedGetTimeListaPostagensPublicacao.tipo = tipoInfoPublicacao
                                                    sharedGetTimeListaPostagensPublicacao.pros = prosInfoPublicacao

                                                    sharedGetTimeListaPostagensPublicacaoTime.id = idTimePublicaoTime

                                                    onNavigate("carregar_tela_editar_publicacao_time")
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
                                                    deletarPublicacaoTime(
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
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(80.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Button(
                                            onClick = {

                                                sharedGetTimeListaPostagensPublicacaoTime.id = idTimePublicaoTime

                                                onNavigate("carregar_tela_inscritos_postagem_time")
                                            },
                                            modifier = Modifier
                                                .width(370.dp)
                                                .height(70.dp),
                                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                                            colors = ButtonDefaults.buttonColors(RedProliseum),
                                        ) {
                                            Text(
                                                text = "INSCRITOS",
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
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(top = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {

                                Log.e("ID DO TIME PARA CRIAR","id do time que vou criar ${idTimeEscolhido}")

                                sharedGetTimeTeams.id = idTimeEscolhido
                                onNavigate("criar_postagem_time")
                            },
                            modifier = Modifier
                                .width(370.dp)
                                .height(160.dp),
                            shape = RoundedCornerShape(20.dp, 20.dp, 20.dp, 20.dp),
                            colors = ButtonDefaults.buttonColors(RedProliseum),
                        ) {
                            Text(
                                text = "Você não tem postagem com esse time, deseja criar uma?",
                                color = Color.White,
                                modifier = Modifier.padding(5.dp),
                                fontWeight = FontWeight(900),
                                fontFamily = customFontFamilyText,
                                fontSize = 20.sp
                            )
                        }
                    }
                }

            }

        }

    }

}

