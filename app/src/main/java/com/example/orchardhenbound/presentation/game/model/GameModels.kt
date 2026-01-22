package com.example.orchardhenbound.presentation.game.model

enum class ItemType {
    GOOD_1,
    GOOD_2,
    GOOD_3,
    GOOD_4,
    GOOD_5,
    BAD_1,
    BAD_2
}

data class FallingItem(
    val id: Long,
    val type: ItemType,
    val xPx: Float,
    val yPx: Float,
    val speedPxPerSec: Float
)
