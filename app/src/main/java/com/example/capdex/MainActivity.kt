package com.example.capdex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.capdex.presentation.AuthViewModel
//import com.example.capdex.presentation.ui.theme.CapDexTheme
//import com.example.capdex.ui.cadastro.CadastroScreen
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.Surface
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val saoPaulo = LatLng(-23.550520, -46.633308)
                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(saoPaulo, 12f)
                    }

                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = cameraPositionState
                    ) {
                        Marker(
                            state = MarkerState(position = saoPaulo),
                            title = "São Paulo"
                        )
                    }
                }
            }
        }
    }
}