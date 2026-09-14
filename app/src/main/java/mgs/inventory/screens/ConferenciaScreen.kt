package mgs.inventory.screens

import androidx.compose.ui.text.style.TextAlign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mgs.inventory.R

data class ItemConferencia(
    val id: Int,
    val quantidade: String,
    val nomeItem: String,
    var statusValidacao: StatusValidacao = StatusValidacao.PENDENTE
)

enum class StatusValidacao {
    PENDENTE, APROVADO, REPROVADO
}

class ConferenciaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ConferenciaScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

val LightGrayBackground = Color(0xFFEE2B3E)

@Composable
fun ConferenciaScreen(modifier: Modifier = Modifier) {
    var itens by remember {
        mutableStateOf(
            List(20) { index ->
                ItemConferencia(
                    id = index + 1,
                    quantidade = "10 ps",
                    nomeItem = "Caixa"
                )
            }
        )
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF0B2B8))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5D7DA)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color.White
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo da Empresa",
                        modifier = Modifier
                            .size(50.dp)
                    )
                }
                Text(
                    text = "empresa",
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray


                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color(0xFFF5D7DA),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Lista de Conferência",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF7C0611), shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Quantidade", fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "Item", fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "Validação", fontWeight = FontWeight.Bold, color = Color.White)
        }


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFEE8791), shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .padding(horizontal = 8.dp)
        ) {
            items(itens, key = { it.id }) { item ->
                LinhaItemConferencia(
                    item = item,
                    onAprovar = {
                        itens = itens.map { if (it.id == item.id) it.copy(statusValidacao = StatusValidacao.APROVADO) else it }
                    },
                    onReprovar = {
                        itens = itens.map { if (it.id == item.id) it.copy(statusValidacao = StatusValidacao.REPROVADO) else it }
                    }
                )
                HorizontalDivider(color = Color.Black.copy(alpha = 0.1f))
            }
        }
    }
}

@Composable
fun LinhaItemConferencia(
    item: ItemConferencia,
    onAprovar: () -> Unit,
    onReprovar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.quantidade,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = item.nomeItem,
            color = Color.White,
            modifier = Modifier.weight(1f)
        )

        Row(
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            IconButton(onClick = onAprovar) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Aprovar",
                    tint = if (item.statusValidacao == StatusValidacao.APROVADO) Color(0xFF4CAF50) else Color.Black
                )
            }
            IconButton(onClick = onReprovar) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Reprovar",
                    tint = if (item.statusValidacao == StatusValidacao.REPROVADO) Color(0xFFEE2B3E) else Color.Black
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TelaConferenciaPreview() {
    MyApplicationTheme {
        ConferenciaScreen()
    }
}

@Composable
fun MyApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(content = content)
}