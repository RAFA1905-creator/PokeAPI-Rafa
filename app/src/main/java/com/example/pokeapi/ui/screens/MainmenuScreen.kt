package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.pokeapi.viewmodel.MainmenuViewModel

@Composable
fun MainmenuScreen(navController: NavController, viewModel: MainmenuViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Menú principal", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.navContent(navController) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver datos de la API")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.navUserdata(navController) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver datos de tu cuenta")
        }
    }
}
