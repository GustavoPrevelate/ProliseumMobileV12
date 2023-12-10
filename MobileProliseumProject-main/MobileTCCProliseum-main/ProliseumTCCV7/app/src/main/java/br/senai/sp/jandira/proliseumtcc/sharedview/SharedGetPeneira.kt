package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetPeneiraAcepted

class SharedGetPeneira: ViewModel() {
    var acepted: List<GetPeneiraAcepted>? = null
}