package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetTimeFilterByUserTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.model.AGetTimeFilterByUserTeamsPropostas

class SharedAGetTimeFilterByUserTeams: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<AGetTimeFilterByUserTeamsJogadores>? = null
    var propostas: List<AGetTimeFilterByUserTeamsPropostas>? = null
}