package com.example.orchardhenbound.data.repository

import com.example.orchardhenbound.data.local.RecordDao
import com.example.orchardhenbound.data.local.RecordEntity
import com.example.orchardhenbound.data.local.toDomain
import com.example.orchardhenbound.domain.model.Record
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
<<<<<<< HEAD
=======
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8

class RecordsRepositoryImpl(
    private val dao: RecordDao
) : RecordsRepository {

    override fun observeTopRecords(limit: Int): Flow<List<Record>> =
        dao.observeTop(limit).map { entities -> entities.map { it.toDomain() } }

    override suspend fun addScore(score: Int) {
<<<<<<< HEAD
        val currentTime = System.currentTimeMillis()
        dao.insert(
            RecordEntity(
                date = currentTime,
=======
        val date = SimpleDateFormat("dd.MM", Locale.getDefault()).format(Date())
        dao.insert(
            RecordEntity(
                date = date,
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
                score = score
            )
        )
    }

    override suspend fun clearAllRecords() {
        dao.clear()
    }
}
