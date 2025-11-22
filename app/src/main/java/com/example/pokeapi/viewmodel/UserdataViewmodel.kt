package com.example.pokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserdataViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun signOut(navController: NavController) {
        auth.signOut()
        navController.navigate("start") {
            popUpTo("content") { inclusive = true }
        }
    }
}
