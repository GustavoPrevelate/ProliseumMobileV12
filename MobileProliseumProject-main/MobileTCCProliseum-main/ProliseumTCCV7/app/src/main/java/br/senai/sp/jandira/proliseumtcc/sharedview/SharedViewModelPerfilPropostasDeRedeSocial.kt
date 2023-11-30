package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeHighlights
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.User

class SharedViewModelPerfilPropostasDeRedeSocial: ViewModel() {
    var id: Int? = 0
    var link: String? = ""
    var tipo: Int? = 0
}