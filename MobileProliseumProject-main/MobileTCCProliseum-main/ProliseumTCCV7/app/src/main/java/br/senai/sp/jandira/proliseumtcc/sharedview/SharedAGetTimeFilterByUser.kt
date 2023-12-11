package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetTimeFilterByUserTeams

class SharedAGetTimeFilterByUser: ViewModel() {
    var teams: List<AGetTimeFilterByUserTeams>? = null
}