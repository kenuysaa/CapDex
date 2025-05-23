package com.example.capdex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn
    private val _signUpResult = MutableStateFlow<Result<Unit>?>(null)
    val signUpResult: StateFlow<Result<Unit>?> = _signUpResult

    init {
        checkIfUserIsSignedIn()
    }

    private fun checkIfUserIsSignedIn() {
        val currentUser = auth.currentUser
        _isSignedIn.value = currentUser != null
    }

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            val result = try {
                auth.createUserWithEmailAndPassword(email, password).await()
                _isSignedIn.value = true
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
            _signUpResult.value = result
        }
    }

    fun signOut() {
        auth.signOut()
        _isSignedIn.value = false
    }

    // Funções para login e cadastro virão aqui
}
