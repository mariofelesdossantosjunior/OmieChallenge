package com.mario.omiechallenge.domain.model

import java.util.UUID

data class OrderItem(
    val id: String =  UUID.randomUUID().toString(),
    val name: String,
    val quantity: Int,
    val unitPrice: Double,
    val total: Double
)
