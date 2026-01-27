package com.example.orchardhenbound.data.repository

import com.example.orchardhenbound.data.local.RecordDao
import com.example.orchardhenbound.data.local.RecordEntity
import com.example.orchardhenbound.data.local.toDomain
import com.example.orchardhenbound.domain.model.Record
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
        // Игнорируем нулевые результаты
        if (score <= 0) return

        val date = SimpleDateFormat("dd.MM", Locale.getDefault()).format(Date())
        val recordsCount = dao.getRecordsCount()

        if (recordsCount < 6) {
            // Если меньше 6 записей - просто добавляем
            dao.insert(
                RecordEntity(
                    date = date,
                    score = score
                )
            )
        } else {
            // Если уже 6 записей - проверяем минимальный score
            val minScore = dao.getMinScore() ?: 0

            if (score > minScore) {
                // Новый результат лучше худшего - удаляем худший и добавляем новый
                dao.deleteWorstRecord()
                dao.insert(
                    RecordEntity(
                        date = date,
                        score = score
                    )
                )
            }
            // Иначе игнорируем (новый результат не попал в топ-6)
        }
    }

    override suspend fun clearAllRecords() {
        dao.clear()
    }
}
