package br.senai.sp.jandira.proliseumtcc.sharedview

import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacao
import br.senai.sp.jandira.proliseumtcc.model.GetPostagemListPublicacaoDonoId

class SharedGetListaPostagensPublicacao {
    var id: Int? = 0
    var descricao: String? = ""
    var jogo: String? = ""
    var funcao: String? = ""
    var elo: String? = ""
    var hora: String? = ""
    var tipo: Boolean? = null
    var pros: String? = ""
    var dono_id: GetPostagemListPublicacaoDonoId? = null
}