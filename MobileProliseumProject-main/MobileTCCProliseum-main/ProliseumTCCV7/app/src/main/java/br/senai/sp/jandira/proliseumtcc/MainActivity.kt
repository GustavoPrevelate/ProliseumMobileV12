package br.senai.sp.jandira.proliseumtcc

import CarregarInformacoesOutroTimeListaJogadoresScreen
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaJogadores
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaPostagensJogadores
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaTimes
import br.senai.sp.jandira.proliseumtcc.ui.theme.ProliseumTCCTheme
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelDataAndGenderCadastroUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelImageUri
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfil
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizador
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelSimpleDataCadastroUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsTimeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelGetMyTeamsUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDeJogadoresAtivos
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMyTeamsUserPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeById
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsPropostas
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelNomeJogadorListaJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilJogadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOrganizadorOutro
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelPerfilOutro
import br.senai.sp.jandira.proliseumtcc.gui.futuramente.CampeonatoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesDeletarOrganizacaoScreen
//import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesDoTimeByIdScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesListaTimesScreen
//import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilJogadorMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOrganizacaoScreen
//import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOutroJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.HomeScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDeJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDePublicacoesDeJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDePublicacoesDeTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDeTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.LoginScreen
import br.senai.sp.jandira.proliseumtcc.gui.NotificacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavegacaoConfiguracoesMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavigationPrincipalScreen
import br.senai.sp.jandira.proliseumtcc.gui.futuramente.PremiumScreen
import br.senai.sp.jandira.proliseumtcc.gui.PropostasScreen
import br.senai.sp.jandira.proliseumtcc.gui.StartScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroOrganizadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroUsuarioJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroDadosPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.CadastroGeneroEDataNascimentoScreen
import br.senai.sp.jandira.proliseumtcc.gui.cadastro.FinalizarCadastroUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarFiltragemListaJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarFiltragemListaPublicacoesJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarFiltragemListaPublicoesJogadoresCarregarScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarFiltragemListaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesListaPublicacoesJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesMinhaPublicacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOutroJogadorListaTimesQueFoiEscolhidoScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarTelaNotificacoesScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarTelaRedesSociaisScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOutroJogadorListaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarInformacoesPerfilOutroJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarTelaEditarPublicacaoTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarTelaInscritosPostagemTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarTelaListagemDeInscricoesScreen
import br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes.CarregarTelaListagemDePropostasScreen
import br.senai.sp.jandira.proliseumtcc.gui.criar.CriarTimeScreen
//import br.senai.sp.jandira.proliseumtcc.gui.deletar.ApagarPublicacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.deletar.DeletarOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.minhas_redes_sociais.CriarRedeSocialScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesMeuPerfilPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarInformacoesTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarMinhaPublicacaoJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.editar_perfil.EditarMinhaPublicacaoTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.gerenciar_time.EntrarNoTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.EscolherTimeParaCriarPostagemTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.EscolherTimeParaVisualizarPostagemTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDeInscricoesParaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDePropostasRecebidasParaJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.listagem.ListaDeTimesMeusTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.meus_highlights.CriarHighLightScreen
import br.senai.sp.jandira.proliseumtcc.gui.meus_highlights.EditarHighLightScreen
import br.senai.sp.jandira.proliseumtcc.gui.meus_highlights.ListaMeusHighLightsScreen
import br.senai.sp.jandira.proliseumtcc.gui.minhas_redes_sociais.DeletarRedeSocialScreen
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavegacaoConfiguracoesMeuPerfilPrincipal
import br.senai.sp.jandira.proliseumtcc.gui.navegacao.NavegacaoConfiguracoesMeuTimeListaJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.ListaHighlightsOutrosUsuariosListaJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.ListaHighlightsOutrosUsuariosScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroJogadorListaJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroJogadorListaTimesScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroTimeListaJogadoresScreen
import br.senai.sp.jandira.proliseumtcc.gui.outros_perfis.PerfilDeOutroTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilJogadorDoMeuTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilOrganizacaoScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.perfis.PerfilUsuarioPadraoScreen
import br.senai.sp.jandira.proliseumtcc.gui.postagem.CriarPostagemTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.postagem.MinhaPostagemScreen
import br.senai.sp.jandira.proliseumtcc.gui.postagem.MinhaPostagemTimeScreen
import br.senai.sp.jandira.proliseumtcc.gui.postagem.PostagemJogadorScreen
import br.senai.sp.jandira.proliseumtcc.gui.proposta.EnviarPropostaScreen
import br.senai.sp.jandira.proliseumtcc.gui.proposta.EnviarPropostaSegundaParteScreen
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetMyTimeUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeams
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAGetTimeFilterByUserTeamsPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeAdded
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedAdicionarJogadorAoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneira
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagem
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemPostProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetMinhaPostagemUserPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneira
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetPeneiraAceptedTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdDoUsuario
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfile
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtual
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdPlayerProfileTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUser
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetProfileByIdUserRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeByIdTeamsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagens
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTime
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedGetTimeListaPostagensPublicacaoTimePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeral
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeralDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeralDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedPropostasRecebidasGeralDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoHighlights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoHighlightsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostasDe
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedResponsePostRedeSocialDonoRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoJogadores
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewModelListaPublicacaoTimes
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
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewNotificacao
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewNotificacaoProposta
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseFirstGetHighLights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseFirstGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetHighLights
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetHighLightsDono
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetRedeSocial
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewResponseGetRedeSocialDono


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION


        setContent {

            ProliseumTCCTheme {
                MainScreen()
            }
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        //VÁRIAVEL PARA LEMBRAR DA TELA ATUAL
        var currentScreen by remember { mutableStateOf("start") }


        /*****************************************************************************************/
        // SHARED VIEW MODEL
        //SERVE PARA FAZER O COMPARTILHAMENTO DE INFORMAÇÕES ENTRE AS ACTIVITY
        /*****************************************************************************************/

        // SharedViewModel TOKEN

        val sharedViewModelTokenEId = remember { SharedViewTokenEId() }

        // SharedViewModel IMAGEM COMPARTILHADA

        val sharedViewModelImageUri = remember { SharedViewModelImageUri() }

        // SharedViewModel DADOS DE CADASTRO DE USUÁRIO

        val sharedViewModelSimpleDataCadastroUser = remember { SharedViewModelSimpleDataCadastroUser() }
        val sharedViewModelDataAndGenderCadastroUser = remember { SharedViewModelDataAndGenderCadastroUser() }

        // SharedViewModel DADOS DE PERFIS

        val sharedViewModelPerfil = remember { SharedViewModelPerfil() }

        val sharedViewModelUser = remember { SharedViewModelUser() }
        val sharedViewModelPerfilPropostas = remember { SharedViewModelPerfilPropostas() }
        val sharedViewModelPerfilPropostasDe = remember { SharedViewModelPerfilPropostasDe() }
        val sharedViewModelPerfilPropostasDeJogadores = remember { SharedViewModelPerfilPropostasDeJogadores() }
        val sharedViewModelPerfilPropostasDePropostas = remember { SharedViewModelPerfilPropostasDePropostas() }
        val sharedViewModelPerfilPropostasDeRedeSocial = remember { SharedViewModelPerfilPropostasDeRedeSocial() }
        val sharedViewModelPerfilPropostasDeHighlights = remember { SharedViewModelPerfilPropostasDeHighlights() }


        val sharedViewModelPlayerProfile = remember { SharedViewModelPlayerProfile() }
        val sharedViewModelPlayerProfileTimeAtual = remember { SharedViewModelPlayerProfileTimeAtual() }
        val sharedViewModelPlayerProfileTimeAtualJogadores = remember { SharedViewModelPlayerProfileTimeAtualJogadores() }
        val sharedViewModelPlayerProfileTimeAtualPropostas = remember { SharedViewModelPlayerProfileTimeAtualPropostas() }

        val sharedViewModelPerfilJogador = remember { SharedViewModelPerfilJogador() }
        val sharedViewModelPerfilOrganizador = remember { SharedViewModelPerfilOrganizador() }


        // SharedViewModel OUTROS DADOS DE PERFIS

        val sharedViewModelPerfilEditarOutro = remember { SharedViewModelPerfilOutro() }
        val sharedViewModelPerfilJogadorOutro = remember { SharedViewModelPerfilJogadorOutro() }
        val sharedViewModelPerfilOrganizadorOutro = remember { SharedViewModelPerfilOrganizadorOutro() }

        // SharedViewModel GET MY TEAMS USER
        // SharedViewModel GET MY TEAMS GERAL
        val sharedGetMyTeamsGeral = remember { SharedGetMyTeamsGeral() }

        val sharedViewModelGetMyTeamsUser = remember { SharedViewModelGetMyTeamsUser() }
        val sharedViewModelGetMyTeamsUserPropostas = remember { SharedViewModelGetMyTeamsUserPropostas() }
        val sharedViewModelGetMyTeamsUserPropostasDe = remember { SharedGetMyTeamsUserPropostasDe() }
        val sharedViewModelGetMyTeamsUserPropostasDeJogadores = remember { SharedGetMyTeamsUserPropostasDeJogadores() }
        val sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos = remember { SharedGetMyTeamsUserPropostasDeJogadoresAtivos() }
        val sharedViewModelGetMyTeamsUserPropostasDePropostas = remember { SharedGetMyTeamsUserPropostasDePropostas() }

        // SharedViewModel GET MY TEAMS TIME

        val sharedViewModelGetMyTeamsTime = remember { SharedViewModelGetMyTeamsTime() }
        val sharedViewModelGetMyTeamsTimeJogadores = remember { SharedViewModelGetMyTeamsTimeJogadores() }
        val sharedViewModelGetMyTeamsTimeJogadoresAtivos = remember { SharedGetMyTeamsTimeJogadoresAtivos() }
        val sharedViewModelGetMyTeamsTimePropostas = remember { SharedViewModelGetMyTeamsTimePropostas() }

        // SharedViewModel GET LISTA DE JOGADORES

        val sharedViewModelNomeJogadorListaJogadores = remember { SharedViewModelNomeJogadorListaJogadores() }
        val sharedViewModelGetListaJogadores = remember { SharedViewModelGetListaJogadores() }
        val sharedViewModelGetListaJogadoresList = remember { SharedViewModelGetListaJogadoresList() }
        val sharedViewModelGetListaJogadoresInfoPerfil = remember { SharedViewModelGetListaJogadoresInfoPerfil() }
        val sharedViewModelGetListaJogadoresTimeAtual = remember { SharedViewModelGetListaJogadoresTimeAtual() }
        val sharedViewModelGetListaJogadoresDentroDeTime = remember { SharedViewModelGetListaJogadoresDentroDeTime() }
        val sharedViewModelGetListaJogadoresDentroDeTimeList = remember { SharedViewModelGetListaJogadoresDentroDeTimeList() }
        val sharedViewModelGetListaJogadoresPropostasList = remember { SharedViewModelGetListaJogadoresPropostasList() }
        val sharedViewModelGetListaJogadoresPropostasRecebidas = remember { SharedViewModelGetListaJogadoresPropostasRecebidas() }

        // SharedViewModel GET TIME BY ID

        val  sharedGetTimeById = remember { SharedGetTimeById() }
        val  sharedGetTimeByIdTeams = remember { SharedGetTimeByIdTeams() }
        val sharedGetTimeByIdTeamsDono = remember { SharedGetTimeByIdTeamsDono() }
        val  sharedGetTimeByIdTeamsJogadores = remember { SharedGetTimeByIdTeamsJogadores() }
        val  sharedGetTimeByIdTeamsJogadoresPerfilId = remember { SharedGetTimeByIdTeamsJogadoresPerfilId() }
        val  sharedGetTimeByIdTeamsOrganizacao = remember { SharedGetTimeByIdTeamsOrganizacao() }
        val  sharedGetTimeByIdOrganizacaoDonoId = remember { SharedGetTimeByIdOrganizacaoDonoId() }
        val  sharedGetTimeByIdTeamsPropostas = remember { SharedGetTimeByIdTeamsPropostas() }

        // SharedViewModel GET TIME FILTER

        val  sharedGetTime = remember { SharedGetTime() }
        val  sharedGetTimeTeams = remember { SharedGetTimeTeams() }
        val  sharedGetTimeTeamsJogadores = remember { SharedGetTimeTeamsJogadores() }
        val  sharedGetTimeTeamsJogadoresPerfilId = remember { SharedGetTimeTeamsJogadoresPerfilId() }
//        val  sharedGetTimeTeamsOrganizacao = remember { SharedGetTimeTeamsOrganizacao() }
//        val  sharedGetTimeOrganizacaoDonoId = remember { SharedGetTimeDono() }
        val  sharedGetTimeDono = remember { SharedGetTimeDono() }
        val  sharedGetTimeTeamsPropostas = remember { SharedGetTimeTeamsPropostas() }


        // SharedViewModel GET POSTAGEM JOGADOR

        val sharedViewModelListaPublicacaoJogadores = remember { SharedViewModelListaPublicacaoJogadores() }

        val sharedGetListaPostagens = remember { SharedGetListaPostagens() }
        val sharedGetListaPostagensPublicacao = remember { SharedGetListaPostagensPublicacao() }
        val sharedGetListaPostagensPublicacaoDonoId = remember { SharedGetListaPostagensPublicacaoDonoId() }

        // SharedViewModel GET MINHA POSTAGEM

        val sharedGetMinhaPostagem = remember { SharedGetMinhaPostagem() }
        val sharedGetMinhaPostagemUser = remember { SharedGetMinhaPostagemUser() }
        val sharedGetMinhaPostagemUserPropostas = remember { SharedGetMinhaPostagemUserPropostas() }
        val sharedGetMinhaPostagemPostProfile = remember { SharedGetMinhaPostagemPostProfile() }

        // SharedViewModel GET NOTIFICACAO

        val sharedViewNotificacao = remember { SharedViewNotificacao() }
        val sharedViewNotificacaoProposta = remember { SharedViewNotificacaoProposta() }

        // SharedViewModel POST REDE SOCIAL

        val sharedResponsePostRedeSocial = remember { SharedResponsePostRedeSocial() }
        val sharedResponsePostRedeSocialDono = remember { SharedResponsePostRedeSocialDono() }
        val sharedResponsePostRedeSocialDonoHighlights = remember { SharedResponsePostRedeSocialDonoHighlights() }
        val sharedResponsePostRedeSocialDonoHighlightsDono = remember { SharedResponsePostRedeSocialDonoHighlightsDono() }
        val sharedResponsePostRedeSocialDonoPropostas = remember { SharedResponsePostRedeSocialDonoPropostas() }
        val sharedResponsePostRedeSocialDonoPropostasDe = remember { SharedResponsePostRedeSocialDonoPropostasDe() }
        val sharedResponsePostRedeSocialDonoPropostasDeJogadores = remember { SharedResponsePostRedeSocialDonoPropostasDeJogadores() }
        val sharedResponsePostRedeSocialDonoPropostasDePropostas = remember { SharedResponsePostRedeSocialDonoPropostasDePropostas() }
        val sharedResponsePostRedeSocialDonoRedeSocial = remember { SharedResponsePostRedeSocialDonoRedeSocial() }

        // SharedViewModel GET REDE SOCIAL
        val sharedViewResponseFirstGetRedeSocial = remember { SharedViewResponseFirstGetRedeSocial() }
        val sharedViewResponseGetRedeSocial = remember { SharedViewResponseGetRedeSocial() }
        val sharedViewResponseGetRedeSocialDono = remember { SharedViewResponseGetRedeSocialDono() }

        // SharedViewModel GET HIGH LIGHTS

        val sharedViewResponseFirstGetHighLights = remember { SharedViewResponseFirstGetHighLights() }
        val sharedViewResponseGetHighLights = remember { SharedViewResponseGetHighLights() }
        val sharedViewResponseGetHighLightsDono = remember { SharedViewResponseGetHighLightsDono() }

        // SharedViewModel PUT ENTRAR NO TIME

        val sharedAdicionarJogadorAoTimeAdded = remember { SharedAdicionarJogadorAoTimeAdded() }
        val sharedAdicionarJogadorAoTime = remember { SharedAdicionarJogadorAoTime() }
        val sharedAdicionarJogadorAoTimeDono = remember { SharedAdicionarJogadorAoTimeDono() }
        val sharedAdicionarJogadorAoTimeJogadores = remember { SharedAdicionarJogadorAoTimeJogadores() }
        val sharedAdicionarJogadorAoTimeJogadoresPerfilId = remember { SharedAdicionarJogadorAoTimeJogadoresPerfilId() }
        val sharedAdicionarJogadorAoTimeJogadoresTimeAtual = remember { SharedAdicionarJogadorAoTimeJogadoresTimeAtual() }
        val saredAdicionarJogadorAoTimeJogadoresTimeAtualJogadores = remember { SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadores() }
        val sharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas = remember { SharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas() }
        val sharedAdicionarJogadorAoTimePropostas = remember { SharedAdicionarJogadorAoTimePropostas() }

        // SharedViewModel GET PROFILE BY ID

        val sharedGetProfileByIdDoUsuario = remember { SharedGetProfileByIdDoUsuario() }
        val sharedGetProfileByIdUser = remember { SharedGetProfileByIdUser() }
        val sharedGetProfileByIdUserRedeSocial = remember { SharedGetProfileByIdUserRedeSocial() }
        val sharedGetProfileByIdUserHighlights = remember { SharedGetProfileByIdUserHighlights() }

        val sharedGetProfileByIdPlayerProfile = remember { SharedGetProfileByIdPlayerProfile() }
        val sharedGetProfileByIdPlayerProfileTimeAtual = remember { SharedGetProfileByIdPlayerProfileTimeAtual() }
        val sharedGetProfileByIdPlayerProfileTimeAtualJogadores = remember { SharedGetProfileByIdPlayerProfileTimeAtualJogadores() }
        val sharedGetProfileByIdPlayerProfileTimeAtualPropostas = remember { SharedGetProfileByIdPlayerProfileTimeAtualPropostas() }

        // SharedViewModel GET PROPOSTAS RECEBIDAS


        val sharedPropostasRecebidas = remember { SharedPropostasRecebidas() }
        val sharedPropostasRecebidasGeral = remember { SharedPropostasRecebidasGeral() }
        val sharedPropostasRecebidasGeralDe = remember { SharedPropostasRecebidasGeralDe() }
        val sharedPropostasRecebidasGeralDeJogadores = remember { SharedPropostasRecebidasGeralDeJogadores() }
        val sharedPropostasRecebidasGeralDePropostas = remember { SharedPropostasRecebidasGeralDePropostas() }

        // SharedViewModel GET PUBLICAÇÕES TIMES

        val sharedViewModelListaPublicacaoTimes = remember { SharedViewModelListaPublicacaoTimes() }

        val sharedGetTimeListaPostagens = remember { SharedGetTimeListaPostagens() }
        val sharedGetTimeListaPostagensPublicacao = remember { SharedGetTimeListaPostagensPublicacao() }
        val sharedGetTimeListaPostagensPublicacaoDonoId = remember { SharedGetTimeListaPostagensPublicacaoDonoId() }
        val sharedGetTimeListaPostagensPublicacaoTime = remember { SharedGetTimeListaPostagensPublicacaoTime() }
        val sharedGetTimeListaPostagensPublicacaoTimeJogadores = remember { SharedGetTimeListaPostagensPublicacaoTimeJogadores() }
        val sharedGetTimeListaPostagensPublicacaoTimePropostas = remember { SharedGetTimeListaPostagensPublicacaoTimePropostas() }

        // SharedViewModel GET PENEIRA


        val sharedGetPeneira = remember { SharedGetPeneira() }
        val sharedGetPeneiraAcepted = remember { SharedGetPeneiraAcepted() }
        val sharedGetPeneiraAceptedJogadores = remember { SharedGetPeneiraAceptedJogadores() }
        val sharedGetPeneiraAceptedJogadoresPerfilId = remember { SharedGetPeneiraAceptedJogadoresPerfilId() }
        val sharedGetPeneiraAceptedTime = remember { SharedGetPeneiraAceptedTime() }
        val sharedGetPeneiraAceptedTimeJogadores = remember { SharedGetPeneiraAceptedTimeJogadores() }
        val sharedGetPeneiraAceptedTimePropostas = remember { SharedGetPeneiraAceptedTimePropostas() }

        // SharedViewModel GET MY TIME ATUALIZADO

        val sharedAGetMyTime = remember { SharedAGetMyTime() }
        val sharedAGetMyTimeTime = remember { SharedAGetMyTimeTime() }
        val sharedAGetMyTimeTimeJogadores = remember { SharedAGetMyTimeTimeJogadores() }
        val sharedAGetMyTimeTimePropostas = remember { SharedAGetMyTimeTimePropostas() }

        val sharedAGetMyTimeUser = remember { SharedAGetMyTimeUser() }
        val sharedAGetMyTimeUserHighlights = remember { SharedAGetMyTimeUserHighlights() }
        val sharedAGetMyTimeUserPropostas = remember { SharedAGetMyTimeUserPropostas() }
        val sharedAGetMyTimeUserRedeSocial = remember { SharedAGetMyTimeUserRedeSocial() }

        // SharedViewModel GET FILTER TIMES BY USER

        val sharedAGetTimeFilterByUser = remember { SharedAGetTimeFilterByUser() }
        val sharedAGetTimeFilterByUserTeams = remember { SharedAGetTimeFilterByUserTeams() }
        val sharedAGetTimeFilterByUserTeamsJogadores = remember { SharedAGetTimeFilterByUserTeamsJogadores() }
        val sharedAGetTimeFilterByUserTeamsPropostas = remember { SharedAGetTimeFilterByUserTeamsPropostas() }

        // SharedViewModel PUT PENEIRA OU ENTRAR NA PENEIRA

        val sharedEntrarNaPeneira = remember { SharedEntrarNaPeneira() }
        val sharedEntrarNaPeneiraAcepted = remember { SharedEntrarNaPeneiraAcepted() }
        val sharedEntrarNaPeneiraAceptedJogadores = remember { SharedEntrarNaPeneiraAceptedJogadores() }
        val sharedEntrarNaPeneiraAceptedJogadoresPerfilId = remember { SharedEntrarNaPeneiraAceptedJogadoresPerfilId() }
        val sharedEntrarNaPeneiraAceptedJogadoresTimeAtual = remember { SharedEntrarNaPeneiraAceptedJogadoresTimeAtual() }
        val sharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores = remember { SharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores() }
        val sharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas = remember { SharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas() }

        // SharedViewModel FILTRAGEM LISTAGEM DE TIMES

        val sharedFiltragemListaTimes = remember { SharedFiltragemListaTimes() }

        // SharedViewModel FILTRAGEM LISTAGEM DE JOGADORES

        val sharedFiltragemListaJogadores = remember {  SharedFiltragemListaJogadores() }

        // SharedViewModel FILTRAGEM LISTAGEM DE PUBLICAÇÕES DE TIMES

        val sharedFiltragemListaPostagensJogadores = remember { SharedFiltragemListaPostagensJogadores() }



        /**********************************************************************************************************************************/

        // TELAS DO PROJETO

        /**********************************************************************************************************************************/

        // TELA DE INÍCIO
        val startScreen: @Composable () -> Unit = {
            StartScreen {
                currentScreen = it
            }
        }

        // TELA DE LOGIN
        val loginScreen: @Composable () -> Unit = {
            LoginScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DE DADOS PADRAO
        val cadastroDadosPadraoScreen: @Composable () -> Unit = {
            CadastroDadosPadraoScreen(sharedViewModelSimpleDataCadastroUser) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO GENERO E DATA DE NASCIMENTO
        val cadastroGeneroEDataNascimentoScreen: @Composable () -> Unit = {
            CadastroGeneroEDataNascimentoScreen(
                sharedViewModelDataAndGenderCadastroUser,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // TELA PARA FINALIZAR CADASTRO DO USUARIO PADRAO
        val finalizarCadastroUsuarioPadraoScreen: @Composable () -> Unit = {
            FinalizarCadastroUsuarioPadraoScreen(
                sharedViewModelDataAndGenderCadastroUser ,
                sharedViewModelSimpleDataCadastroUser,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // TELA HOME
        val homeScreen: @Composable () -> Unit = {
            HomeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,
                sharedViewModelPerfilPropostasDeRedeSocial,
                sharedViewModelPerfilPropostasDeHighlights,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                //SharedViewModel GET MY TIME ATUALIZADO
                sharedAGetMyTime,
                sharedAGetMyTimeTime,
                sharedAGetMyTimeTimeJogadores,
                sharedAGetMyTimeTimePropostas,

                sharedAGetMyTimeUser,
                sharedAGetMyTimeUserHighlights,
                sharedAGetMyTimeUserPropostas,
                sharedAGetMyTimeUserRedeSocial,

                sharedViewModelListaPublicacaoTimes,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,

                // SharedViewModel LISTA POSTAGEM JOGADORES

                sharedViewModelListaPublicacaoJogadores,

                sharedGetListaPostagens,
                sharedGetListaPostagensPublicacao,
                sharedGetListaPostagensPublicacaoDonoId,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,

                sharedAGetTimeFilterByUser,
                sharedAGetTimeFilterByUserTeams,
                sharedAGetTimeFilterByUserTeamsJogadores,
                sharedAGetTimeFilterByUserTeamsPropostas,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES DE PERFIL DE USUÁRIO PADRÃO
        val carregarInformacoesPerfilUsuarioPadraoScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilUsuarioPadraoScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,
                sharedViewModelPerfilPropostasDeRedeSocial,
                sharedViewModelPerfilPropostasDeHighlights,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                //SharedViewModel GET MY TIME ATUALIZADO
                sharedAGetMyTime,
                sharedAGetMyTimeTime,
                sharedAGetMyTimeTimeJogadores,
                sharedAGetMyTimeTimePropostas,

                sharedAGetMyTimeUser,
                sharedAGetMyTimeUserHighlights,
                sharedAGetMyTimeUserPropostas,
                sharedAGetMyTimeUserRedeSocial,
            ) {
                currentScreen = it
            }
        }

        // TELA PERFIL DE USUÁRIO PADRÃO
        val perfilUsuarioPadraoScreen: @Composable () -> Unit = {
            PerfilUsuarioPadraoScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,
                sharedViewModelPerfilPropostasDeRedeSocial,
                sharedViewModelPerfilPropostasDeHighlights,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                sharedResponsePostRedeSocial,
                sharedResponsePostRedeSocialDono,
                sharedResponsePostRedeSocialDonoHighlights,
                sharedResponsePostRedeSocialDonoHighlightsDono,
                sharedResponsePostRedeSocialDonoPropostas,
                sharedResponsePostRedeSocialDonoPropostasDe,
                sharedResponsePostRedeSocialDonoPropostasDeJogadores,
                sharedResponsePostRedeSocialDonoPropostasDePropostas,
                sharedResponsePostRedeSocialDonoRedeSocial,

                sharedViewResponseFirstGetRedeSocial,
                sharedViewResponseGetRedeSocial,
                sharedViewResponseGetRedeSocialDono,
            ) {
                currentScreen = it
            }
        }

        // TELA EDITAR INFORMAÇÕES DO MEU PERFIL DE USUÁRIO PADRÃO
        val editarInformacoesMeuPerfilPadraoScreen: @Composable () -> Unit = {
            EditarInformacoesMeuPerfilPadraoScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelImageUri
            ) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO PRINCIPAL ENTRE AS TELAS
        val navegacaoPrincipalScreen: @Composable () -> Unit = {
            NavigationPrincipalScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }


        // TELA DE CADASTRO DO USUÁRIO JOGADOR
        val cadastroUsuarioJogadorScreen: @Composable () -> Unit = {
            CadastroUsuarioJogadorScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CADASTRO DA ORGANIZACAO
        val cadastroOrganizacaoScreen: @Composable () -> Unit = {
            CadastroOrganizadorScreen(sharedViewModelTokenEId, sharedViewModelPerfilOrganizador, sharedViewModelImageUri) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DA ORGANIZAÇÃO
        val perfilOrganizacaoScreen: @Composable () -> Unit = {
            PerfilOrganizacaoScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,
                sharedGetMyTeamsGeral,
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas
            ) {
                currentScreen = it
            }
        }

        // TELA DE EDITAR INFORMAÇÕES DA ORGANIZAÇÃO
        val editarInformacoesOrganizacaoScreen: @Composable () -> Unit = {
            EditarInformacoesOrganizacaoScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilOrganizador,
                sharedViewModelImageUri
            ) {
                currentScreen = it
            }
        }

        // TELA DE EDITAR INFORMAÇÕES DO JOGADOR
        val editarInformacoesJogadorScreen: @Composable () -> Unit = {
            EditarInformacoesJogadorScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,
                sharedViewModelPerfilPropostasDeRedeSocial,
                sharedViewModelPerfilPropostasDeHighlights,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
            ) {
                currentScreen = it
            }
        }

        // TELA DE DELETAR ORGANIZAÇÃO
        val deletarOrganizacaoScreen: @Composable () -> Unit = {
            DeletarOrganizacaoScreen(
                sharedViewModelTokenEId,

                sharedViewModelUser,

                sharedViewModelPerfilOrganizador

            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES PARA DELETAR A ORGANIZAÇÃO
        val carregarInformacoesDeletarOrganizacaoScreen: @Composable () -> Unit = {
            CarregarInformacoesDeletarOrganizacaoScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CRIAR TIME
        val criarTimeScreen: @Composable () -> Unit = {
            CriarTimeScreen(sharedViewModelTokenEId) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR INFORMAÇÕES DO PERFIL DA ORGANIZAÇÃO
        val carregarInformacoesPerfilOrganizacaoScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilOrganizacaoScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,
                sharedGetMyTeamsGeral,
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas
                ) {
                currentScreen = it
            }
        }

        // TELA PERFIL DE TIME
        val perfilTimeScreen: @Composable () -> Unit = {
            PerfilTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsOrganizacao,
                sharedGetTimeByIdOrganizacaoDonoId,
                sharedGetTimeByIdTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA EDITAR INFORMAÇÕES DO TIME
        val editarInformacoesTimeScreen: @Composable () -> Unit = {
            EditarInformacoesTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,


                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
                sharedViewModelImageUri,
            ) {
                currentScreen = it
            }
        }

        // TELA LISTAGEM DE JOGADORES
        val listaDeJogadoresScreen: @Composable () -> Unit = {
            ListaDeJogadoresScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                sharedFiltragemListaJogadores,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PROPOSTAS
        val propostasScreen: @Composable () -> Unit = {
            PropostasScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE LISTAGEM DE PUBLICAÇÕES DE JOGADORES
        val listaDePublicacoesDeJogadoresScreen: @Composable () -> Unit = {
            ListaDePublicacoesDeJogadoresScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,
                sharedViewModelListaPublicacaoJogadores,
                sharedGetListaPostagens,
                sharedGetListaPostagensPublicacao,
                sharedGetListaPostagensPublicacaoDonoId,

                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                sharedAGetTimeFilterByUser,
                sharedAGetTimeFilterByUserTeams,
                sharedAGetTimeFilterByUserTeamsJogadores,
                sharedAGetTimeFilterByUserTeamsPropostas,

                sharedFiltragemListaPostagensJogadores
            ) {
                currentScreen = it
            }
        }

        // TELA DE PUBLICAÇÕES DE TIMES
        val listaDePublicacoesDeTimesScreen: @Composable () -> Unit = {
            ListaDePublicacoesDeTimesScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,


                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,


                sharedViewModelListaPublicacaoTimes,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,

                sharedEntrarNaPeneira,
                sharedEntrarNaPeneiraAcepted,
                sharedEntrarNaPeneiraAceptedJogadores,
                sharedEntrarNaPeneiraAceptedJogadoresPerfilId,
                sharedEntrarNaPeneiraAceptedJogadoresTimeAtual,
                sharedEntrarNaPeneiraAceptedJogadoresTimeAtualJogadores,
                sharedEntrarNaPeneiraAceptedJogadoresTimeAtualPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CAMPEONATO
        // /* FUTURAMENTE */
        val campeonatoScreen: @Composable () -> Unit = {
            CampeonatoScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA PREMIUM
        // /* FUTURAMENTE */
        val premiumScreen: @Composable () -> Unit = {
            PremiumScreen(

            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO JOGADOR
        val perfilDeOutroJogadorScreen: @Composable () -> Unit = {
            PerfilDeOutroJogadorScreen(
                sharedViewModelTokenEId,

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                //Shared REDE SOCIAL

                sharedResponsePostRedeSocial,
                sharedResponsePostRedeSocialDono,
                sharedResponsePostRedeSocialDonoHighlights,
                sharedResponsePostRedeSocialDonoHighlightsDono,
                sharedResponsePostRedeSocialDonoPropostas,
                sharedResponsePostRedeSocialDonoPropostasDe,
                sharedResponsePostRedeSocialDonoPropostasDeJogadores,
                sharedResponsePostRedeSocialDonoPropostasDePropostas,
                sharedResponsePostRedeSocialDonoRedeSocial,

                sharedViewResponseFirstGetRedeSocial,
                sharedViewResponseGetRedeSocial,
                sharedViewResponseGetRedeSocialDono,
            ) {
                currentScreen = it
            }
        }

//        // TELA DE CARREGAR INFORMAÇÕES DE PERFIL DE OUTRO JOGADOR
//        val carregarInformacoesPerfilOutroJogadorScreen: @Composable () -> Unit = {
//            CarregarInformacoesPerfilOutroJogadorScreen(
//                sharedViewModelTokenEId,
//                sharedViewModelPerfilEditarOutro,
//                sharedViewModelPerfilJogadorOutro,
//                sharedViewModelPerfilOrganizadorOutro,
//
//                // SharedViewModel GET MY TEAMS GERAL
//                sharedGetMyTeamsGeral,
//
//                // SharedViewModelGetMyTeams de USUARIO
//                sharedViewModelGetMyTeamsUser,
//                sharedViewModelGetMyTeamsUserPropostas,
//                sharedViewModelGetMyTeamsUserPropostasDe,
//                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
//                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
//                sharedViewModelGetMyTeamsUserPropostasDePropostas,
//
//                // SharedViewModelGetMyTeams de TIME
//                sharedViewModelGetMyTeamsTime,
//                sharedViewModelGetMyTeamsTimeJogadores,
//                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
//                sharedViewModelGetMyTeamsTimePropostas,
//
//                sharedViewModelNomeJogadorListaJogadores,
//                sharedViewModelGetListaJogadores,
//                sharedViewModelGetListaJogadoresList,
//                sharedViewModelGetListaJogadoresInfoPerfil,
//                sharedViewModelGetListaJogadoresTimeAtual,
//                sharedViewModelGetListaJogadoresDentroDeTime,
//                sharedViewModelGetListaJogadoresDentroDeTimeList,
//                sharedViewModelGetListaJogadoresPropostasList,
//                sharedViewModelGetListaJogadoresPropostasRecebidas,
//            ) {
//                currentScreen = it
//            }
//        }

//        // TELA CARREGAR INFORMAÇÕES DO TIME PELO ID
//        val carregarInformacoesDoTimeByIdScreen: @Composable () -> Unit = {
//            CarregarInformacoesDoTimeByIdScreen(
//                sharedViewModelTokenEId,
//                sharedViewModelPerfilEditarOutro,
//                sharedViewModelPerfilJogadorOutro,
//                sharedViewModelPerfilOrganizadorOutro,
//
//                // SharedViewModel GET MY TEAMS GERAL
//                sharedGetMyTeamsGeral,
//
//                // SharedViewModelGetMyTeams de USUARIO
//                sharedViewModelGetMyTeamsUser,
//                sharedViewModelGetMyTeamsUserPropostas,
//                sharedViewModelGetMyTeamsUserPropostasDe,
//                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
//                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
//                sharedViewModelGetMyTeamsUserPropostasDePropostas,
//
//                // SharedViewModelGetMyTeams de TIME
//                sharedViewModelGetMyTeamsTime,
//                sharedViewModelGetMyTeamsTimeJogadores,
//                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
//                sharedViewModelGetMyTeamsTimePropostas,
//
//                sharedViewModelNomeJogadorListaJogadores,
//                sharedViewModelGetListaJogadores,
//                sharedViewModelGetListaJogadoresList,
//                sharedViewModelGetListaJogadoresInfoPerfil,
//                sharedViewModelGetListaJogadoresTimeAtual,
//                sharedViewModelGetListaJogadoresDentroDeTime,
//                sharedViewModelGetListaJogadoresDentroDeTimeList,
//                sharedViewModelGetListaJogadoresPropostasList,
//                sharedViewModelGetListaJogadoresPropostasRecebidas,
//
//                sharedGetTimeById,
//                sharedGetTimeByIdTeams,
//                sharedGetTimeByIdTeamsJogadores,
//                sharedGetTimeByIdTeamsJogadoresPerfilId,
//                sharedGetTimeByIdTeamsOrganizacao,
//                sharedGetTimeByIdOrganizacaoDonoId,
//                sharedGetTimeByIdTeamsPropostas,
//            ) {
//                currentScreen = it
//            }
//        }

//        // TELA DE CARREGAR AS INFORMAÇÕES DE PERFIL DE UM JOGADOR DO MEU TIME
//        val carregarInformacoesPerfilJogadorMeuTimeScreen: @Composable () -> Unit = {
//            CarregarInformacoesPerfilJogadorMeuTimeScreen(
//                sharedViewModelTokenEId,
//                sharedViewModelPerfilEditarOutro,
//                sharedViewModelPerfilJogadorOutro,
//                sharedViewModelPerfilOrganizadorOutro,
//                sharedViewModelPerfil,
//                sharedViewModelPerfilJogador,
//                sharedViewModelPerfilOrganizador,
//
//                // SharedViewModel GET MY TEAMS GERAL
//                sharedGetMyTeamsGeral,
//
//                // SharedViewModelGetMyTeams de USUARIO
//                sharedViewModelGetMyTeamsUser,
//                sharedViewModelGetMyTeamsUserPropostas,
//                sharedViewModelGetMyTeamsUserPropostasDe,
//                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
//                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
//                sharedViewModelGetMyTeamsUserPropostasDePropostas,
//
//                // SharedViewModelGetMyTeams de TIME
//                sharedViewModelGetMyTeamsTime,
//                sharedViewModelGetMyTeamsTimeJogadores,
//                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
//                sharedViewModelGetMyTeamsTimePropostas,
//
//                sharedViewModelNomeJogadorListaJogadores,
//                sharedViewModelGetListaJogadores,
//                sharedViewModelGetListaJogadoresList,
//                sharedViewModelGetListaJogadoresInfoPerfil,
//                sharedViewModelGetListaJogadoresTimeAtual,
//                sharedViewModelGetListaJogadoresDentroDeTime,
//                sharedViewModelGetListaJogadoresDentroDeTimeList,
//                sharedViewModelGetListaJogadoresPropostasList,
//                sharedViewModelGetListaJogadoresPropostasRecebidas,
//
//                // SharedViewModel GET TIME BY ID
//                sharedGetTimeById,
//                sharedGetTimeByIdTeams,
//                sharedGetTimeByIdTeamsJogadores,
//                sharedGetTimeByIdTeamsJogadoresPerfilId,
//                sharedGetTimeByIdTeamsOrganizacao,
//                sharedGetTimeByIdOrganizacaoDonoId,
//                sharedGetTimeByIdTeamsPropostas,
//            ) {
//                currentScreen = it
//            }
//        }

        // TELA DE PERFIL DE UM JOGADOR DENTRO DE UM DO(S) MEU(S) TIME(S)
        val perfilJogadorDoMeuTimeScreen: @Composable () -> Unit = {
            PerfilJogadorDoMeuTimeScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsOrganizacao,
                sharedGetTimeByIdOrganizacaoDonoId,
                sharedGetTimeByIdTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE LISTAGEM DE TIMES
        val listaDeTimesScreen: @Composable () -> Unit = {
            ListaDeTimesScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedFiltragemListaTimes,
            ) {
                currentScreen = it
            }
        }

        // TELA DE CARREGAR AS INFORMAÇÕES DE LISTAGEM DE TIMES
        val carregarInformacoesListaTimesScreen: @Composable () -> Unit = {
            CarregarInformacoesListaTimesScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val perfilDeOutroTimeScreen: @Composable () -> Unit = {
            PerfilDeOutroTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        // TELA DE NAVEGAÇÃO DE CONFIGURAÇÕES/GERENCIAMENTO DO MEU TIME
        val navegacaoConfiguracoesMeuTimeScreen: @Composable () -> Unit = {
            NavegacaoConfiguracoesMeuTimeScreen(
                sharedViewModelTokenEId,
                sharedViewModelUser,
                sharedGetTimeTeams,
            ) {
                currentScreen = it
            }
        }


        // TELA DE NAVEGAÇÃO DE CONFIGURAÇÕES/GERENCIAMENTO DO MEU TIME
        val carregarInformacoesPerfilOutroJogadorListaTimesScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilOutroJogadorListaTimesScreen(
                sharedViewModelTokenEId,

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,
            ) {
                currentScreen = it
            }
        }



        // TELA DE PERFIL DE OUTRO TIME
        val perfilDeOutroJogadorListaTimesScreen: @Composable () -> Unit = {
            PerfilDeOutroJogadorListaTimesScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val postagemJogadorScreen: @Composable () -> Unit = {
            PostagemJogadorScreen(
                sharedViewModelTokenEId
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val carregarInformacoesMinhaPublicacaoScreen: @Composable () -> Unit = {
            CarregarInformacoesMinhaPublicacaoScreen(
                sharedViewModelTokenEId,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val minhaPostagemScreen: @Composable () -> Unit = {
            MinhaPostagemScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val editarMinhaPublicacaoJogadorScreen: @Composable () -> Unit = {
            EditarMinhaPublicacaoJogadorScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val notificacaoScreen: @Composable () -> Unit = {
            NotificacaoScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,
                sharedViewNotificacao,
                sharedViewNotificacaoProposta,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val navegacaoConfiguracoesMeuPerfilPrincipal: @Composable () -> Unit = {
            NavegacaoConfiguracoesMeuPerfilPrincipal(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelImageUri,
                sharedResponsePostRedeSocial,
                sharedResponsePostRedeSocialDono,
                sharedResponsePostRedeSocialDonoHighlights,
                sharedResponsePostRedeSocialDonoHighlightsDono,
                sharedResponsePostRedeSocialDonoPropostas,
                sharedResponsePostRedeSocialDonoPropostasDe,
                sharedResponsePostRedeSocialDonoPropostasDeJogadores,
                sharedResponsePostRedeSocialDonoPropostasDePropostas,
                sharedResponsePostRedeSocialDonoRedeSocial,

                sharedViewResponseFirstGetRedeSocial,
                sharedViewResponseGetRedeSocial,
                sharedViewResponseGetRedeSocialDono,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val carregarTelaNotificacoesScreen: @Composable () -> Unit = {
            CarregarTelaNotificacoesScreen(
                sharedViewModelTokenEId,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val criarRedeSocialScreen: @Composable () -> Unit = {
            CriarRedeSocialScreen(
                sharedViewModelTokenEId,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val deletarRedeSocialScreen: @Composable () -> Unit = {
            DeletarRedeSocialScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,
                sharedViewModelPerfilPropostasDeRedeSocial,
                sharedViewModelPerfilPropostasDeHighlights,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                sharedResponsePostRedeSocial,
                sharedResponsePostRedeSocialDono,
                sharedResponsePostRedeSocialDonoHighlights,
                sharedResponsePostRedeSocialDonoHighlightsDono,
                sharedResponsePostRedeSocialDonoPropostas,
                sharedResponsePostRedeSocialDonoPropostasDe,
                sharedResponsePostRedeSocialDonoPropostasDeJogadores,
                sharedResponsePostRedeSocialDonoPropostasDePropostas,
                sharedResponsePostRedeSocialDonoRedeSocial,

                sharedViewResponseFirstGetRedeSocial,
                sharedViewResponseGetRedeSocial,
                sharedViewResponseGetRedeSocialDono,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val carregarTelaRedesSociaisScreen: @Composable () -> Unit = {
            CarregarTelaRedesSociaisScreen(
                sharedViewModelTokenEId,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val listaMeusHighLightsScreen: @Composable () -> Unit = {
            ListaMeusHighLightsScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,


                sharedViewModelListaPublicacaoJogadores,

                sharedGetListaPostagens,
                sharedGetListaPostagensPublicacao,
                sharedGetListaPostagensPublicacaoDonoId,

                sharedViewResponseFirstGetHighLights,
                sharedViewResponseGetHighLights,
                sharedViewResponseGetHighLightsDono,
            ) {
                currentScreen = it
            }
        }

        // TELA DE PERFIL DE OUTRO TIME
        val editarHighLightScreen: @Composable () -> Unit = {
            EditarHighLightScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelImageUri,

                sharedViewResponseFirstGetHighLights,
                sharedViewResponseGetHighLights,
                sharedViewResponseGetHighLightsDono,
            ) {
                currentScreen = it
            }
        }


        // TELA DE PERFIL DE OUTRO TIME
        val criarHighLightScreen: @Composable () -> Unit = {
            CriarHighLightScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelImageUri,

                sharedViewResponseFirstGetHighLights,
                sharedViewResponseGetHighLights,
                sharedViewResponseGetHighLightsDono,
            ) {
                currentScreen = it
            }
        }

        val entrarNoTimeScreen: @Composable () -> Unit = {
            EntrarNoTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
                sharedViewModelImageUri,

                // SharedViewModel PUT ADICIONAR JOGADOR AO TIME
                sharedAdicionarJogadorAoTimeAdded,
                sharedAdicionarJogadorAoTime,
                sharedAdicionarJogadorAoTimeDono,
                sharedAdicionarJogadorAoTimeJogadores,
                sharedAdicionarJogadorAoTimeJogadoresPerfilId,
                sharedAdicionarJogadorAoTimeJogadoresTimeAtual,
                saredAdicionarJogadorAoTimeJogadoresTimeAtualJogadores,
                sharedAdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas,
                sharedAdicionarJogadorAoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val enviarPropostaScreen: @Composable () -> Unit = {
            EnviarPropostaScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        val enviarPropostaSegundaParteScreen: @Composable () -> Unit = {
            EnviarPropostaSegundaParteScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,
            ) {
                currentScreen = it
            }
        }

        val listaHighlightsOutrosUsuariosScreen: @Composable () -> Unit = {
            ListaHighlightsOutrosUsuariosScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,


                sharedViewModelListaPublicacaoJogadores,

                sharedGetListaPostagens,
                sharedGetListaPostagensPublicacao,
                sharedGetListaPostagensPublicacaoDonoId,

                sharedViewResponseFirstGetHighLights,
                sharedViewResponseGetHighLights,
                sharedViewResponseGetHighLightsDono,


                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,
            ) {
                currentScreen = it
            }
        }

        val carregarInformacoesPerfilOutroJogadorScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilOutroJogadorScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,
            ) {
                currentScreen = it
            }
        }

        val perfilDeOutroJogadorListaJogadoresScreen: @Composable () -> Unit = {
            PerfilDeOutroJogadorListaJogadoresScreen(
                sharedViewModelTokenEId,

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                //Shared REDE SOCIAL

                sharedResponsePostRedeSocial,
                sharedResponsePostRedeSocialDono,
                sharedResponsePostRedeSocialDonoHighlights,
                sharedResponsePostRedeSocialDonoHighlightsDono,
                sharedResponsePostRedeSocialDonoPropostas,
                sharedResponsePostRedeSocialDonoPropostasDe,
                sharedResponsePostRedeSocialDonoPropostasDeJogadores,
                sharedResponsePostRedeSocialDonoPropostasDePropostas,
                sharedResponsePostRedeSocialDonoRedeSocial,

                sharedViewResponseFirstGetRedeSocial,
                sharedViewResponseGetRedeSocial,
                sharedViewResponseGetRedeSocialDono,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

            ) {
                currentScreen = it
            }
        }

        val listaHighlightsOutrosUsuariosListaJogadoresScreen: @Composable () -> Unit = {
            ListaHighlightsOutrosUsuariosListaJogadoresScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,


                sharedViewModelListaPublicacaoJogadores,

                sharedGetListaPostagens,
                sharedGetListaPostagensPublicacao,
                sharedGetListaPostagensPublicacaoDonoId,

                sharedViewResponseFirstGetHighLights,
                sharedViewResponseGetHighLights,
                sharedViewResponseGetHighLightsDono,


                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                ) {
                currentScreen = it
            }
        }

        val carregarInformacoesOutroTimeListajogadoresScreen: @Composable () -> Unit = {
            CarregarInformacoesOutroTimeListaJogadoresScreen(
                sharedViewModelTokenEId,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsDono,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsPropostas,

                ) {
                currentScreen = it
            }
        }


        val perfilDeOutroTimeListaJogadoresScreen: @Composable () -> Unit = {
            PerfilDeOutroTimeListaJogadoresScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,


                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedGetTimeById,
                sharedGetTimeByIdTeams,
                sharedGetTimeByIdTeamsDono,
                sharedGetTimeByIdTeamsJogadores,
                sharedGetTimeByIdTeamsJogadoresPerfilId,
                sharedGetTimeByIdTeamsPropostas,

                ) {
                currentScreen = it
            }
        }

        val navegacaoConfiguracoesMeuTimeListaJogadoresScreen: @Composable () -> Unit = {
            NavegacaoConfiguracoesMeuTimeListaJogadoresScreen(
                sharedViewModelTokenEId,
                sharedViewModelUser,
                sharedGetTimeTeams,

                ) {
                currentScreen = it
            }
        }

        val carregarInformacoesPerfilOutroJogadorListaTimesQueFoiEscolhidoScreen: @Composable () -> Unit = {
            CarregarInformacoesPerfilOutroJogadorListaTimesQueFoiEscolhidoScreen(
                sharedViewModelTokenEId,

                // SharedViewModel GET TIME FILTER
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedGetProfileByIdDoUsuario,
                sharedGetProfileByIdUser,
                sharedGetProfileByIdUserRedeSocial,
                sharedGetProfileByIdUserHighlights,

                sharedGetProfileByIdPlayerProfile,
                sharedGetProfileByIdPlayerProfileTimeAtual,
                sharedGetProfileByIdPlayerProfileTimeAtualJogadores,
                sharedGetProfileByIdPlayerProfileTimeAtualPropostas,

                sharedGetTimeByIdTeamsJogadoresPerfilId,
                ) {
                currentScreen = it
            }
        }

//        val apagarPublicacaoScreen: @Composable () -> Unit = {
//            ApagarPublicacaoScreen(
//                sharedViewModelTokenEId,
//
//                sharedViewModelUser,
//
//                sharedViewModelPerfilOrganizador,
//            ) {
//                currentScreen = it
//            }
//        }

        val carregarInformacoesListaPublicacoesJogadoresScreen: @Composable () -> Unit = {
            CarregarInformacoesListaPublicacoesJogadoresScreen(
                sharedViewModelTokenEId,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,
            ) {
                currentScreen = it
            }
        }

        val listaDePropostasRecebidasParaJogadoresScreen: @Composable () -> Unit = {
            ListaDePropostasRecebidasParaJogadoresScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,


                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                sharedPropostasRecebidas,
                sharedPropostasRecebidasGeral,
                sharedPropostasRecebidasGeralDe,
                sharedPropostasRecebidasGeralDeJogadores,
                sharedPropostasRecebidasGeralDePropostas,
            ) {
                currentScreen = it
            }
        }

        val carregarTelaListagemDePropostasScreen: @Composable () -> Unit = {
            CarregarTelaListagemDePropostasScreen(
                sharedViewModelTokenEId,
            ) {
                currentScreen = it
            }
        }

        val escolherTimeParaCriarPostagemTimeScreen: @Composable () -> Unit = {
            EscolherTimeParaCriarPostagemTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedViewModelListaPublicacaoTimes,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val criarPostagemTimeScreen: @Composable () -> Unit = {
            CriarPostagemTimeScreen(
                sharedViewModelTokenEId,

                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,
            ) {
                currentScreen = it
            }
        }

        val escolherTimeParaVisualizarPostagemTimeScreen: @Composable () -> Unit = {
            EscolherTimeParaVisualizarPostagemTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedViewModelListaPublicacaoTimes,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val minhaPostagemTimeScreen: @Composable () -> Unit = {
            MinhaPostagemTimeScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,


                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,

                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedViewModelListaPublicacaoTimes,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }


        val editarMinhaPublicacaoTimeScreen: @Composable () -> Unit = {
            EditarMinhaPublicacaoTimeScreen(
                sharedViewModelTokenEId,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,

                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedViewModelListaPublicacaoTimes,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val carregarTelaEditarPublicacaoTimeScreen: @Composable () -> Unit = {
            CarregarTelaEditarPublicacaoTimeScreen(
                sharedViewModelTokenEId,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val listaDeInscricoesParaTimesScreen: @Composable () -> Unit = {
            ListaDeInscricoesParaTimesScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,


                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedGetPeneira,
                sharedGetPeneiraAcepted,
                sharedGetPeneiraAceptedJogadores,
                sharedGetPeneiraAceptedJogadoresPerfilId,
                sharedGetPeneiraAceptedTime,
                sharedGetPeneiraAceptedTimeJogadores,
                sharedGetPeneiraAceptedTimePropostas,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val carregarTelaInscritosPostagemTimeScreen: @Composable () -> Unit = {
            CarregarTelaInscritosPostagemTimeScreen(
                sharedViewModelTokenEId,

                sharedGetTimeListaPostagens,
                sharedGetTimeListaPostagensPublicacao,
                sharedGetTimeListaPostagensPublicacaoDonoId,
                sharedGetTimeListaPostagensPublicacaoTime,
                sharedGetTimeListaPostagensPublicacaoTimeJogadores,
                sharedGetTimeListaPostagensPublicacaoTimePropostas,
            ) {
                currentScreen = it
            }
        }

        val carregarTelaListagemDeInscricoesScreen: @Composable () -> Unit = {
            CarregarTelaListagemDeInscricoesScreen(
                sharedViewModelTokenEId,

            ) {
                currentScreen = it
            }
        }

        val carregarFiltragemListaTimesScreen: @Composable () -> Unit = {
            CarregarFiltragemListaTimesScreen(
                sharedViewModelTokenEId,
                sharedFiltragemListaTimes,

                ) {
                currentScreen = it
            }
        }

        val listaDeTimesMeusTimesScreen: @Composable () -> Unit = {
            ListaDeTimesMeusTimesScreen(
                sharedViewModelTokenEId,

                sharedViewModelPerfil,
                sharedViewModelUser,
                sharedViewModelPerfilPropostas,
                sharedViewModelPerfilPropostasDe,
                sharedViewModelPerfilPropostasDeJogadores,
                sharedViewModelPerfilPropostasDePropostas,

                sharedViewModelPlayerProfile,
                sharedViewModelPlayerProfileTimeAtual,
                sharedViewModelPlayerProfileTimeAtualJogadores,
                sharedViewModelPlayerProfileTimeAtualPropostas,

                sharedViewModelPerfilEditarOutro,
                sharedViewModelPerfilJogadorOutro,
                sharedViewModelPerfilOrganizadorOutro,

                // SharedViewModel GET MY TEAMS GERAL
                sharedGetMyTeamsGeral,

                // SharedViewModelGetMyTeams de USUARIO
                sharedViewModelGetMyTeamsUser,
                sharedViewModelGetMyTeamsUserPropostas,
                sharedViewModelGetMyTeamsUserPropostasDe,
                sharedViewModelGetMyTeamsUserPropostasDeJogadores,
                sharedViewModelGetMyTeamsUserPropostasDeJogadoresAtivos,
                sharedViewModelGetMyTeamsUserPropostasDePropostas,

                // SharedViewModelGetMyTeams de TIME
                sharedViewModelGetMyTeamsTime,
                sharedViewModelGetMyTeamsTimeJogadores,
                sharedViewModelGetMyTeamsTimeJogadoresAtivos,
                sharedViewModelGetMyTeamsTimePropostas,

                sharedViewModelNomeJogadorListaJogadores,
                sharedViewModelGetListaJogadores,
                sharedViewModelGetListaJogadoresList,
                sharedViewModelGetListaJogadoresInfoPerfil,
                sharedViewModelGetListaJogadoresTimeAtual,
                sharedViewModelGetListaJogadoresDentroDeTime,
                sharedViewModelGetListaJogadoresDentroDeTimeList,
                sharedViewModelGetListaJogadoresPropostasList,
                sharedViewModelGetListaJogadoresPropostasRecebidas,

                // SharedViewModel GET TIME BY ID
                sharedGetTime,
                sharedGetTimeTeams,
                sharedGetTimeTeamsJogadores,
                sharedGetTimeTeamsJogadoresPerfilId,
                sharedGetTimeDono,
                sharedGetTimeTeamsPropostas,

                sharedFiltragemListaTimes,

                ) {
                currentScreen = it
            }
        }


        val carregarFiltragemListaJogadoresScreen: @Composable () -> Unit = {
            CarregarFiltragemListaJogadoresScreen(
                sharedViewModelTokenEId,
                sharedFiltragemListaJogadores,

                ) {
                currentScreen = it
            }
        }


        val carregarFiltragemListaPublicoesJogadoresCarregarScreen: @Composable () -> Unit = {
            CarregarFiltragemListaPublicoesJogadoresCarregarScreen(
                sharedViewModelTokenEId,
                sharedFiltragemListaPostagensJogadores,

                ) {
                currentScreen = it
            }
        }

        val carregarFiltragemListaPublicacoesJogadoresScreen: @Composable () -> Unit = {
            CarregarFiltragemListaPublicacoesJogadoresScreen(
                sharedViewModelTokenEId,
                sharedViewModelPerfil,
                sharedViewModelPerfilJogador,
                sharedViewModelPerfilOrganizador,

                //SharedViewModel GET MINHA POSTAGEM
                sharedGetMinhaPostagem,
                sharedGetMinhaPostagemUser,
                sharedGetMinhaPostagemUserPropostas,
                sharedGetMinhaPostagemPostProfile,

                sharedFiltragemListaPostagensJogadores,

                ) {
                currentScreen = it
            }
        }





        // NAVEGAÇÃO DO PROJETO
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = {
                slideInHorizontally() + fadeIn() with slideOutHorizontally() + fadeOut()
            },
            content = { screen ->
                when (screen) {
                    "start" -> startScreen()
                    "login" -> loginScreen()
                    "cadastro_perfil" -> cadastroDadosPadraoScreen()
                    "cadastro_tipo_usuario" -> cadastroGeneroEDataNascimentoScreen()
                    "cadastro_usuario_padrao" -> finalizarCadastroUsuarioPadraoScreen()
                    "home" -> homeScreen()
                    "carregar_informacoes_perfil_usuario" -> carregarInformacoesPerfilUsuarioPadraoScreen()
                    "perfil_usuario_jogador" -> perfilUsuarioPadraoScreen()
                    "editar_perfil_usuario_padrao_1" -> editarInformacoesMeuPerfilPadraoScreen()
                    "navigation_proliseum" -> navegacaoPrincipalScreen()
                    "cadastro_usuario_jogador" -> cadastroUsuarioJogadorScreen()
                    "cadastro_usuario_organizador" -> cadastroOrganizacaoScreen()
                    "perfil_organizacao" -> perfilOrganizacaoScreen()
                    "editar_perfil_organizador_1" -> editarInformacoesOrganizacaoScreen()
                    "editar_perfil_jogador_1" -> editarInformacoesJogadorScreen()
                    "deletar_organizacao" -> deletarOrganizacaoScreen()
                    "carregar_deletar_organizacao" -> carregarInformacoesDeletarOrganizacaoScreen()
                    "criar_time" -> criarTimeScreen()
                    "carregar_informacoes_perfil_organizacao" -> carregarInformacoesPerfilOrganizacaoScreen()
                    "perfil_time" -> perfilTimeScreen()
                    "editar_perfil_time" -> editarInformacoesTimeScreen()
                    "lista_de_jogadores" -> listaDeJogadoresScreen()
                    "propostas" -> propostasScreen()
                    "lista_de_publicacoes_jogadores" -> listaDePublicacoesDeJogadoresScreen()
                    "lista_de_publicacoes_times" -> listaDePublicacoesDeTimesScreen()
                    "campeonatos" -> campeonatoScreen()
                    "premium" -> premiumScreen()
                    "perfil_outro_jogador" -> perfilDeOutroJogadorScreen()
//                    "carregar_informacoes_perfil_outro_jogador" -> carregarInformacoesPerfilOutroJogadorScreen()
//                    "carregar_informacoes_do_time_by_id" -> carregarInformacoesDoTimeByIdScreen()
//                    "carregar_informacoes_perfil_jogador_meu_time" -> carregarInformacoesPerfilJogadorMeuTimeScreen()
                    "perfil_jogador_do_meu_time" -> perfilJogadorDoMeuTimeScreen()
                    "carregar_informacoes_lista_times" -> carregarInformacoesListaTimesScreen()
                    "lista_times" -> listaDeTimesScreen()
                    "perfil_outro_time" -> perfilDeOutroTimeScreen()
                    "navigation_configuracoes_meu_time" -> navegacaoConfiguracoesMeuTimeScreen()

                    "perfil_outro_jogador_lista_times" -> perfilDeOutroJogadorListaTimesScreen()
                    "postagem_jogador_screen" -> postagemJogadorScreen()
                    "carregar_informacoes_minha_publicacao" -> carregarInformacoesMinhaPublicacaoScreen()
                    "minha_postagem" -> minhaPostagemScreen()
                    "editar_minha_publicacao_jogador" -> editarMinhaPublicacaoJogadorScreen()
                    "notificacao" -> notificacaoScreen()
                    "navegacao_configuracoes_meu_perfil_principal" -> navegacaoConfiguracoesMeuPerfilPrincipal()
                    "carregar_tela_notificacoes" -> carregarTelaNotificacoesScreen()
                    "criar_rede_social" -> criarRedeSocialScreen()
                    "deletar_rede_social" -> deletarRedeSocialScreen()
                    "carregar_tela_redes_sociais" -> carregarTelaRedesSociaisScreen()
                    "lista_meus_high_lights" -> listaMeusHighLightsScreen()
                    "editar_high_lights" -> editarHighLightScreen()
                    "criar_high_light" -> criarHighLightScreen()
                    "entrar_no_time" -> entrarNoTimeScreen()

                    "carregar_informacoes_perfil_outro_jogador_lista_jogadores" -> carregarInformacoesPerfilOutroJogadorListaTimesScreen()
                    "enviar_proposta" -> enviarPropostaScreen()
                    "enviar_proposta_segunda_parte" -> enviarPropostaSegundaParteScreen()
                    "lista_highlights_outros_usuarios" -> listaHighlightsOutrosUsuariosScreen()

                    "carregar_informacoes_perfil_outro_jogador_escolhido" -> carregarInformacoesPerfilOutroJogadorScreen()
                    "perfil_de_outro_jogador_lista_jogadores" -> perfilDeOutroJogadorListaJogadoresScreen()

                    "lista_highlights_outros_usuarios_lista_jogadores" -> listaHighlightsOutrosUsuariosListaJogadoresScreen()

                    "perfil_outro_time_lista_jogadores" -> perfilDeOutroTimeListaJogadoresScreen()
                    "carregar_informacoes_outro_time_lista_jogadores" -> carregarInformacoesOutroTimeListajogadoresScreen()
                    "navegacao_configuracoes_meu_time_lista_jogadores" -> navegacaoConfiguracoesMeuTimeListaJogadoresScreen()


                    "carregar_informacoes_perfil_outro_jogador_lista_times_que_foi_escolhido" -> carregarInformacoesPerfilOutroJogadorListaTimesQueFoiEscolhidoScreen()
//                    "apagar_publicacao" -> apagarPublicacaoScreen()
                    "carregar_informacoes_lista_publicacoes_jogadores" -> carregarInformacoesListaPublicacoesJogadoresScreen()
                    "lista_propostas_recebidas_para_jogadores" -> listaDePropostasRecebidasParaJogadoresScreen()
                    "carregar_tela_listagem_propostas" -> carregarTelaListagemDePropostasScreen()
                    "escolher_time_para_criar_postagem_time" -> escolherTimeParaCriarPostagemTimeScreen()
                    "criar_postagem_time" -> criarPostagemTimeScreen()
                    "escolher_time_para_visualizar_postagem_time" -> escolherTimeParaVisualizarPostagemTimeScreen()
                    "minha_postagem_time" -> minhaPostagemTimeScreen()
                    "editar_minha_publicacao_time" -> editarMinhaPublicacaoTimeScreen()
                    "carregar_tela_editar_publicacao_time" -> carregarTelaEditarPublicacaoTimeScreen()
                    "lista_inscricoes_para_times" -> listaDeInscricoesParaTimesScreen()
                    "carregar_tela_inscritos_postagem_time" -> carregarTelaInscritosPostagemTimeScreen()
                    "carregar_tela_listagem_inscricoes" -> carregarTelaListagemDeInscricoesScreen()
                    "carregar_filtragem_lista_times" -> carregarFiltragemListaTimesScreen()
                    "lista_times_meus_times" -> listaDeTimesMeusTimesScreen()
                    "carregar_filtragem_lista_jogadores" -> carregarFiltragemListaJogadoresScreen()
                    "carregar_filtragem_lista_publicacoes_jogadores" -> carregarFiltragemListaPublicacoesJogadoresScreen()
                    "carregar_filtragem_lista_publicacoes_jogadores_carregar" -> carregarFiltragemListaPublicoesJogadoresCarregarScreen()


                    else -> startScreen()
                }
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

