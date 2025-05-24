package com.example.capdex.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capdex.data.repository.UserRepository
import com.example.capdex.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isRegistering: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val successMessage: String? = null,
    val userUid: String? = null
)

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState

    fun onEmailChanged(newEmail: String) {
        _uiState.update { it.copy(email = newEmail) }
    }

    fun onPasswordChanged(newPassword: String) {
        _uiState.update { it.copy(password = newPassword) }
    }

    fun registerUser(userType: String = "comum") {
        _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
        viewModelScope.launch {
            try {
                val authResult = firebaseAuth.createUserWithEmailAndPassword(uiState.value.email, uiState.value.password).await()
                val uid = authResult.user?.uid
                if (uid != null) {
                    val user = User(uid = uid, email = uiState.value.email, userType = userType)
                    val saveResult = userRepository.saveUser(user)
                    saveResult.onSuccess {
                        _uiState.update {
                            it.copy(isLoading = false, successMessage = "Cadastro realizado com sucesso!", userUid = uid)
                        }
                    }
                    saveResult.onFailure { e ->
                        // Lidar com o erro ao salvar no Firestore (talvez deslogar o usuário?)
                        _uiState.update { it.copy(isLoading = false, errorMessage = "Erro ao salvar informações do usuário: ${e.localizedMessage}") }
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false, errorMessage = "Erro ao obter UID do usuário.") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.localizedMessage ?: "Erro ao cadastrar.") }
            }
        }
    }

    fun loginUser() {
        _uiState.update { it.copy(isLoading = true, errorMessage = null, successMessage = null) }
        viewModelScope.launch {
            try {
                val authResult = firebaseAuth.signInWithEmailAndPassword(uiState.value.email, uiState.value.password).await()
                _uiState.update {
                    it.copy(isLoading = false, successMessage = "Login realizado com sucesso! UID: ${authResult.user?.uid}", userUid = authResult.user?.uid)
                }
                // Aqui você buscaria as informações do usuário do Firestore para determinar o tipo
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, errorMessage = e.localizedMessage ?: "Erro ao fazer login.") }
            }
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
        _uiState.update { AuthUiState() }
    }
}
