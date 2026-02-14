package com.example.simpleexpensetracker.domain.usecase

import com.example.simpleexpensetracker.domain.model.Expense
import com.example.simpleexpensetracker.domain.repository.Repository

class AddExpenseUseCase(private val repository: Repository) {
    operator fun invoke(expense: Expense) = repository.addExpense(expense)
}