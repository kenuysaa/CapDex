package com.example.capdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.capdex.ui.map.MapPreviewScreen
import com.example.capdex.presentation.ui.theme.CapDexTheme
import com.example.capdex.ui.cadastro.CadastroTest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            CapDexTheme {
//                CadastroTest()
//            }
            MapPreviewScreen()
        }
    }
}