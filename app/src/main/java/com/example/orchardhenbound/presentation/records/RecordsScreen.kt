package com.example.orchardhenbound.presentation.records

import androidx.annotation.DrawableRes
<<<<<<< HEAD
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
=======
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
<<<<<<< HEAD
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
=======
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
import com.example.orchardhenbound.R
import com.example.orchardhenbound.domain.model.Record
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.components.StrokeText
<<<<<<< HEAD
import com.example.orchardhenbound.presentation.game.components.RecordPlate
import com.example.orchardhenbound.ui.theme.ACCENT_BOTTOM
import com.example.orchardhenbound.ui.theme.ACCENT_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY
=======
import com.example.orchardhenbound.ui.theme.ACCENT_BOTTOM
import com.example.orchardhenbound.ui.theme.ACCENT_TOP
import com.example.orchardhenbound.ui.theme.PLATE_BOTTOM
import com.example.orchardhenbound.ui.theme.PLATE_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY
import com.example.orchardhenbound.presentation.game.components.RecordPlate
import com.example.orchardhenbound.presentation.game.components.TopBarRow
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8

@Composable
fun RecordsScreen(
    viewModel: RecordsViewModel,
    onBack: () -> Unit,
    @DrawableRes emptyBackgroundRes: Int = R.drawable.bg_records_empty,
    @DrawableRes fullBackgroundRes: Int = R.drawable.bg_records_full
) {
<<<<<<< HEAD
    val records by viewModel.records.collectAsStateWithLifecycle()
=======
    val records by viewModel.records.collectAsState()
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8

    RecordsContent(
        records = records,
        onBack = onBack,
        emptyBackgroundRes = emptyBackgroundRes,
        fullBackgroundRes = fullBackgroundRes
    )
}

@Composable
private fun RecordsContent(
    records: List<Record>,
    onBack: () -> Unit,
    @DrawableRes emptyBackgroundRes: Int,
    @DrawableRes fullBackgroundRes: Int
) {
    val bgRes = if (records.isEmpty()) emptyBackgroundRes else fullBackgroundRes

    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = bgRes,
            contentDescription = stringResource(R.string.cd_records_background)
        )

<<<<<<< HEAD
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp, start = 16.dp, end = 16.dp)
            ) {
                BackButton(
                    onClick = onBack,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                StrokeText(
                    text = "RECORDS",
                    fontFamily = MaterialTheme.typography.bodyLarge.fontFamily!!,
                    fontSize = 32.sp,
                    fillBrush = Brush.linearGradient(listOf(ACCENT_TOP, ACCENT_BOTTOM)),
                    strokeColor = STROKE_PRIMARY,
                    strokeWidth = 6f,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.05f))

            if (records.isEmpty()) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
=======
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarRow(
                buttonText = stringResource(R.string.game_play_again),
                onClick = onBack
            )

            if (records.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
                ) {
                    StrokeText(
                        text = stringResource(R.string.records_empty),
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily!!,
<<<<<<< HEAD
                        fontSize = 36.sp,
=======
                        fontSize = 32.sp,
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
                        fillBrush = Brush.linearGradient(listOf(ACCENT_TOP, ACCENT_BOTTOM)),
                        strokeColor = STROKE_PRIMARY,
                        strokeWidth = 6f
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
<<<<<<< HEAD
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 32.dp)
                ) {
                    itemsIndexed(records) { index, record ->
                        RecordPlate(
                            record = record,
                            dimmed = index >= 3
                        )
                    }
=======
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(records) { index, record ->
                        RecordPlate(record = record, dimmed = index >= 3)
                    }

                    item { Spacer(modifier = Modifier.height(24.dp)) }
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
                }
            }
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
