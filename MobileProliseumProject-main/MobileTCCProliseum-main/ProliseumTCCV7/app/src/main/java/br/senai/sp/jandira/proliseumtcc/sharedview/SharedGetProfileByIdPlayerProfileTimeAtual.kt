package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetProfileByIdPlayerProfileTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetProfileByIdPlayerProfileTimeAtualPropostas

class SharedGetProfileByIdPlayerProfileTimeAtual: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<GetProfileByIdPlayerProfileTimeAtualJogadores>? = null
    var propostas: List<GetProfileByIdPlayerProfileTimeAtualPropostas>? = null
}