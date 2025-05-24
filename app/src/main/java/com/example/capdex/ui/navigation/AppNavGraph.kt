package com.example.capdex.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.capdex.presentation.AuthViewModel
import com.example.capdex.ui.cadastro.CadastroScreen
import com.example.capdex.ui.login.LoginScreen
import com.example.capdex.ui.map.MapPreviewScreen
import com.example.capdex.ui.main.LogoutScreen
import com.example.capdex.ui.main.MainScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                authViewModel = authViewModel,
                onNavigateToCadastro = { navController.navigate(Screen.Cadastro.route) },
                onLoginSuccess = { navController.navigate(Screen.Main.route) }
            )
        }
        composable(Screen.Cadastro.route) {
            CadastroScreen(
                authViewModel = authViewModel,
                onNavigateToLogin = { navController.navigate(Screen.Login.route) },
                onRegistrationSuccess = { navController.navigate(Screen.Main.route) }
            )
        }
        composable(Screen.Main.route) {
            MainScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(Screen.Map.route) {
            MapPreviewScreen()
        }
        composable(Screen.Logout.route) {
            LogoutScreen(authViewModel = authViewModel) {
                navController.navigate(Screen.Login.route) {
                    popUpTo(Screen.Main.route) { inclusive = true }
                }
            }
        }
    }
}