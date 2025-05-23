package com.example.capdex.ui.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun MapPreviewScreen() {
    val context = LocalContext.current
    val viewModel: MapViewModel = viewModel()
    val currentLocation by viewModel.currentLocation.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.initializeLocationService(context)
    }

    val defaultLocation = LatLng(-23.550520, -46.633308) // São Paulo
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(currentLocation ?: defaultLocation, 15f)
    }

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = true)
            ) {
                currentLocation?.let { location ->
                    Marker(
                        state = MarkerState(position = location),
                        title = "Localização Atual"
                    )
                }
            }
        }
    }
}
