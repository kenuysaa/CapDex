package com.example.capdex.ui.cadastro

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
fun CadastroScreen(
    authViewModel: AuthViewModel = hiltViewModel(),
    onNavigateToLogin: () -> Unit,
    onRegistrationSuccess: (String) -> Unit
) {
    val uiState by authViewModel.uiState.collectAsState()
    var selectedUserType by remember { mutableStateOf("proprietario") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Cadastro de Usuário",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = uiState.nomeCompleto, // Novo campo
            onValueChange = { authViewModel.onNomeCompletoChanged(it) }, // Nova função no ViewModel
            label = { Text("Nome Completo") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { selectedUserType = "proprietario" },
                enabled = !uiState.isLoading,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedUserType == "proprietario") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text("Proprietário")
            }
            Button(
                onClick = { selectedUserType = "comum" },
                enabled = !uiState.isLoading,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedUserType == "comum") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text("Comum")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { authViewModel.registerUser(selectedUserType) },
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (uiState.isLoading) "Cadastrando..." else "Cadastrar como $selectedUserType")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = onNavigateToLogin) {
            Text("Já tem uma conta? Faça login")
        }

        uiState.errorMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        uiState.successMessage?.let {
            Spacer(modifier = Modifier.height(8.dp))
            Text(it, color = MaterialTheme.colorScheme.secondary)
            if (uiState.userUid != null) {
                onRegistrationSuccess(uiState.userUid!!)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    MaterialTheme {
        CadastroScreen(
            onNavigateToLogin = {},
            onRegistrationSuccess = {}
        )
    }
}