package com.example.orchardhenbound.data.repository

import com.example.orchardhenbound.data.local.RecordDao
import com.example.orchardhenbound.data.local.RecordEntity
import com.example.orchardhenbound.data.local.toDomain
import com.example.orchardhenbound.domain.model.Record
import com.example.orchardhenbound.domain.repository.RecordsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecordsRepositoryImpl(
    private val dao: RecordDao
) : RecordsRepository {

    override fun observeTopRecords(limit: Int): Flow<List<Record>> =
        dao.observeTop(limit).map { entities -> entities.map { it.toDomain() } }

    override suspend fun addScore(score: Int) {
        val date = SimpleDateFormat("dd.MM", Locale.getDefault()).format(Date())
        dao.insert(
            RecordEntity(
                date = date,
                score = score
            )
        )
    }

    override suspend fun clearAllRecords() {
        dao.clear()
    }
}
