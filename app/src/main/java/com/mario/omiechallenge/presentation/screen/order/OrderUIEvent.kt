package com.mario.omiechallenge.presentation.screen.order

sealed class OrderUIEvent {
    data object LoadOrders : OrderUIEvent()
}