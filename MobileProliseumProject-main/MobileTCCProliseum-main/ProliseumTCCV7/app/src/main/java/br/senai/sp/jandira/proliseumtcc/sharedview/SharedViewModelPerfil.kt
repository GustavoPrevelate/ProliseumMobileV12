package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.User

class SharedViewModelPerfil: ViewModel() {
    var user: User? = null
    var playerProfile: PlayerProfileResponse? = null
}