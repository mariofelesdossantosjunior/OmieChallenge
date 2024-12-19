package com.mario.omiechallenge.domain

import com.mario.omiechallenge.domain.model.Order

interface Repository {
    fun getAllOrders(): Result<List<Order>>
    fun saveOrder(order: Order)
}