package com.example.pokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MainmenuViewModel : ViewModel() {
    fun navContent(navController: NavController) {
        navController.navigate("content")
    }

    fun navUserdata(navController: NavController) {
        navController.navigate("userdata")
    }
}
