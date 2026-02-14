package com.example.simpleexpensetracker.domain.repository

import com.example.simpleexpensetracker.domain.model.Expense

interface Repository {
    fun getExpenses(): List<Expense>
    fun deleteExpenses(expense: Expense)
    fun addExpense(expense: Expense)
    fun getTotal(): Double

}