package com.example.orchardhenbound.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {

    @Query("SELECT * FROM records WHERE score > 0 ORDER BY score DESC LIMIT :limit")
    fun observeTop(limit: Int = 6): Flow<List<RecordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: RecordEntity)

    @Query("SELECT COUNT(*) FROM records WHERE score > 0")
    suspend fun getRecordsCount(): Int

    @Query("SELECT MIN(score) FROM records WHERE score > 0")
    suspend fun getMinScore(): Int?

    @Query("DELETE FROM records WHERE id IN (SELECT id FROM records WHERE score > 0 ORDER BY score ASC, createdAt ASC LIMIT 1)")
    suspend fun deleteWorstRecord()
}
