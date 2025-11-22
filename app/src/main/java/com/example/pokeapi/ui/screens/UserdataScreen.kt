package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokeapi.viewmodel.UserdataViewModel

@Composable
fun UserdataScreen(navController: NavController, viewModel: UserdataViewModel = viewModel()) {
    val currentUser = viewModel.getCurrentUser()

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
            onClick = { viewModel.signOut(navController) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar sesión")
        }
    }
}
