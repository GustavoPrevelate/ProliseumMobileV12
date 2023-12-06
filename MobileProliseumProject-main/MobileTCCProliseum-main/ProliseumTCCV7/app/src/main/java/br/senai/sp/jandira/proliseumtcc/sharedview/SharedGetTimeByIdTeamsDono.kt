package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.MeusTimes
import br.senai.sp.jandira.proliseumtcc.model.TimeForGetMyTeams
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsJogadores
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsOrganizacao
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsOrganizacaoDonoId
import br.senai.sp.jandira.proliseumtcc.model.getTimeByIdTeamsPropostas

class SharedGetTimeByIdTeamsDono: ViewModel() {

    var id: Int? = 0
    var nome_usuario: String? = ""
    var nome_completo: String? = ""
    var email: String? = ""
    var senha: String? = ""
    var data_nascimento: String? = ""
    var genero: Int? = 0
    var nickname: String? = ""
    var biografia: String? = ""

}