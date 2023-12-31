package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeHighlights
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeRedeSocial
import br.senai.sp.jandira.proliseumtcc.model.User

class SharedViewModelPerfilPropostasDe: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<ProfileResponsePropostasDeJogadores>? = null
    var propostas: List<ProfileResponsePropostasDePropostas>? = null
    var redeSocial: List<ProfileResponsePropostasDeRedeSocial>? = null
    var highlights: List<ProfileResponsePropostasDeHighlights>? = null
}