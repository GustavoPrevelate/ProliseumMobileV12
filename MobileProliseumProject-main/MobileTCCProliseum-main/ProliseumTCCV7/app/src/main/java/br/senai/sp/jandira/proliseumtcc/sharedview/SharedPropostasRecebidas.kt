package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PropostasRecebidasGeral

class SharedPropostasRecebidas: ViewModel() {
    var propostas: List<PropostasRecebidasGeral>? = null
}