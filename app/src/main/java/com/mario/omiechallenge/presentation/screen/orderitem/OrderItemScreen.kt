package com.mario.omiechallenge.presentation.screen.orderitem

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.R
import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.domain.model.Order
import com.mario.omiechallenge.domain.model.OrderItem
import com.mario.omiechallenge.presentation.screen.core.composables.topbar.OmieTopBar
import com.mario.omiechallenge.presentation.screen.orderitem.composables.FormInputValueItem
import com.mario.omiechallenge.presentation.screen.orderitem.composables.OmieBottomBarOrderItems
import com.mario.omiechallenge.presentation.screen.orderitem.composables.OmieOrderItemsList
import com.mario.omiechallenge.presentation.screen.theme.Colors

@Composable
fun OrderItemScreen(
    uiState: OrderItemUIState,
    onEvent: (OrderItemUIEvent) -> Unit,
    goBackToOrders: () -> Unit,
    orderId: String?
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val messageFailure = stringResource(R.string.message_failure_add_order)
    LaunchedEffect(uiState.isFailure) {
        if (uiState.isFailure) {
            snackBarHostState.showSnackbar(
                message = messageFailure
            )
        }
    }

    LaunchedEffect(orderId) {
        if(!orderId.isNullOrEmpty()) {
            onEvent(
                OrderItemUIEvent.LoadOrderItem(orderId)
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            OmieTopBar(
                text = stringResource(R.string.new_order)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                FormInputValueItem(
                    uiState = uiState,
                    onEvent = onEvent
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Colors.Gray300)
                Spacer(modifier = Modifier.height(8.dp))

                OmieOrderItemsList(
                    items = uiState.items,
                    onDeleteItem = {
                        onEvent(
                            OrderItemUIEvent.OnDeleteItem(it)
                        )
                    }
                )
            }
        },
        bottomBar = {
            OmieBottomBarOrderItems(
                uiState = uiState,
                goBackToOrders = goBackToOrders,
                onEvent = onEvent
            )
        }
    )
}

@Preview
@Composable
private fun OrderScreenPreview() {
    OrderItemScreen(
        uiState = OrderItemUIState(
            items = fakeOrderItems.toMutableList()
        ),
        onEvent = {},
        goBackToOrders = {},
        orderId = ""
    )
}