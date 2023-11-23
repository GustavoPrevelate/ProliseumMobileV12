package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsOrganizacaoDonoId

class SharedGetTimeByIdTeamsOrganizacao: ViewModel() {

    var id: Int? = 0
    var nome_organizacao: String? = ""
    var biografia: String? = ""
    var dono_id: getTimeByIdTeamsOrganizacaoDonoId? = null
}