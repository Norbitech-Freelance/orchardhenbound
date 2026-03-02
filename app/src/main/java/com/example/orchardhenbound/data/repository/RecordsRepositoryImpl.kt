package com.example.orchardhenbound.data.repository

import com.example.orchardhenbound.data.local.RecordDao
import com.example.orchardhenbound.data.local.RecordEntity
import com.example.orchardhenbound.data.local.toDomain
import com.example.orchardhenbound.domain.model.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecordsRepositoryImpl(
    private val dao: RecordDao
) : RecordsRepository {

    override fun observeTopRecords(limit: Int): Flow<List<Record>> =
        dao.observeTop(limit).map { entities -> entities.map { it.toDomain() } }

    override suspend fun addScore(score: Int) {
        val currentTime = System.currentTimeMillis()
        dao.insert(
            RecordEntity(
                date = currentTime,
                score = score
            )
        )
    }

    override suspend fun clearAllRecords() {
        dao.clear()
    }
}
