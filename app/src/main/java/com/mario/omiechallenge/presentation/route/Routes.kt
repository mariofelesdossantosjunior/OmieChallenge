package com.mario.omiechallenge.presentation.route

import kotlinx.serialization.Serializable

@Serializable
data object OrderRoute

@Serializable
data class OrderItemsRoute(
    val id: String? = null
)