package com.mario.omiechallenge.presentation.navigation

sealed class Screen(val route: String) {
    data object Order : Screen(route = "order")
    data object OrderItem : Screen(route = "order_item")
}