package com.example.dailyexpendituretracker.presentation.weekly_expense_screen

import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailyexpendituretracker.domain.viewmodel.WeeklyExpenseViewModel
import com.example.dailyexpendituretracker.presentation.Screen
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun WeeklyExpenseScreen(
    modifier : Modifier = Modifier,
    viewModel: WeeklyExpenseViewModel = hiltViewModel()
) {
    val descriptionColor = MaterialTheme.colorScheme.onSurface.toArgb()

    val barEntries by viewModel.barEntries.collectAsState()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = "Past 6 days expenses")

        Box(
            modifier = modifier.fillMaxSize()
        ) {

            Log.e("ExpenseRepository", "barEntries: $barEntries")
            val dataSet = BarDataSet(barEntries, "Expenses").apply {
                color = MaterialTheme.colorScheme.primary.toArgb()
                valueTextColor = MaterialTheme.colorScheme.onBackground.toArgb()
                valueTextSize = 25f
            }
            val barData = BarData(dataSet)
            if(barEntries.isNotEmpty()) {

                AndroidView(factory = { context ->
                    BarChart(context).apply {
                        data = barData
                        description.text = ""
                        description.textColor = descriptionColor
                        legend.isEnabled = false
                        xAxis.textColor = Color.WHITE
                        xAxis.position = XAxis.XAxisPosition.BOTTOM
                        xAxis.setDrawGridLines(false)
                        axisLeft.setDrawGridLines(false)
                        axisLeft.textColor = Color.WHITE
                        axisRight.isEnabled = false
                        invalidate()
                    }
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp))
            }
        }
        Text(text = "y-axis: Amount, x-axis: Days")
    }
}