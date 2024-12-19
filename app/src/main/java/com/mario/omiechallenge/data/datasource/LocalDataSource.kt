package com.mario.omiechallenge.data.datasource

import com.mario.omiechallenge.domain.model.Order

class LocalDataSource {

    private val orders = mutableListOf<Order>()

    fun getAllOrders(): Result<List<Order>> {
        return Result.success(orders)
    }

    fun addOrder(order: Order) {
        orders.add(order)
    }

}