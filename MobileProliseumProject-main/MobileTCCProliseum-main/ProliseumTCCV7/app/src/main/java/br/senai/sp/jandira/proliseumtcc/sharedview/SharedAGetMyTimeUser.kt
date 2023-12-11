package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUser
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUserHighlights
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUserPropostas
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUserRedeSocial

class SharedAGetMyTimeUser: ViewModel() {
    var id: Int? = 0
    var nome_usuario: String? = ""
    var nome_completo: String? = ""
    var email: String? = ""
    var data_nascimento: String? = ""
    var genero: Int? = 0
    var nickname: String? = ""
    var biografia: String? = ""
    var propostas: List<AGetMyTimeUserPropostas>? = null
    var redeSocial: List<AGetMyTimeUserRedeSocial>? = null
    var highlights: List<AGetMyTimeUserHighlights>? = null
}