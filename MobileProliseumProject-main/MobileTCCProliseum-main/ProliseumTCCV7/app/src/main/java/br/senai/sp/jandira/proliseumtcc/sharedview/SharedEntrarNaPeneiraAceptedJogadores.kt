package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresTimeAtual

class SharedEntrarNaPeneiraAceptedJogadores: ViewModel() {
    var id: Int? = 0
    var nickname: String? = ""
    var jogo: Int? = 0
    var funcao: Int? = 0
    var elo: Int? = 0
    var perfil_id: EntrarNaPeneiraAceptedJogadoresPerfilId? = null
    var time_atual: EntrarNaPeneiraAceptedJogadoresTimeAtual? = null
}