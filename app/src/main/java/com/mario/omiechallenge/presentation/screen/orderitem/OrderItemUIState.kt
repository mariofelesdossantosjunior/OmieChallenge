package com.mario.omiechallenge.presentation.screen.orderitem

import com.mario.omiechallenge.domain.model.OrderItem

data class OrderItemUIState(
    val client: String = "",
    val productName: String = "",
    val productQuantity: String = "",
    val productValue: String = "",
    val items: List<OrderItem> = emptyList(),
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
) {
    val total: Double get() = items.sumOf { it.total }
}