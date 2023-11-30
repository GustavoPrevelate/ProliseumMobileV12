package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDe
import br.senai.sp.jandira.proliseumtcc.model.User

class SharedViewModelPerfilPropostas: ViewModel() {
    var id: Int? = 0
    var menssagem: String? = ""
    var de: ProfileResponsePropostasDe? = null
}