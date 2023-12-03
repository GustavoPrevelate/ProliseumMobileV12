package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDono

class SharedViewResponsePostHighLights: ViewModel() {
    var titulo: String? = ""
    var dono: ResponsePostHighLightsDono? = null
    var id: Int? = 0
}