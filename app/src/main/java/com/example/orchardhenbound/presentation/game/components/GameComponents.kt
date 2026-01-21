package com.example.orchardhenbound.presentation.game.components

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.game.components.GameOverOverlay
import com.example.orchardhenbound.presentation.game.components.HeartsRow
import com.example.orchardhenbound.presentation.game.components.PauseOverlay
import com.example.orchardhenbound.presentation.game.components.ScorePlate
import com.example.orchardhenbound.utils.clickableNoRipple
import com.example.orchardhenbound.utils.getDrawableRes
import com.example.orchardhenbound.utils.offsetPx

private const val MAX_LIVES = 3

