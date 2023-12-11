package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAcepted

class SharedEntrarNaPeneira: ViewModel() {
    var acepted: EntrarNaPeneiraAcepted? = null
}