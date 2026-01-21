package com.example.orchardhenbound.data.repository

import com.example.orchardhenbound.domain.model.Record
import kotlinx.coroutines.flow.Flow

interface RecordsRepository {
    fun observeTopRecords(limit: Int = 10): Flow<List<Record>>
    suspend fun addScore(score: Int)
    suspend fun clearAllRecords()
}
