package com.example.capdex.ui.cadastro

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capdex.presentation.AuthViewModel

@Composable
fun CadastroTest() {
    val authViewModel: AuthViewModel = viewModel()
    val isSignedIn by authViewModel.isSignedIn.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (isSignedIn) {
            Text("Usuário Logado! (Tela Principal)")
            // Aqui você pode chamar sua tela principal ou navegação
        } else {
            CadastroScreen(
                onNavigateToLogin = { /* TODO: Navegar para login */ },
                authViewModel = authViewModel
            )
        }
    }
}