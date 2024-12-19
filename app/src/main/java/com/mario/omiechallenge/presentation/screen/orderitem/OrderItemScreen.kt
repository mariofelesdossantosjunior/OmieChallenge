package com.mario.omiechallenge.presentation.screen.orderitem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.R
import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.presentation.screen.components.buttons.OmieButton
import com.mario.omiechallenge.presentation.screen.components.orderitems.OmieOrderItemsList
import com.mario.omiechallenge.presentation.screen.components.textFields.OmieTextField
import com.mario.omiechallenge.presentation.screen.components.topbar.OmieTopBar
import com.mario.omiechallenge.presentation.screen.theme.Colors
import com.mario.omiechallenge.util.formatedToReal

@Composable
fun OrderItemScreen(
    uiState: OrderItemUIState,
    onEvent: (OrderItemUIEvent) -> Unit,
    goBackToOrders: () -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {

                OmieTextField(
                    label = stringResource(R.string.client),
                    value = uiState.client,
                    onValueChange = {
                        onEvent(OrderItemUIEvent.OnClientChanged(it))
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Colors.Gray300)
                Spacer(modifier = Modifier.height(8.dp))

                OmieTextField(
                    modifier = Modifier.focusRequester(focusRequester),
                    label = stringResource(R.string.product_name),
                    value = uiState.productName,
                    onValueChange = {
                        onEvent(OrderItemUIEvent.OnProductNameChanged(it))
                    }
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OmieTextField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.quantity),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = uiState.productQuantity,
                        onValueChange = {
                            onEvent(
                                OrderItemUIEvent.OnProductQuantityChanged(
                                    productQuantity = it
                                )
                            )
                        }
                    )

                    OmieTextField(
                        modifier = Modifier.weight(1f),
                        label = stringResource(R.string.unit_value),
                        value = uiState.productValue,
                        onValueChange = {
                            onEvent(
                                OrderItemUIEvent.OnProductValueChanged(
                                    productValue = it
                                )
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                    )
                }

                OmieButton(
                    modifier = Modifier.padding(16.dp),
                    text = stringResource(R.string.add),
                    onClick = {
                        onEvent(
                            OrderItemUIEvent.OnAddItem
                        )
                        focusRequester.requestFocus()
                    }
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Colors.Gray300)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Total: ${uiState.total.formatedToReal()}",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center
                )
                HorizontalDivider(color = Colors.Gray300)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OmieButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.cancel),
                        color = Colors.RedDark,
                        onClick = goBackToOrders
                    )

                    OmieButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(R.string.save),
                        color = Colors.GreenDark,
                        onClick = {
                            onEvent(OrderItemUIEvent.OnSaveOrder)
                            goBackToOrders()
                        }
                    )
                }
            }
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