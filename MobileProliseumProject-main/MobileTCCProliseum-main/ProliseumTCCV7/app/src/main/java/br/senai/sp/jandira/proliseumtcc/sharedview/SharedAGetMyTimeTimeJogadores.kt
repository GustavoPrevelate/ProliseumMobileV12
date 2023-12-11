package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTimeJogadores
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTimePropostas
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUser

class SharedAGetMyTimeTimeJogadores: ViewModel() {
    var id: Int? = 0
    var nickname: String? = ""
    var jogo: Int? = 0
    var funcao: Int? = 0
    var elo: Int? = 0
}