package com.example.orchardhenbound.presentation.records

import androidx.annotation.DrawableRes
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.orchardhenbound.R
import com.example.orchardhenbound.domain.model.Record
import com.example.orchardhenbound.presentation.components.AccentOutlinedText
import com.example.orchardhenbound.presentation.components.BackButton
import com.example.orchardhenbound.presentation.components.FullScreenBackground
import com.example.orchardhenbound.presentation.components.TitleOutlinedText
import com.example.orchardhenbound.presentation.components.RecordItem

@Composable
fun RecordsScreen(
    viewModel: RecordsViewModel,
    onBack: () -> Unit,
    @DrawableRes emptyBackgroundRes: Int = R.drawable.bg_records_empty,
    @DrawableRes fullBackgroundRes: Int = R.drawable.bg_records_full
) {
    val records by viewModel.records.collectAsStateWithLifecycle()

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

                TitleOutlinedText(
                    text = "RECORDS",
                    maxFontSize = 32.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.05f))

            if (records.isEmpty()) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    AccentOutlinedText(
                        text = stringResource(R.string.records_empty),
                        maxFontSize = 36.sp
                    )

                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 32.dp)
                ) {
                    itemsIndexed(records) { index, record ->
                        RecordItem(
                            record = record,
                            isTopRecord = index < 3
                        )
                    }
                }
            }
        }
    }
}
