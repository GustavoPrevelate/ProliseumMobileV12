package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponseTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponseTimeAtualPropostas
import br.senai.sp.jandira.proliseumtcc.model.User

class SharedViewModelPlayerProfileTimeAtual: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: Int? = 0
    var jogadores: List<PlayerProfileResponseTimeAtualJogadores>? = null
    var propostas: List<PlayerProfileResponseTimeAtualPropostas>? = null
}