package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidasGeralDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidasGeralDePropostas

class SharedPropostasRecebidasGeralDe: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<PropostasRecebidasGeralDeJogadores>? = null
    var propostas: List<PropostasRecebidasGeralDePropostas>? = null
}