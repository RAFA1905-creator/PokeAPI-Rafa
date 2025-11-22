package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ContentScreen() {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Usuario logueado", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        if (currentUser != null) {
            Text("Email: ${currentUser.email ?: "No disponible"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text("UID: ${currentUser.uid}")
        } else {
            Text("No hay usuario logueado")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                auth.signOut()
                // Aquí podrías navegar de vuelta al login
                // navController.navigate("login")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}
