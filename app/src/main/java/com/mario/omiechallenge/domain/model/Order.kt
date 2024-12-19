package com.mario.omiechallenge.domain.model

import java.util.Date
import java.util.UUID

data class Order(
    val id: String = UUID.randomUUID().toString(),
    val date: Date = Date(),
    val clientName: String,
    val items: List<OrderItem>
) {
    val total: Double
        get() = items.sumOf { it.total }
}
