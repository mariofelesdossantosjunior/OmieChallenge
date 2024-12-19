package com.mario.omiechallenge.data.datasource

import com.mario.omiechallenge.domain.model.Order
import com.mario.omiechallenge.domain.model.OrderItem
import java.util.Date

val fakeOrders = listOf(
    Order(
        id = "123456789",
        date = Date(),
        clientName = "Mario",
        items = listOf(
            OrderItem(
                id = "1",
                name = "Item 1",
                unitPrice = 20.0,
                quantity = 2,
                total = 40.0
            ),
            OrderItem(
                id = "2",
                name = "Item 2",
                unitPrice = 10.0,
                quantity = 6,
                total = 60.0
            ),
        )
    ),
    Order(
        id = "3456789",
        date = Date(),
        clientName = "Jose",
        items = listOf(
            OrderItem(
                id = "1",
                name = "Item 3",
                unitPrice = 100.0,
                quantity = 1,
                total = 100.0
            ),
            OrderItem(
                id = "2",
                name = "Item 4",
                unitPrice = 20.0,
                quantity = 10,
                total = 200.0
            )
        )
    ),
)