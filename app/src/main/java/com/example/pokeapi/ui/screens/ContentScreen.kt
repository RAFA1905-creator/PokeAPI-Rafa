package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pokeapi.viewmodel.ContentViewModel

@Composable
fun ContentScreen(viewModel: ContentViewModel = viewModel()) {
    var pokemon by remember { mutableStateOf(viewModel.pokemon) }
    var errorMessage by remember { mutableStateOf(viewModel.errorMessage) }
    var isLoading by remember { mutableStateOf(viewModel.isLoading) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Datos del Pokémon", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.fetchPokemon("bulbasaur") {
                    pokemon = viewModel.pokemon
                    errorMessage = viewModel.errorMessage
                    isLoading = viewModel.isLoading
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cargar Pokémon")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            isLoading -> Text("Cargando datos...")
            errorMessage != null -> Text("Error: $errorMessage", color = MaterialTheme.colorScheme.error)
            pokemon != null -> {
                Text("Nombre: ${pokemon!!.name}")
                Text("ID: ${pokemon!!.id}")
                Text("Altura: ${pokemon!!.height}")
                Text("Peso: ${pokemon!!.weight}")
            }
        }
    }
}
