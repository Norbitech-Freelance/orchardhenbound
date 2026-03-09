package com.example.orchardhenbound.ui.presentation.game

import com.example.orchardhenbound.domain.model.FallingItem

data class GameUiState(
    val isPaused: Boolean = false,
    val isGameOver: Boolean = false,
    val lives: Int = 3,
    val score: Int = 0,
    val playerX: Float = 0f,
    val facingRight: Boolean = true,
    val items: List<FallingItem> = emptyList(),
    val isScoreSaved: Boolean = false
)
