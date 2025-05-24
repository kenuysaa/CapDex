package com.example.capdex.ui.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Cadastro : Screen("cadastro")
    object Main : Screen("main")
    object Map : Screen("map")
    object Logout : Screen("logout")
}

sealed class MainScreenRoute(val route: String) {
    object Map : MainScreenRoute("map")
    object Logout : MainScreenRoute("logout")
}