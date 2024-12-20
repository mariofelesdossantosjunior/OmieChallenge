package com.mario.omiechallenge.presentation.screen.orderitem

import com.mario.omiechallenge.domain.model.OrderItem

sealed class OrderItemUIEvent {
    data class OnClientChanged(val client: String) : OrderItemUIEvent()
    data class OnProductNameChanged(val productName: String) : OrderItemUIEvent()
    data class OnProductQuantityChanged(val productQuantity: String) : OrderItemUIEvent()
    data class OnProductValueChanged(val productValue: String) : OrderItemUIEvent()
    data object OnAddItem : OrderItemUIEvent()
    data class OnDeleteItem(val item: OrderItem) : OrderItemUIEvent()
    data object OnSaveOrUpdateOrder : OrderItemUIEvent()
    data class LoadOrderItem(val orderId: String) : OrderItemUIEvent()
}