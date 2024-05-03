package com.example.dailyexpendituretracker.presentation

sealed class Screen(val route: String){
    data object HomeScreen: Screen("home_screen")
    data object WeeklyExpenseScreen: Screen("weekly_expense_screen")
}