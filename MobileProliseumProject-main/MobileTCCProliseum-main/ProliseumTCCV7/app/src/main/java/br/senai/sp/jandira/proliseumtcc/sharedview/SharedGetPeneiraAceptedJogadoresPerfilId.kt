package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAceptedTime

class SharedGetPeneiraAceptedJogadoresPerfilId: ViewModel() {
    var id: Int? = 0
    var nome_usuario: String? = ""
    var nome_completo: String? = ""
    var email: String? = ""
    var senha: String? = ""
    var data_nascimento: String? = ""
    var genero: Int? = 0
    var nickname: String? = ""
    var biografia: String? = ""
}