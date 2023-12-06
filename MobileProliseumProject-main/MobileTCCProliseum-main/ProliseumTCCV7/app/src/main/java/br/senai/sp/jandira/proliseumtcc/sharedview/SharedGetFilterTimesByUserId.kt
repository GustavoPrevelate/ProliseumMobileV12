package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.GetFilterTimesByUserIdUser
import br.senai.sp.jandira.proliseumtcc.model.getTimeTeams

class SharedGetFilterTimesByUserId: ViewModel() {
    var teams: List<GetFilterTimesByUserIdUser>? = null

    // Utilize um MutableState para armazenar o ID do time selecionado
    var selectedTimeFilterTimeUserById by mutableStateOf<Int?>(null)

    fun getTeamFilterTimeUserById(teamId: Int): GetFilterTimesByUserIdUser? {
        return teams?.find { it.id == teamId }
    }
}