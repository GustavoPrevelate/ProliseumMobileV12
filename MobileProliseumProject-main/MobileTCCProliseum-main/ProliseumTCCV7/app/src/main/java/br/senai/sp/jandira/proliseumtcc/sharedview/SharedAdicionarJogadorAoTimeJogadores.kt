package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimeJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.model.AdicionarJogadorAoTimeJogadoresTimeAtual

class SharedAdicionarJogadorAoTimeJogadores: ViewModel() {
    var id: Int? = 0
    var nickname: String? = ""
    var jogo: Int? = 0
    var funcao: Int? = 0
    var elo: Int? = 0
    var perfil_id: AdicionarJogadorAoTimeJogadoresPerfilId? = null
    var time_atual: AdicionarJogadorAoTimeJogadoresTimeAtual? = null
}