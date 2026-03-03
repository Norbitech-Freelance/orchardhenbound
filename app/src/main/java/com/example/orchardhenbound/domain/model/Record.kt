package com.example.orchardhenbound.domain.model

data class Record(
    val id: Long = 0,
<<<<<<< HEAD
    val date: Long,
=======
    val date: String,
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
    val score: Int,
    val createdAt: Long = System.currentTimeMillis()
)
