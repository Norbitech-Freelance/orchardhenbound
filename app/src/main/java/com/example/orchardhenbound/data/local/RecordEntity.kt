package com.example.orchardhenbound.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.orchardhenbound.domain.model.Record

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
<<<<<<< HEAD
    val date: Long,
=======
    val date: String,
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
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