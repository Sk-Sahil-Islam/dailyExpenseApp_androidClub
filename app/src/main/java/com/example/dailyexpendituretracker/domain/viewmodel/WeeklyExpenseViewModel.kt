package com.example.dailyexpendituretracker.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyexpendituretracker.domain.repository.ExpenseRepository
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeeklyExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
): ViewModel() {
    private val _barEntries = MutableStateFlow<List<BarEntry>>(emptyList())
    val barEntries: StateFlow<List<BarEntry>> = _barEntries

    init {
        getTotalExpensesForPastSixDays()
    }

    private fun getTotalExpensesForPastSixDays() {
        viewModelScope.launch {
            val totalExpenses = repository.getTotalExpensesForPastSixDays()
            val entries = totalExpenses.toList().mapIndexed { index, (date, total) ->
                BarEntry(index.toFloat(), total.toFloat())
            }
            _barEntries.value = entries
        }
    }
}