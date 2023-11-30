package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.PlayerProfileResponse
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.ProfileResponsePropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.model.User

class SharedViewModelPerfilPropostasDe: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<ProfileResponsePropostasDeJogadores>? = null
    var propostas: List<ProfileResponsePropostasDePropostas>? = null
}