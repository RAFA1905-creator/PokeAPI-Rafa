package com.example.pokeapi.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()

    var errorMensaje: String? = null
        private set

    var cargando: Boolean = false
        private set

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            cargando = true
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    cargando = false
                    if (task.isSuccessful) {
                        errorMensaje = null
                        onResult(true)
                    } else {
                        errorMensaje = "Email o contraseña inválidos"
                        Log.e("Login", "Error: ${task.exception?.message}")
                        onResult(false)
                    }
                }
        } else {
            errorMensaje = "Escribe tu email y contraseña registrados"
            onResult(false)
        }
    }
}
