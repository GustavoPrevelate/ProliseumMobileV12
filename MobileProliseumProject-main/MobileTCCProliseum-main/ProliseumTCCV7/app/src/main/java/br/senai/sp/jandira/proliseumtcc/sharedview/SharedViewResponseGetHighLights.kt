package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetHighLightsDono

class SharedViewResponseGetHighLights: ViewModel() {
    var titulo: String? = ""
    var dono: ResponseGetHighLightsDono? = null
    var id: Int? = 0
}