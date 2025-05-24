package com.example.capdex.ui.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capdex.presentation.AuthViewModel

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToCadastro: () -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    val uiState by authViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Login",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = uiState.email,
            onValueChange = { authViewModel.onEmailChanged(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.password,
            onValueChange = { authViewModel.onPasswordChanged(it) },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { authViewModel.loginUser() },
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (uiState.isLoading) "Entrando..." else "Entrar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateToCadastro) {
            Text("Não tem uma conta? Cadastre-se")
        }

        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        uiState.successMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.secondary)
            if (uiState.userUid != null) {
                onLoginSuccess(uiState.userUid!!)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    MaterialTheme {
        LoginScreen(
            onNavigateToCadastro = {},
            onLoginSuccess = {}
        )
    }
}