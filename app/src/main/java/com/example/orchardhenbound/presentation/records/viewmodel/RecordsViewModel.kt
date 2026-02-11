package com.example.orchardhenbound.presentation.records.viewmodel

import androidx.lifecycle.ViewModel
import com.example.orchardhenbound.domain.model.Record
import androidx.lifecycle.viewModelScope
import com.example.orchardhenbound.data.repository.RecordsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class RecordsViewModel(
    private val recordsRepository: RecordsRepository
) : ViewModel() {

    val records: StateFlow<List<Record>> = recordsRepository
        .observeTopRecords(limit = 6)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
