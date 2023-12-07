package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidasGeralDe

class SharedPropostasRecebidasGeral: ViewModel() {
    var id: Int? = 0
    var menssagem: String? = ""
    var de: PropostasRecebidasGeralDe? = null
}