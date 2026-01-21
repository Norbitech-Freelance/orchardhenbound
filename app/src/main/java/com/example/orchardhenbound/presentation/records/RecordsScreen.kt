package com.example.orchardhenbound.presentation.records

import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.domain.model.Record
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.components.StrokeText
import com.example.orchardhenbound.ui.theme.ACCENT_BOTTOM
import com.example.orchardhenbound.ui.theme.ACCENT_TOP
import com.example.orchardhenbound.ui.theme.BalooFontFamily
import com.example.orchardhenbound.ui.theme.PLATE_BOTTOM
import com.example.orchardhenbound.ui.theme.PLATE_TOP
import com.example.orchardhenbound.ui.theme.STROKE_PRIMARY

@Composable
fun RecordsScreen(
    viewModel: RecordsViewModel,
    onBack: () -> Unit,
    @DrawableRes emptyBackgroundRes: Int = R.drawable.bg_records_empty,
    @DrawableRes fullBackgroundRes: Int = R.drawable.bg_records_full
) {
    val records by viewModel.records.collectAsState()

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

        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, top = 60.dp)
                    .height(60.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(onClick = onBack)

                StrokeText(
                    text = stringResource(R.string.records_title),
                    fontFamily = BalooFontFamily,
                    fontSize = 40.sp,
                    fillBrush = SolidColor(Color.White),
                    strokeColor = STROKE_PRIMARY,
                    strokeWidth = 8f
                )
            }

            if (records.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StrokeText(
                        text = stringResource(R.string.records_empty),
                        fontFamily = BalooFontFamily,
                        fontSize = 32.sp,
                        fillBrush = Brush.linearGradient(listOf(ACCENT_TOP, ACCENT_BOTTOM)),
                        strokeColor = STROKE_PRIMARY,
                        strokeWidth = 6f
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 24.dp, end = 24.dp, top = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    itemsIndexed(records) { index, record ->
                        RecordPlate(record = record, dimmed = index >= 3)
                    }

                    item { Spacer(modifier = Modifier.height(24.dp)) }
                }
            }
        }
    }
}

@Composable
private fun RecordPlate(
    record: Record,
    dimmed: Boolean
) {
    val bgAlpha = if (dimmed) 0.55f else 1f
    val borderAlpha = if (dimmed) 0.55f else 1f

    val plateBrush = Brush.linearGradient(
        colors = listOf(
            PLATE_TOP.copy(alpha = bgAlpha),
            PLATE_BOTTOM.copy(alpha = bgAlpha)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(brush = plateBrush, shape = RoundedCornerShape(50.dp))
            .border(
                width = 1.dp,
                color = STROKE_PRIMARY.copy(alpha = borderAlpha),
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 28.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val accentBrush = Brush.linearGradient(listOf(ACCENT_TOP, ACCENT_BOTTOM))

            StrokeText(
                text = record.date,
                fontFamily = BalooFontFamily,
                fontSize = 32.sp,
                fillBrush = accentBrush,
                strokeColor = STROKE_PRIMARY,
                strokeWidth = 6f
            )

            StrokeText(
                text = record.score.toString(),
                fontFamily = BalooFontFamily,
                fontSize = 32.sp,
                fillBrush = accentBrush,
                strokeColor = STROKE_PRIMARY,
                strokeWidth = 6f
            )
        }
    }
}
