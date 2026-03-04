package com.example.orchardhenbound.utils.extensions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner

fun Modifier.clickableNoRipple(onClick: () -> Unit): Modifier = composed {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        if (lifecycle.currentState == Lifecycle.State.RESUMED) {
            onClick()
        }
    }
}
