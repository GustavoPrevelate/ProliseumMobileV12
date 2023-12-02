package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDono
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoHighlights
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoHighlightsDono
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoPropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoRedeSocial

class SharedResponsePostRedeSocialDonoHighlights: ViewModel() {
    var id: Int? = 0
    var titulo: String? = ""
    var dono: ResponsePostRedeSocialDonoHighlightsDono? = null
}