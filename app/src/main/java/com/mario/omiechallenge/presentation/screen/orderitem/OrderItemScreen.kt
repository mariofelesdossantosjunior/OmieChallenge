package com.mario.omiechallenge.presentation.screen.orderitem

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mario.omiechallenge.R
import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.presentation.screen.core.composables.topbar.OmieTopBar
import com.mario.omiechallenge.presentation.screen.orderitem.composables.FormInputValueItem
import com.mario.omiechallenge.presentation.screen.orderitem.composables.OmieBottomBarOrderItems

@Composable
fun OrderItemScreen(
    uiState: OrderItemUIState,
    onEvent: (OrderItemUIEvent) -> Unit,
    goBackToOrders: () -> Unit
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
            FormInputValueItem(
                modifier = Modifier.padding(paddingValues),
                uiState = uiState,
                onEvent = onEvent
            )
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
        goBackToOrders = {}
    )
}