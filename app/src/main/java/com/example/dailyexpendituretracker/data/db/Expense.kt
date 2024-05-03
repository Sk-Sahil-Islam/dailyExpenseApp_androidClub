package com.example.dailyexpendituretracker.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.Locale

@Entity(
    tableName = "expenses"
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val amount: Double = 0.0,
    val date: String = "",
    val title: String = "",
    var dateLong: Long = 0L
)