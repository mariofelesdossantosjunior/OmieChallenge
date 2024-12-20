package com.mario.omiechallenge.presentation.screen.orderitem

import com.mario.omiechallenge.domain.model.OrderItem

data class OrderItemUIState(
    val id: String? = null,
    val client: String = "",
    val clientError: String = "",
    val productName: String = "",
    val productError: String = "",
    val productQuantity: String = "",
    val quantityError: String = "",
    val productValue: String = "",
    val valueError: String = "",
    val items: List<OrderItem> = emptyList(),
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
) {
    val isValid: Boolean get() = client.isNotBlank() && items.isNotEmpty()

    val total: Double get() = items.sumOf { it.total }
}