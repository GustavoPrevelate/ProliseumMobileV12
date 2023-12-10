package br.senai.sp.jandira.proliseumtcc.sharedview

import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacaoTimeJogadores
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacaoTimePropostas

class SharedGetTimeListaPostagensPublicacaoTime {
    var id: Int? = 0
    var nome_time: String? = ""
    var jogo: Int? = 0
    var biografia: String? = ""
    var jogadores: List<GetTimePostagemListPublicacaoTimeJogadores>? = null
    var propostas: List<GetTimePostagemListPublicacaoTimePropostas>? = null


}