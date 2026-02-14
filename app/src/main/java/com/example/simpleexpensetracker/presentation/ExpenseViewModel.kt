package com.example.simpleexpensetracker.presentation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.simpleexpensetracker.data.RepositoryImpl
import com.example.simpleexpensetracker.domain.model.Expense
import com.example.simpleexpensetracker.domain.usecase.AddExpenseUseCase
import com.example.simpleexpensetracker.domain.usecase.DeleteExpenseUseCase
import com.example.simpleexpensetracker.domain.usecase.GetAmountExpense
import com.example.simpleexpensetracker.domain.usecase.GetExpenseUseCase

class ExpenseViewModel : ViewModel() {
    private val repository = RepositoryImpl()
    private val getExpenseUseCase = GetExpenseUseCase(repository)
    private val addExpenseUseCase = AddExpenseUseCase(repository)
    private val deleteExpenseUseCase = DeleteExpenseUseCase(repository)
    private val getAmountExpense = GetAmountExpense(repository)

    val expenses = mutableStateListOf<Expense>()

    fun load() {
        expenses.clear()
        expenses.addAll(getExpenseUseCase())
    }

    fun addExpense(title: String, amount: Double?) {

        if (title.isNotBlank()  && amount!! > 0) {
            addExpenseUseCase(Expense(0, title, amount))
            getTotal()
            load()
        }
    }

    fun deleteAmount(expense: Expense) {
        deleteExpenseUseCase(expense)
        getTotal()
        load()
    }

    fun getTotal(): Double = if(expenses.isNotEmpty())  getAmountExpense() else 0.0

}