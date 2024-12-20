package com.mario.omiechallenge.presentation.screen.orderitem

import com.mario.omiechallenge.domain.model.OrderItem

sealed class OrderItemUIEvent {
    data class OnClientChanged(val client: String) : OrderItemUIEvent()
    data class OnProductNameChanged(val name: String) : OrderItemUIEvent()
    data class OnProductQuantityChanged(val quantity: String) : OrderItemUIEvent()
    data class OnProductPriceChanged(val price: String) : OrderItemUIEvent()
    data object OnAddItem : OrderItemUIEvent()
    data class OnDeleteItem(val item: OrderItem) : OrderItemUIEvent()
    data object OnSaveOrUpdateOrder : OrderItemUIEvent()
    data class LoadOrderItem(val orderId: String) : OrderItemUIEvent()
}