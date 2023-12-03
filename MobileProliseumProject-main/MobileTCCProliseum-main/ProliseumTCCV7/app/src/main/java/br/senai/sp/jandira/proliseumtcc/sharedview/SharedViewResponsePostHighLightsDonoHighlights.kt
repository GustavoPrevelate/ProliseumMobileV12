package br.senai.sp.jandira.proliseumtcc.sharedview

import androidx.lifecycle.ViewModel
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDono
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoHighlights
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoPropostas
import br.senai.sp.jandira.proliseumtcc.model.ResponsePostHighLightsDonoRedesSociais

class SharedViewResponsePostHighLightsDonoHighlights: ViewModel() {
    var id: Int? = 0
    var titulo: String? = ""
}