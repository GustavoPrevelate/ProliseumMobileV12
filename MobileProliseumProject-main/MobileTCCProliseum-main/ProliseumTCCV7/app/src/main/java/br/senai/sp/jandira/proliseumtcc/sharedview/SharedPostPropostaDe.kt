package br.senai.sp.jandira.proliseumtcc.sharedview

import br.senai.sp.jandira.proliseumtcc.model.PostPropostaDeDono
import br.senai.sp.jandira.proliseumtcc.model.PostPropostaDeJogadores
import br.senai.sp.jandira.proliseumtcc.model.PostPropostaDePropostas

class SharedPostPropostaDe {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var dono: PostPropostaDeDono? = null
    var jogadores: List<PostPropostaDeJogadores>? = null
    var propostas: List<PostPropostaDePropostas>? = null
}