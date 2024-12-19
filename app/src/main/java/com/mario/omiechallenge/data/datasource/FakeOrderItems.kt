package com.mario.omiechallenge.data.datasource

import com.mario.omiechallenge.domain.model.OrderItem

val fakeOrderItems = listOf(
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
    )
)