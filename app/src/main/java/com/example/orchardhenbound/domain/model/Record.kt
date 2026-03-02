package com.example.orchardhenbound.domain.model

data class Record(
    val id: Long = 0,
    val date: Long,
    val score: Int,
    val createdAt: Long = System.currentTimeMillis()
)
