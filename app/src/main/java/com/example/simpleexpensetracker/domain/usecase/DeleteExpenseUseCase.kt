package com.example.simpleexpensetracker.domain.usecase

import com.example.simpleexpensetracker.domain.model.Expense
import com.example.simpleexpensetracker.domain.repository.Repository

class DeleteExpenseUseCase(private val repository: Repository) {
    operator fun invoke(expense: Expense) = repository.deleteExpenses(expense)
}