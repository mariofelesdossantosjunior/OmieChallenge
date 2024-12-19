package com.mario.omiechallenge.domain

import com.mario.omiechallenge.domain.model.Order

class GetAllOrdersUseCase(
    private val repository: Repository
) {

    operator fun invoke(): Result<List<Order>> {
        return repository.getAllOrders()
    }
}