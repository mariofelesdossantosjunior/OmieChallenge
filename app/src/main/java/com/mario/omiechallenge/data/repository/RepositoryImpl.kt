package com.mario.omiechallenge.data.repository

import com.mario.omiechallenge.data.datasource.LocalDataSource
import com.mario.omiechallenge.domain.Repository
import com.mario.omiechallenge.domain.model.Order

class RepositoryImpl(
    private val localDataSource: LocalDataSource
) : Repository {

    override fun getAllOrders(): Result<List<Order>> {
        return localDataSource.getAllOrders()
    }

    override fun add(order: Order) {
        localDataSource.addOrder(order)
    }

    override fun update(order: Order) {
        localDataSource.updateOrder(order)
    }

}