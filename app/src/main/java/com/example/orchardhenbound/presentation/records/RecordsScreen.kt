package com.example.orchardhenbound.presentation.records

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import com.example.orchardhenbound.domain.model.Record
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orchardhenbound.R
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.components.StrokeText
import com.example.orchardhenbound.presentation.game.components.TopBarRow
import com.example.orchardhenbound.presentation.records.components.RecordPlate
import com.example.orchardhenbound.presentation.records.viewmodel.RecordsViewModel
import com.example.orchardhenbound.ui.theme.ACCENT_BOTTOM
import com.example.orchardhenbound.ui.theme.ACCENT_TOP
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
            TopBarRow(
                buttonText = "RECORDS",
                onClick = onBack
            )

            if (records.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StrokeText(
                        text = stringResource(R.string.records_empty),
                        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily!!,
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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        bottom = 24.dp
                    )
                ) {
                    itemsIndexed(records) { index, record ->
                        RecordPlate(record = record, dimmed = index >= 3)
                    }
                }
            }
        }
    }
}
