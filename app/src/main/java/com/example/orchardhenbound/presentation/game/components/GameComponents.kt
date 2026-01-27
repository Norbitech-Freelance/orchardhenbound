package com.example.orchardhenbound.presentation.game.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.game.GameViewModel
import com.example.orchardhenbound.presentation.game.model.ItemType
import com.example.orchardhenbound.utils.extensions.clickableNoRipple

private const val MAX_LIVES = 3

@Composable
fun GameContent(
    viewModel: GameViewModel,
    onPause: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.state.collectAsState()
    val density = LocalDensity.current

    LaunchedEffect(Unit) {
        viewModel.startLoop()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.stopLoop()
        }
    }

    BackHandler {
        onPause()
    }

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    viewModel.onDrag(dragAmount.x)
                }
            }
    ) {
        val screenWpx = with(density) { maxWidth.toPx() }
        val screenHpx = with(density) { maxHeight.toPx() }
        val itemSizePx = with(density) { 40.dp.toPx() }  // ИЗМЕНЕНО: 60 → 40
        val playerWidthPx = with(density) { 120.dp.toPx() }
        val playerHeightPx = with(density) { 120.dp.toPx() }

        LaunchedEffect(screenWpx, screenHpx) {
            if (screenWpx > 0f && screenHpx > 0f) {
                viewModel.onScreenMetrics(
                    wPx = screenWpx,
                    hPx = screenHpx,
                    itemSizePx = itemSizePx,
                    playerWidthPx = playerWidthPx,
                    playerHeightPx = playerHeightPx
                )
            }
        }

        // Адаптивные позиции для UI элементов
        val baseW = 412f
        val baseH = 917f

        // Пауза и сердечки на одной высоте
        val uiTop = maxHeight * (60f / baseH)
        val uiLeft = maxWidth * (24f / baseW)
        val uiRight = maxWidth * (24f / baseW)

        // ScorePlate
        val scorePlateWidth = maxWidth * (120f / baseW)
        val scorePlateLeft = maxWidth * (146f / baseW)
        val scorePlateTop = maxHeight * (150f / baseH)

        // Сердечки
        val heartsWidth = maxWidth * (136f / baseW)
        val heartsHeight = maxHeight * (33f / baseH)

        // Адаптивный размер предметов
        val itemSize = maxWidth * (40f / baseW)  // ДОБАВЛЕНО: 40/412 = 9.7%

        // СНАЧАЛА падающие предметы (ниже по z-order)
        state.items.forEach { item ->
            val res = when (item.type) {
                ItemType.GOOD_1 -> R.drawable.item_good_1
                ItemType.GOOD_2 -> R.drawable.item_good_2
                ItemType.GOOD_3 -> R.drawable.item_good_3
                ItemType.GOOD_4 -> R.drawable.item_good_4
                ItemType.GOOD_5 -> R.drawable.item_good_5
                ItemType.BAD_1 -> R.drawable.item_bad_1
                ItemType.BAD_2 -> R.drawable.item_bad_2
            }

            Image(
                painter = painterResource(id = res),
                contentDescription = null,
                modifier = Modifier
                    .size(itemSize)  // ИЗМЕНЕНО: 60.dp → адаптивный itemSize
                    .align(Alignment.TopStart)
                    .offset(
                        x = with(density) { item.xPx.toDp() },
                        y = with(density) { item.yPx.toDp() }
                    ),
                contentScale = ContentScale.Fit
            )
        }

        // Персонаж (курица)
        val playerRes = if (state.facingRight) {
            R.drawable.player_right
        } else {
            R.drawable.player_left
        }

        val playerYRatio = 699f / 892f
        val playerY = maxHeight * playerYRatio

        Image(
            painter = painterResource(id = playerRes),
            contentDescription = "Player",
            modifier = Modifier
                .size(120.dp, 120.dp)
                .align(Alignment.TopStart)
                .offset(
                    x = with(density) { state.playerX.toDp() },
                    y = playerY
                ),
            contentScale = ContentScale.Fit
        )

        // ПОТОМ UI элементы (сверху по z-order)

        // Кнопка паузы (слева, top 60, left 24)
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = uiLeft, y = uiTop)
                .size(57.dp, 60.dp)
                .clickableNoRipple(onClick = onPause)
        ) {
            Image(
                painter = painterResource(id = R.drawable.btn_pause),
                contentDescription = "Pause",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }

        // Сердечки (справа, top 60, right 24)
        HeartsRow(
            lives = state.lives,
            maxLives = MAX_LIVES,
            totalWidth = heartsWidth,
            heartHeight = heartsHeight,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = -uiRight, y = uiTop)
        )

        // Счет (адаптивная позиция)
        ScorePlate(
            score = state.score,
            width = scorePlateWidth,
            height = 40.dp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(
                    x = scorePlateLeft,
                    y = scorePlateTop
                )
        )
    }
}
