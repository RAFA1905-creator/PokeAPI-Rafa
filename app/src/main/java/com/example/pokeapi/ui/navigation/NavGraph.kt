package com.example.pokeapi.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.pokeapi.ui.screens.ContentScreen
import com.example.pokeapi.ui.screens.LoginScreen
import com.example.pokeapi.ui.screens.RegisterScreen
import com.example.pokeapi.ui.screens.StartScreen
import com.example.pokeapi.ui.screens.UserdataScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = ScreenRoutes.START) {
        composable(ScreenRoutes.START) { StartScreen(navController) }
        composable(ScreenRoutes.LOGIN) { LoginScreen(navController) }
        composable(ScreenRoutes.REGISTER) { RegisterScreen(navController) }
        composable(ScreenRoutes.CONTENT) { ContentScreen() }
        composable(ScreenRoutes.USERDATA) { UserdataScreen(navController) }
    }
}

