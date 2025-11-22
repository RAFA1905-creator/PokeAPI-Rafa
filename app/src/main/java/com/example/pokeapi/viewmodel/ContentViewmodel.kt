package com.example.pokeapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class ContentViewModel : ViewModel() {
    var pokemon: Pokemon? = null
        private set

    var errorMessage: String? = null
        private set

    var isLoading: Boolean = false
        private set

    private val api = PokeApiService.create()

    fun fetchPokemon(name: String, onResult: () -> Unit) {
        viewModelScope.launch {
            try {
                isLoading = true
                val result = api.getPokemon(name)
                pokemon = result
                errorMessage = null
            } catch (e: Exception) {
                errorMessage = e.message
            } finally {
                isLoading = false
                onResult()
            }
        }
    }
}
