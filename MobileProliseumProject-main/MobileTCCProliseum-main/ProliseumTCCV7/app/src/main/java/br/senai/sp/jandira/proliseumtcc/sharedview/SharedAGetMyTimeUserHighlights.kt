package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeTime
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUser
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUserHighlights
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUserPropostas
import br.senai.sp.jandira.proliseumtcc.model.AGetMyTimeUserRedeSocial

class SharedAGetMyTimeUserHighlights: ViewModel() {
    var id: Int? = 0
    var titulo: String? = ""
}