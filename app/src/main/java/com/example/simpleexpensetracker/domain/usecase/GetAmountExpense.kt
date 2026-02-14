package com.example.simpleexpensetracker.domain.usecase

import com.example.simpleexpensetracker.domain.repository.Repository

class GetAmountExpense(private val repository: Repository) {
    operator fun invoke(): Double = repository.getTotal()
}