package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDono

class SharedResponsePostRedeSocial: ViewModel() {
    var link: String? = ""
    var tipo: Int? = 0
    var dono: ResponsePostRedeSocialDono? = null
    var id: Int? = 0
}