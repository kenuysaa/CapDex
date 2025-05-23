package com.example.capdex.ui.map

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capdex.location.LocationService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapViewModel : ViewModel() {
    private val _currentLocation = MutableStateFlow<LatLng?>(null)
    val currentLocation: StateFlow<LatLng?> = _currentLocation

    private var locationService: LocationService? = null

    fun initializeLocationService(context: android.content.Context) {
        locationService = LocationService(context).apply {
            setOnLocationUpdateListener { location ->
                updateLocation(location)
            }
            startLocationUpdates()
        }
    }

    fun updateLocation(location: Location) {
        _currentLocation.value = LatLng(location.latitude, location.longitude)
    }

    override fun onCleared() {
        super.onCleared()
        locationService?.stopLocationUpdates()
    }
} 