package com.mario.omiechallenge.data.repository

import com.mario.omiechallenge.data.datasource.LocalDatSource
import com.mario.omiechallenge.domain.Repository
import com.mario.omiechallenge.domain.model.Order

class RepositoryImpl(
    private val localDatSource: LocalDatSource
) : Repository {

    override fun getAllOrders(): Result<List<Order>> {
        return localDatSource.getAllOrders()
    }

    override fun saveOrder(order: Order) {
        localDatSource.addOrder(order)
    }

}