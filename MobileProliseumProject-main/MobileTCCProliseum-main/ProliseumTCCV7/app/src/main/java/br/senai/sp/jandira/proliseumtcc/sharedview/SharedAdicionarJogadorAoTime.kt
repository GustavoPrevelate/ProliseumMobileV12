package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimeDono
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimePropostas

class SharedAdicionarJogadorAoTime: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var dono: AdicionarJogadorAoTimeDono? = null
    var jogadores: List<AdicionarJogadorAoTimeJogadores>? = null
    var propostas: List<AdicionarJogadorAoTimePropostas>? = null
}