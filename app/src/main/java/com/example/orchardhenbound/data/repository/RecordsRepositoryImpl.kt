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
        if (score <= 0) return

        val recordsCount = dao.getRecordsCount()

        if (recordsCount < 6) {
            dao.insert(
                RecordEntity(
                    score = score
                )
            )
        } else {
            val minScore = dao.getMinScore() ?: 0

            if (score > minScore) {
                dao.deleteWorstRecord()
                dao.insert(
                    RecordEntity(
                        score = score
                    )
                )
            }
        }
    }
}
