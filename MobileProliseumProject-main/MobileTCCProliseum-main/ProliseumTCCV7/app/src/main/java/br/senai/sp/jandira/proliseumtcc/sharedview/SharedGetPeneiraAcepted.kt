package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedTime

class SharedGetPeneiraAcepted: ViewModel() {
    var id: Int? = 0
    var menssagem: String? = ""
    var time: GetPeneiraAceptedTime? = null
    var jogadores: List<GetPeneiraAceptedJogadores>? = null
}