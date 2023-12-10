package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedTime
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedTimeJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedTimePropostas

class SharedGetPeneiraAceptedTimePropostas: ViewModel() {
    var id: Int? = 0
    var menssagem: String? = ""
}