package com.example.dailyexpendituretracker.data.repository

import android.util.Log
import com.example.dailyexpendituretracker.data.db.Expense
import com.example.dailyexpendituretracker.data.db.ExpenseDao
import com.example.dailyexpendituretracker.domain.repository.ExpenseRepository
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ExpenseRepositoryImpl @Inject constructor(
    private val expenseDao: ExpenseDao
): ExpenseRepository {
    override fun getExpenses() = expenseDao.getAllExpenses()

    override suspend fun addExpense(expense: Expense) = expenseDao.insertExpense(expense)

    override suspend fun deleteExpense(expense: Expense) = expenseDao.deleteExpense(expense)

    override suspend fun updateExpense(expense: Expense) = expenseDao.insertExpense(expense)

    override suspend fun getTotalExpensesForPastSixDays(): Map<String, Double> {
        val sixDaysAgo = System.currentTimeMillis() - 6 * 24 * 60 * 60 * 1000
        val expenses = expenseDao.getExpensesForPastSixDays(sixDaysAgo)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val result = expenses.groupBy { expense -> format.format(Date(expense.dateLong)) }
            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }
        Log.e("ExpenseRepositoryImpl", "getTotalExpensesForPastSixDays: $result")
        return result
    }
}