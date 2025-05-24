package com.example.capdex.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.capdex.presentation.AuthViewModel

@Composable
fun MainScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { navController.navigate("map") }) {
            Text("Mapa")
        }
        Button(onClick = { navController.navigate("logout") }) {
            Text("Logout")
        }
    }
}