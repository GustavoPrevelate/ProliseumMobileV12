package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedTime

class SharedGetPeneiraAceptedJogadores: ViewModel() {
    var id: Int? = 0
    var nickname: String? = ""
    var jogo: Int? = 0
    var funcao: Int? = 0
    var elo: Int? = 0
    var perfil_id: GetPeneiraAceptedJogadoresPerfilId? = null
}