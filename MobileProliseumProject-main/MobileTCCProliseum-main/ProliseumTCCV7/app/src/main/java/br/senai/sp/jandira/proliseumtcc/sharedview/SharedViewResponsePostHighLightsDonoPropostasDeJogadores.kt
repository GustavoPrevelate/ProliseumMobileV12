package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDono
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoHighlights
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoPropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoPropostasDe
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoRedesSociais

class SharedViewResponsePostHighLightsDonoPropostasDeJogadores: ViewModel() {
    var id: Int? = 0
    var nickname: String? = ""
    var jogo: Int? = 0
    var funcao: Int? = 0
    var elo: Int? = 0
}