package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDono
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoHighlights
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoPropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoRedeSocial

class SharedResponsePostRedeSocialDono: ViewModel() {
    var id: Int? = 0
    var nome_usuario: String? = ""
    var nome_completo: String? = ""
    var email: String? = ""
    var data_nascimento: String? = ""
    var genero: Int? = 0
    var nickname: String?= ""
    var biografia: String? = ""
    var propostas: List<ResponsePostRedeSocialDonoPropostas>? = null
    var highlights: List<ResponsePostRedeSocialDonoHighlights>? = null
    var redeSocial: List<ResponsePostRedeSocialDonoRedeSocial>? = null
}