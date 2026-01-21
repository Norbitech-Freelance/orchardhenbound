package com.example.orchardhenbound.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orchardhenbound.domain.model.Record

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val score: Int,
    val createdAt: Long = System.currentTimeMillis()
)

fun RecordEntity.toDomain() = Record(
    id = id,
    date = date,
    score = score,
    createdAt = createdAt
)

fun Record.toEntity() = RecordEntity(
    id = id,
    date = date,
    score = score,
    createdAt = createdAt
)