package com.example.dailyexpendituretracker.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyexpendituretracker.data.db.Expense
import com.example.dailyexpendituretracker.domain.repository.ExpenseRepository
import com.example.dailyexpendituretracker.domain.state.ExpenseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {

    private val _state = MutableStateFlow(ExpenseState())
    val state: StateFlow<ExpenseState> get() = _state

    init {
        loadState()
    }

    private fun loadState() {
        viewModelScope.launch {
            try {
                repository.getExpenses().collectLatest {
                    _state.value = _state.value.copy(
                        expenses = it
                    )
                }
                _state.value = _state.value.copy(
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message!!,
                    isLoading = false
                )
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isLoading = true
                )
                repository.addExpense(expense)
                _state.value = _state.value.copy(
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message!!,
                    isLoading = false
                )
            }
        }
    }

    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    isLoading = true
                )
                repository.deleteExpense(expense)
                _state.value = _state.value.copy(
                    isLoading = false
                )
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    error = e.message!!,
                    isLoading = false
                )
            }
        }
    }


}