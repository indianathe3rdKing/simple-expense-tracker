package com.example.simpleexpensetracker.data

import com.example.simpleexpensetracker.domain.model.Expense
import com.example.simpleexpensetracker.domain.repository.Repository

class RepositoryImpl: Repository {
    private var expenses = mutableListOf<Expense>()
    private var nextId = 0

    override fun getExpenses(): List<Expense> =
        expenses.toList()


    override fun deleteExpenses(expense: Expense) {
        expenses.removeIf { it.id == expense.id }
    }

    override fun addExpense(expense: Expense) {
        expenses.add(expense.copy(id = nextId++))
    }

    override fun getTotal(): Double =
       expenses.sumOf { it.amount }

}