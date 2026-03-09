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

    override fun observeRecords(): Flow<List<Record>> =
        dao.observeAllRecords().map { entities ->
            val allRecords = entities.map { it.toDomain() }

            val top3 = allRecords.take(3)

            val recent3 = allRecords
                .drop(3)
                .sortedByDescending { it.createdAt }
                .take(3)

            top3 + recent3
        }

    override suspend fun addScore(score: Int) {
        dao.insert(
            RecordEntity(
                score = score
            )
        )
    }
}