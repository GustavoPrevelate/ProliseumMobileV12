package br.senai.sp.jandira.proliseumtcc.sharedview

import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacaoDonoId
import br.senai.sp.jandira.proliseumtcc.model.GetTimePostagemListPublicacaoTime

class SharedGetTimeListaPostagensPublicacao {
    var id: Int? = 0
    var descricao: String? = ""
    var jogo: String? = ""
    var funcao: String? = ""
    var elo: String? = ""
    var hora: String? = ""
    var tipo: Boolean? = null
    var pros: String? = ""
    var dono_id: GetTimePostagemListPublicacaoDonoId? = null
    var time: GetTimePostagemListPublicacaoTime? = null
}