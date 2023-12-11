package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTimeJogadores
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTimePropostas
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUser

class SharedAGetMyTimeTime: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<AGetMyTimeTimeJogadores>? = null
    var propostas: List<AGetMyTimeTimePropostas>? = null
}