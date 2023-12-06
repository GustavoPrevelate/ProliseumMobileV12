package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetFilterTimesByUserIdUserJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetFilterTimesByUserIdUserPropostas

class SharedGetFilterTimesByUserIdUser: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<GetFilterTimesByUserIdUserJogadores>? = null
    var propostas: List<GetFilterTimesByUserIdUserPropostas>? = null
}