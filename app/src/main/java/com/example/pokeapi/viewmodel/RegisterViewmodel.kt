package com.example.pokeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var errorMensaje: String? = null
        private set

    var cargando: Boolean = false
        private set

    fun registro(email: String, password: String, onResult: (Boolean) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            cargando = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    cargando = false
                    if (task.isSuccessful) {
                        errorMensaje = null
                        onResult(true)
                    } else {
                        errorMensaje = task.exception?.localizedMessage ?: "Error desconocido"
                        Log.e("Register", "Error: ${task.exception?.message}")
                        onResult(false)
                    }
                }
        } else {
            errorMensaje = "Escribe un email y una contraseña"
            onResult(false)
        }
    }
}
