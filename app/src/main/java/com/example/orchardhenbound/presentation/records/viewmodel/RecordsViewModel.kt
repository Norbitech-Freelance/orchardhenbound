package com.example.orchardhenbound.presentation.records.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.orchardhenbound.data.repository.RecordsRepository
import com.example.orchardhenbound.domain.Record
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class RecordsViewModel(
    private val recordsRepository: RecordsRepository
) : ViewModel() {

    val records: StateFlow<List<Record>> = recordsRepository
        .observeTopRecords(limit = 10)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
