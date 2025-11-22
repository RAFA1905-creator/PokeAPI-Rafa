package com.example.pokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class StartViewModel : ViewModel() {
    fun navLogin(navController: NavController) {
        navController.navigate("login")
    }

    fun navRegister(navController: NavController) {
        navController.navigate("register")
    }
}
