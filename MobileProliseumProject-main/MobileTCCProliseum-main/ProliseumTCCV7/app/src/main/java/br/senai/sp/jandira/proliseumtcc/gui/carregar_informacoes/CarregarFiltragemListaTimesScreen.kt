package br.senai.sp.jandira.proliseumtcc.gui.carregar_informacoes

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.proliseumtcc.R
import br.senai.sp.jandira.proliseumtcc.filtragem.SharedFiltragemListaTimes
import br.senai.sp.jandira.proliseumtcc.sharedview.SharedViewTokenEId
import br.senai.sp.jandira.proliseumtcc.ui.theme.AzulEscuroProliseum
import br.senai.sp.jandira.proliseumtcc.ui.theme.RedProliseum
import kotlinx.coroutines.delay

@Composable
fun CarregarFiltragemListaTimesScreen(
    sharedViewModelTokenEId: SharedViewTokenEId,
    sharedFiltragemListaTimes: SharedFiltragemListaTimes,

    onNavigate: (String) -> Unit
) {

    //CARREGAR TELA
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        // Espera por 0.5 segundos antes de continuar
        delay(500)
        loading = false
    }

    var filtragemNomeState by remember { mutableStateOf(sharedFiltragemListaTimes.name) }

    LaunchedEffect(sharedFiltragemListaTimes) {

        // Esta parte só será executada quando o composable for inicializado
        filtragemNomeState = sharedFiltragemListaTimes.name

        // Atribua outras variáveis de estado para outros campos da mesma maneira
    }

    val customFontFamilyText = FontFamily(
        Font(R.font.font_poppins)
    )

    // DESIGN DA TELA
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        AzulEscuroProliseum, AzulEscuroProliseum
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()

        ) {


            if (loading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_proliseum),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "CARREGANDO",
                            fontFamily = customFontFamilyText,
                            fontSize = 32.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                        ,
                        color = RedProliseum
                    )
                }

            } else {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.logo_proliseum),
                            contentDescription = "",
                            modifier = Modifier.size(80.dp)
                        )

                        Spacer(modifier = Modifier.width(20.dp))

                        Text(
                            text = "CARREGANDO",
                            fontFamily = customFontFamilyText,
                            fontSize = 32.sp,
                            fontWeight = FontWeight(900),
                            color = Color.White
                        )
                    }

                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(300.dp)
                            .width(300.dp)
                        ,
                        color = RedProliseum
                    )
                }

                //TOKEN
                val token = sharedViewModelTokenEId.token

                if(token != null && token.isNotEmpty()){

                    onNavigate("lista_times")

                } else{
                    Log.e("TOKEN NULO","o token esta nulo, carregando informaçoes")
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.logo_proliseum),
                                contentDescription = "",
                                modifier = Modifier.size(80.dp)
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Text(
                                text = "CARREGANDO",
                                fontFamily = customFontFamilyText,
                                fontSize = 32.sp,
                                fontWeight = FontWeight(900),
                                color = Color.White
                            )
                        }

                        CircularProgressIndicator(
                            modifier = Modifier
                                .height(300.dp)
                                .width(300.dp)
                            ,
                            color = RedProliseum
                        )
                    }
                }
            }
        }
    }
}