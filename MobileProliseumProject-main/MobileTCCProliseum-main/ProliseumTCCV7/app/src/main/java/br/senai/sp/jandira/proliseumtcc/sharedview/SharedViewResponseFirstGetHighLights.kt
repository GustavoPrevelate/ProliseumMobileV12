package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponseGetHighLights

class SharedViewResponseFirstGetHighLights: ViewModel() {
    var highlight: List<ResponseGetHighLights>? = null
}