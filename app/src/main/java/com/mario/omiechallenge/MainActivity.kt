package com.mario.omiechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mario.omiechallenge.domain.model.Order
import com.mario.omiechallenge.presentation.route.OrderItemsRoute
import com.mario.omiechallenge.presentation.route.OrderRoute
import com.mario.omiechallenge.presentation.screen.order.OrderScreen
import com.mario.omiechallenge.presentation.screen.order.OrderViewModel
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemScreen
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemViewModel
import com.mario.omiechallenge.presentation.screen.theme.OmieTheme
import org.koin.compose.viewmodel.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OmieTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = OrderRoute
                ) {
                    composable<OrderRoute> {
                        val viewModel = koinViewModel<OrderViewModel>()
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                        OrderScreen(
                            uiState = uiState,
                            navigateToAddOrder = {
                                navController.navigate(
                                    route = OrderItemsRoute()
                                )
                            },
                            navigateToEditOrderItem = { selectedOrder ->
                                navController.navigate(
                                    route = OrderItemsRoute(
                                        id = selectedOrder.id
                                    )
                                )
                            }
                        )
                    }

                    composable<OrderItemsRoute> {
                        val orderId = it.toRoute<OrderItemsRoute>().id

                        val viewModel = koinViewModel<OrderItemViewModel>()
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                        OrderItemScreen(
                            uiState = uiState,
                            onEvent = viewModel::onEvent,
                            orderId = orderId,
                            goBackToOrders = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}