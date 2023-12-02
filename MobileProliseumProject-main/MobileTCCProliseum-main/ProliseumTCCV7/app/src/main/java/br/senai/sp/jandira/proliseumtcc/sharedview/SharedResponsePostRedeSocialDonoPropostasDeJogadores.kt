package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDono
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoHighlights
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoPropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoPropostasDe
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoPropostasDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoPropostasDePropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostRedeSocialDonoRedeSocial

class SharedResponsePostRedeSocialDonoPropostasDeJogadores: ViewModel() {
    val id: Int? = 0
    val nickname: String? = ""
    val jogo: Int? = 0
    val funcao: Int? = 0
    val elo: Int? = 0
}