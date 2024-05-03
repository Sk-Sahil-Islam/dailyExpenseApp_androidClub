package com.example.dailyexpendituretracker.domain.repository

import com.example.dailyexpendituretracker.data.db.Expense
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    fun getExpenses(): Flow<List<Expense>>
    suspend fun addExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun getTotalExpensesForPastSixDays(): Map<String, Double>
}