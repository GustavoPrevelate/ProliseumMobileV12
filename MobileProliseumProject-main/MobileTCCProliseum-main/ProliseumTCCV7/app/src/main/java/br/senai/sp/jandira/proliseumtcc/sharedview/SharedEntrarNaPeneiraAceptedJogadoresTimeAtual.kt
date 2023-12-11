package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAcepted
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadores
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresPerfilId
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresTimeAtual
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresTimeAtualJogadores
import br.senai.sp.jandira.proliseumtcc.model.EntrarNaPeneiraAceptedJogadoresTimeAtualPropostas

class SharedEntrarNaPeneiraAceptedJogadoresTimeAtual: ViewModel() {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<EntrarNaPeneiraAceptedJogadoresTimeAtualJogadores>? = null
    var propostas: List<EntrarNaPeneiraAceptedJogadoresTimeAtualPropostas>? = null
}