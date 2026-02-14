package com.example.simpleexpensetracker.domain.usecase

import com.example.simpleexpensetracker.domain.model.Expense
import com.example.simpleexpensetracker.domain.repository.Repository

class GetExpenseUseCase(private val repository: Repository) {
    operator fun invoke(): List<Expense> = repository.getExpenses()
}