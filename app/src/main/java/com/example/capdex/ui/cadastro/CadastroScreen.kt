package com.example.capdex.ui.cadastro

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capdex.presentation.AuthViewModel

@Composable
fun CadastroScreen(authViewModel: AuthViewModel = viewModel(), onNavigateToLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val signUpResult by authViewModel.signUpResult.collectAsState(initial = null)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { authViewModel.signUp(email, password) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cadastrar")
        }

        if (signUpResult?.isSuccess == true) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Cadastro realizado com sucesso!")
            LaunchedEffect(Unit) {
                // Opcional: Navegar para outra tela após um tempo
                // onNavigateToLogin()
            }
        }

        if (signUpResult?.isFailure == true) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = signUpResult?.exceptionOrNull()?.localizedMessage ?: "Erro ao cadastrar.",
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}