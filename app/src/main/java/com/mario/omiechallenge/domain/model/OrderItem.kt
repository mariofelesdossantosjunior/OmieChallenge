package com.mario.omiechallenge.domain.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class OrderItem(
    val id: String =  UUID.randomUUID().toString(),
    val name: String = "",
    val quantity: Int = 0,
    val unitPrice: Double = 0.0,
    val total: Double = 0.0
)
