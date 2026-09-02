package mgs.inventory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mgs.inventory.screens.ConferenciaScreen
import mgs.inventory.screens.DashboardScreen
import mgs.inventory.screens.LoginScreen
import mgs.inventory.ui.theme.MGSInventoryTheme

private object Rotas {
    const val LOGIN = "login"
    const val DASHBOARD = "dashboard"
    const val CONFERENCIA = "conferencia"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MGSInventoryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Rotas.LOGIN,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        composable(Rotas.LOGIN) {
                            LoginScreen(
                                onLoginClick = { _, _ ->
                                    navController.navigate(Rotas.DASHBOARD) {
                                        popUpTo(Rotas.LOGIN) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(Rotas.DASHBOARD) {
                            DashboardScreen(
                                onAbrirConferencia = { navController.navigate(Rotas.CONFERENCIA) }
                            )
                        }
                        composable(Rotas.CONFERENCIA) {
                            ConferenciaScreen()
                        }
                    }
                }
            }
        }
    }
}
