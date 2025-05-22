package com.example.capdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.capdex.presentation.AuthViewModel
import com.example.capdex.presentation.ui.theme.CapDexTheme
import com.example.capdex.ui.cadastro.CadastroScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CapDexTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val authViewModel: AuthViewModel = viewModel()
                    val isSignedIn by authViewModel.isSignedIn.collectAsState()

                    if (isSignedIn) {
                        Text("Usuário Logado! (Tela Principal)")
                        // Navegar para a tela principal aqui
                    } else {
                        CadastroScreen(
                            onNavigateToLogin = { /* TODO: Navegar para login */ },
                            authViewModel = authViewModel
                        )
                    }
                }
            }
        }
    }
}


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CapDexTheme {
//        Greeting("Android")
//    }
//}