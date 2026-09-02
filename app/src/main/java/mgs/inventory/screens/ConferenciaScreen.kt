package mgs.inventory.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mgs.inventory.model.ItemConferencia
import mgs.inventory.model.StatusValidacao

@Composable
fun ConferenciaScreen(modifier: Modifier = Modifier) {

    var itens by remember {
        mutableStateOf(
            List(20) { index ->
                ItemConferencia(
                    id = index + 1,
                    quantidade = "10 pç",
                    nomeItem = "Caixa"
                )
            }
        )
    }

    fun atualizarStatus(id: Int, status: StatusValidacao) {
        itens = itens.map { item ->
            if (item.id == id) item.copy(status = status) else item
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFE0E0E0))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Empresa",
                    color = Color.Black
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Surface {
            Text(
                text = "Lista de Conferência",
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Quantidade", fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "Item", fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "Validação", fontWeight = FontWeight.Bold, color = Color.Black)
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF757575), shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .padding(horizontal = 8.dp)
        ) {
            items(itens, key = { it.id }) { item ->
                LinhaItemConferencia(
                    item = item,
                    onAprovar = { atualizarStatus(item.id, StatusValidacao.APROVADO) },
                    onReprovar = { atualizarStatus(item.id, StatusValidacao.REPROVADO) }
                )
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
        Text(text = item.quantidade, color = Color.White)
        Text(text = item.nomeItem, color = Color.White)

        when (item.status) {
            StatusValidacao.PENDENTE -> Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Button(
                    onClick = onAprovar,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32)),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("Aprovar")
                }
                Button(
                    onClick = onReprovar,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828)),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("Reprovar")
                }
            }
            StatusValidacao.APROVADO -> Text(text = "Aprovado", color = Color(0xFF81C784), fontWeight = FontWeight.Bold)
            StatusValidacao.REPROVADO -> Text(text = "Reprovado", color = Color(0xFFE57373), fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun ConferenciaScreenPreview() {
    ConferenciaScreen()
}
