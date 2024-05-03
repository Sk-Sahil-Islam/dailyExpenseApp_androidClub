package com.example.dailyexpendituretracker.domain.state

import com.example.dailyexpendituretracker.data.db.Expense

data class ExpenseState(
    var expenses: List<Expense> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
