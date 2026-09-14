package mgs.inventory.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mgs.inventory.R

val ColorMainBackground = Color(0xFFC0C0C0)
val ColorCardBackground = Color(0xFFE0E5EA)
val ColorTextDark = Color(0xFF000000)

data class AcessoRapidoItem(val titulo: String, val onClick: () -> Unit)

@Composable
fun DashboardScreen(
    onAbrirConferencia: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(ColorMainBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MockupTopBar()

        NumberCardsRow()

        ImageCard(
            imageResId = R.drawable.grafico,
            contentDescription = "Gráfico de Pizza"
        )

        AcessoRapidoSection(
            itens = listOf(
                AcessoRapidoItem("Conferência", onAbrirConferencia)
            )
        )
    }
}

@Composable
fun AcessoRapidoSection(itens: List<AcessoRapidoItem>, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Acesso rápido",
                color = ColorTextDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )

            itens.forEach { item ->
                Button(onClick = item.onClick, modifier = Modifier.fillMaxWidth()) {
                    Text(item.titulo)
                }
            }
        }
    }
}

@Composable
fun MockupTopBar() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCardBackground)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(96.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo da Empresa",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = "empresa",
                color = ColorTextDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun NumberCardsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        WeightNumberCard(title = "Itens em estoque", value = "10", modifier = Modifier.weight(1f))
        WeightNumberCard(title = "Lorem Ipsum", value = "10", modifier = Modifier.weight(1f))
    }
}

@Composable
fun WeightNumberCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.aspectRatio(1f),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCardBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                color = ColorTextDark,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = value,
                color = ColorTextDark,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ImageCard(imageResId: Int, contentDescription: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = ColorCardBackground)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 220.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen()
}
