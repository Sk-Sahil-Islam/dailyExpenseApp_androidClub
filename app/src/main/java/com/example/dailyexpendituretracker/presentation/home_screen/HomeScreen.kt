package com.example.dailyexpendituretracker.presentation.home_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dailyexpendituretracker.data.db.Expense
import com.example.dailyexpendituretracker.domain.viewmodel.ExpenseViewModel
import com.example.dailyexpendituretracker.presentation.Screen
import com.example.dailyexpendituretracker.presentation.home_screen.componants.ExpenseAddDialog
import com.example.dailyexpendituretracker.presentation.home_screen.componants.ExpenseItem
import com.example.dailyexpendituretracker.presentation.home_screen.componants.ExpenseUpdateDialog

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ExpenseViewModel = hiltViewModel(),
    navController: NavController
) {
    val state by viewModel.state.collectAsState()
    var showAddDialog by remember { mutableStateOf(false) }
    var showEditDialog by remember { mutableStateOf(false) }
    var currentExpense by remember { mutableStateOf(Expense()) }

    Scaffold(
        bottomBar = {
            if (navController.currentBackStackEntry?.destination?.route == Screen.HomeScreen.route) {
                BottomAppBar(
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(Screen.WeeklyExpenseScreen.route)
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.DateRange,
                                contentDescription = "weekly expense"
                            )
                        }
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = { showAddDialog = true }) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "add"
                            )
                        }
                    }
                )
            }
        }) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.expenses.size) {
                    ExpenseItem(
                        expense = state.expenses[it],
                        onDelete = { expense ->
                            viewModel.deleteExpense(expense)
                        },
                        onEdit = { expense ->
                            currentExpense = expense
                            showEditDialog = true
                        }
                    )
                }
            }

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = androidx.compose.ui.Alignment.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator()
                }
            }

            ExpenseAddDialog(
                showDialog = showAddDialog,
                expense = currentExpense,
                onDismissRequest = {
                    currentExpense = Expense()
                    showAddDialog = false
                },
                onCancel = {
                    currentExpense = Expense()
                    showAddDialog = false
                },
                onAdd = {
                    currentExpense = Expense()
                    viewModel.addExpense(it)
                    showAddDialog = false
                }
            )
            ExpenseUpdateDialog(
                showDialog = showEditDialog,
                expense = currentExpense,
                onDismissRequest = {
                    currentExpense = Expense()
                    showEditDialog = false
                },
                onCancel = {
                    currentExpense = Expense()
                    showEditDialog = false
                },
                onUpdate = {
                    viewModel.addExpense(it)
                    currentExpense = Expense()
                    showEditDialog = false
                }
            )
        }

    }

}