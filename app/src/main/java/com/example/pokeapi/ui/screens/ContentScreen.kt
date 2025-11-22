package com.example.pokeapi.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int
)

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemon(@Path("name") name: String): Pokemon

    companion object {
        fun create(): PokeApiService {
            return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PokeApiService::class.java)
        }
    }
}

@Composable
fun ContentScreen() {
    val api = remember { PokeApiService.create() }
    var pokemon by remember { mutableStateOf<Pokemon?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

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
                scope.launch {
                    try {
                        isLoading = true
                        val result = api.getPokemon("bulbasaur")
                        pokemon = result
                        errorMessage = null
                    } catch (e: Exception) {
                        errorMessage = e.message
                    } finally {
                        isLoading = false
                    }
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
