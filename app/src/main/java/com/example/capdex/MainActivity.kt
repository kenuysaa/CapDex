package com.example.capdex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.capdex.presentation.AuthViewModel
import com.example.capdex.presentation.ui.theme.CapDexTheme
import com.example.capdex.ui.cadastro.CadastroScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CapDexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val authViewModel: AuthViewModel = hiltViewModel()
                    CadastroScreen(
                        authViewModel = authViewModel,
                        onNavigateToLogin = { Log.d("Navigation", "Navigate to Login") },
                        onRegistrationSuccess = { uid -> Log.d("Cadastro", "Sucesso no cadastro com UID: $uid") }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CapDexTheme {
        val authViewModel: AuthViewModel = hiltViewModel()
        CadastroScreen(
            authViewModel = authViewModel,
            onNavigateToLogin = { /* Não faz nada na preview */ },
            onRegistrationSuccess = { /* Não faz nada na preview */ }
        )
    }
}