package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimeJogadoresTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas

class SharedAdicionarJogadorAoTimeJogadoresTimeAtual: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<AdicionarJogadorAoTimeJogadoresTimeAtualJogadores>? = null
    var propostas: List<AdicionarJogadorAoTimeJogadoresTimeAtualJogadoresPropostas>? = null
}