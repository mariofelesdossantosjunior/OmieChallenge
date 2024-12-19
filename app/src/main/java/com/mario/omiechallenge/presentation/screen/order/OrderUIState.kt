package com.mario.omiechallenge.presentation.screen.order

import com.mario.omiechallenge.domain.model.Order

data class OrderUIState(
    val isLoading: Boolean = false,
    val isFailure: Boolean = false,
    val orders: List<Order> = emptyList()
)