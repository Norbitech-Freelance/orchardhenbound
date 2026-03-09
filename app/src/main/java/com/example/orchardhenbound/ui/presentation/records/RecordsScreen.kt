package com.example.orchardhenbound.ui.presentation.records

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.orchardhenbound.R
import com.example.orchardhenbound.domain.model.Record
import com.example.orchardhenbound.ui.presentation.components.AccentOutlinedText
import com.example.orchardhenbound.ui.presentation.components.BackButton
import com.example.orchardhenbound.ui.presentation.components.FullScreenBackground
import com.example.orchardhenbound.ui.presentation.components.TitleOutlinedText
import com.example.orchardhenbound.ui.presentation.components.RecordItem

@Composable
fun RecordsScreen(
    viewModel: RecordsViewModel,
    onBack: () -> Unit
) {
    val records by viewModel.records.collectAsStateWithLifecycle()

    RecordsContent(
        records = records,
        onBack = onBack
    )
}

@Composable
private fun RecordsContent(
    records: List<Record>,
    onBack: () -> Unit
) {
    val bgRes = if (records.isEmpty()) {
        R.drawable.bg_records
    } else {
        R.drawable.bg_records
    }

    Box(modifier = Modifier.fillMaxSize()) {
        FullScreenBackground(
            backgroundRes = bgRes,
            contentDescription = stringResource(R.string.cd_records_background)
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.06f))

            Row(
                modifier = Modifier.fillMaxWidth(0.85f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButton(
                    onClick = onBack
                )

                Spacer(modifier = Modifier.weight(1f))

                TitleOutlinedText(
                    text = stringResource(R.string.records_title),
                    maxFontSize = 40.sp
                )
            }

            Spacer(modifier = Modifier.fillMaxHeight(0.05f))

            if (records.isEmpty()) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.weight(0.15f))

                    AccentOutlinedText(
                        text = stringResource(R.string.records_empty),
                        maxFontSize = 36.sp
                    )

                    Spacer(modifier = Modifier.weight(1f))
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
@Preview(showBackground = true, device = "id:pixel_7_pro")
@Composable
fun RecordsScreenEmptyPreview() {
    MaterialTheme {
        RecordsContent(
            records = emptyList(),
            onBack = {}
        )
    }
}