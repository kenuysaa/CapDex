package com.example.capdex.data.repository

import com.example.capdex.domain.model.Embarcacao
import kotlinx.coroutines.flow.Flow

interface EmbarcacaoRepository {
    suspend fun salvarEmbarcacao(embarcacao: Embarcacao, proprietarioId: String): Result<Unit> // Adicionado proprietarioId
    fun getEmbarcacoes(): Flow<Result<List<Embarcacao>>>
}