package com.example.orchardhenbound.domain

data class Record(
    val id: Long = 0,
    val date: String,
    val score: Int,
    val createdAt: Long = System.currentTimeMillis()
)