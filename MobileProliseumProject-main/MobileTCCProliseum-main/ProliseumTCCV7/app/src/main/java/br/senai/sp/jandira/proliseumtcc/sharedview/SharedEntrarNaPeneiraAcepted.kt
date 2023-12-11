package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadores

class SharedEntrarNaPeneiraAcepted: ViewModel() {
    var id: Int? = 0
    var menssagem: String? = ""
    var jogadores: List<EntrarNaPeneiraAceptedJogadores>? = null
}