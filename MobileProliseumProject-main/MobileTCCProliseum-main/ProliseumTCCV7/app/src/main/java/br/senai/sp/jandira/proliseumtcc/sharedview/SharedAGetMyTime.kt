package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUser

class SharedAGetMyTime: ViewModel() {
    var user: AGetMyTimeUser? = null
    var time: List<AGetMyTimeTime>? = null
}