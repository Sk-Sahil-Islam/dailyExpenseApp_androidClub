package com.example.dailyexpendituretracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailyexpendituretracker.presentation.Screen
import com.example.dailyexpendituretracker.presentation.home_screen.HomeScreen
import com.example.dailyexpendituretracker.presentation.weekly_expense_screen.WeeklyExpenseScreen
import com.example.dailyexpendituretracker.ui.theme.DailyExpenditureTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyExpenditureTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = Screen.HomeScreen.route) {
                        composable(Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(Screen.WeeklyExpenseScreen.route) {
                            WeeklyExpenseScreen() // You need to define this composable
                        }
                    }
                }
            }
        }
    }
}
