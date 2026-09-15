package mgs.inventory.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mgs.inventory.R

@Composable
fun CadastroUsuarioScreen () {

    var name by remember { mutableStateOf("") }
    var sobrenome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var senhaConfirm by remember { mutableStateOf("") }
    var senhaVisivel by remember { mutableStateOf(false) }
    var senhaConfirmVisivel by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Surface (
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo da Empresa",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            }

            Text(
                text = "Registrar Usuário",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            TextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            )

            TextField(
                value = sobrenome,
                onValueChange = { sobrenome = it },
                label = { Text("Sobrenome") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            )

            TextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            )


            TextField(
                value = senha,
                onValueChange = { senha = it },
                label = { Text("Senha") },
                singleLine = true,
                visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Text(if (senhaVisivel) "Ocultar" else "Mostrar")
                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            )

            TextField(
                value = senhaConfirm,
                onValueChange = { senhaConfirm = it },
                label = { Text("Confirmação de Senha") },
                singleLine = true,
                visualTransformation = if (senhaConfirmVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    TextButton(onClick = { senhaConfirmVisivel = !senhaConfirmVisivel }) {
                        Text(if (senhaConfirmVisivel) "Ocultar" else "Mostrar")
                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp)
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrar Usuário")
            }
        }
    }

}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
private fun DashboardScreenPreview() {
    CadastroUsuarioScreen()
}
