package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetProfileByIdPlayerProfile
import br.senai.sp.jandira.proliseumtcc.model.GetProfileByIdUser

class SharedGetProfileByIdDoUsuario: ViewModel() {
    var user: GetProfileByIdUser? = null
    var playerProfile: GetProfileByIdPlayerProfile? = null
}