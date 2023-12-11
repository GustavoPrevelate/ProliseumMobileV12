package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresTimeAtual

class SharedEntrarNaPeneiraAceptedJogadoresPerfilId: ViewModel() {
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