package com.mario.omiechallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mario.omiechallenge.presentation.screen.order.OrderScreen
import com.mario.omiechallenge.presentation.screen.order.OrderViewModel
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemScreen
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Order.route
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Order.route) {
            val viewModel = koinViewModel<OrderViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            OrderScreen(
                uiState = uiState,
                navigateToAddOrder = {
                    navController.navigate(
                        route = Screen.OrderItem.route
                    )
                }
            )
        }

        composable(route = Screen.OrderItem.route) {
            val viewModel = koinViewModel<OrderItemViewModel>()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            OrderItemScreen(
                uiState = uiState,
                onEvent = viewModel::onEvent,
                goBackToOrders = {
                    navController.popBackStack()
                }
            )
        }
    }
}