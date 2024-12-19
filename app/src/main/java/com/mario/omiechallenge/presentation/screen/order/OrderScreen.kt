package com.mario.omiechallenge.presentation.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.R
import com.mario.omiechallenge.presentation.screen.order.composables.OmieOrderList
import com.mario.omiechallenge.presentation.screen.core.composables.indicator.OmieProgressIndicator
import com.mario.omiechallenge.presentation.screen.core.composables.topbar.OmieTopBar
import com.mario.omiechallenge.presentation.screen.theme.Colors

@Composable
fun OrderScreen(
    uiState: OrderUIState,
    navigateToAddOrder: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    val messageFailure = stringResource(R.string.message_failure)
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
                text = stringResource(R.string.title_sales)
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToAddOrder,
                containerColor = Colors.BlueDark,
                contentColor = Color.White
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "add"
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 25.dp)
        ) {

            if (uiState.isLoading) {
                OmieProgressIndicator()
            }

            OmieOrderList(
                orders = uiState.orders
            )
        }
    }
}

@Preview
@Composable
private fun OrderScreenPreview() {
    OrderScreen(
        uiState = OrderUIState(),
        navigateToAddOrder = {}
    )
}