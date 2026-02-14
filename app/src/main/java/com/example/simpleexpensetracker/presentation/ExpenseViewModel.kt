package com.example.simpleexpensetracker.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.simpleexpensetracker.data.RepositoryImpl
import com.example.simpleexpensetracker.domain.model.Expense
import com.example.simpleexpensetracker.domain.usecase.AddExpenseUseCase
import com.example.simpleexpensetracker.domain.usecase.DeleteExpenseUseCase
import com.example.simpleexpensetracker.domain.usecase.GetAmountExpense
import com.example.simpleexpensetracker.domain.usecase.GetExpenseUseCase

class ExpenseViewModel: ViewModel() {
    private val repository = RepositoryImpl()
    private val getExpenseUseCase = GetExpenseUseCase(repository)
    private val addExpenseUseCase = AddExpenseUseCase(repository)
    private val deleteExpenseUseCase = DeleteExpenseUseCase(repository)
    private val getAmountExpense = GetAmountExpense(repository)

    val expenses = mutableStateListOf<Expense>()

    fun load(){
        expenses.clear()
        expenses.addAll(getExpenseUseCase())
    }

    fun addExpense(expense: Expense){
        addExpenseUseCase(expense)
        load()
    }

    fun deleteAmount(expense: Expense){
        deleteExpenseUseCase(expense)
        load()
    }

    fun getTotal(){
        getAmountExpense()
    }
}