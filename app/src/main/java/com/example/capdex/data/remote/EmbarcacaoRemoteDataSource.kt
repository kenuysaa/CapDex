package com.example.capdex.data.remote

import com.example.capdex.domain.model.Embarcacao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class EmbarcacaoRemoteDataSource @Inject constructor(
    private val firestore: FirebaseFirestore
) {
    private val embarcacoesCollection = firestore.collection("embarcacoes")

    suspend fun adicionarEmbarcacao(embarcacao: Embarcacao, proprietarioId: String): Result<Unit> {
        val embarcacaoComProprietarioId = embarcacao.copy(proprietarioId = proprietarioId)
        return try {
            embarcacoesCollection.add(embarcacaoComProprietarioId).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun obterEmbarcacoes(): Flow<Result<List<Embarcacao>>> {
        // Implementação para obter todas as embarcações
        TODO("Implementar obtenção de embarcações")
    }
}