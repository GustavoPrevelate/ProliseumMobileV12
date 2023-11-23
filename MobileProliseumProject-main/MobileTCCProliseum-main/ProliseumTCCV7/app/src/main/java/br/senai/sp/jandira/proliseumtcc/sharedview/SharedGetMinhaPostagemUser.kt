package br.senai.sp.jandira.proliseumtcc.sharedview

import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagemPostProfile
import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagemUser
import br.senai.sp.jandira.proliseumtcc.model.GetMinhaPostagemUserPropostas

class SharedGetMinhaPostagemUser {
    var id: Int? = null
    var nome_usuario: String? = null
    var nome_completo: String? = null
    var email: String? = null
    var data_nascimento: String? = null
    var genero: Int? = null
    var nickname: String? = null
    var biografia: String? = null
    var propostas: List<GetMinhaPostagemUserPropostas>? = null
}