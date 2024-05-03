package com.example.dailyexpendituretracker.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {

    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): Flow<List<Expense>>

    @Upsert
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("SELECT * FROM expenses WHERE dateLong >= :fromDate ORDER BY dateLong DESC LIMIT 6")
    suspend fun getExpensesForPastSixDays(fromDate: Long): List<Expense>
}