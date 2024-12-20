package com.mario.omiechallenge.domain.model

import com.mario.omiechallenge.util.formatedDate
import kotlinx.serialization.Serializable
import java.util.Date
import java.util.UUID

@Serializable
data class Order(
    val id: String = UUID.randomUUID().toString(),
    val date: String = Date().formatedDate("dd/MM/yyyy"),
    val clientName: String = "",
    val items: List<OrderItem> = emptyList()
) {
    val total: Double get() = items.sumOf { it.total }
}
