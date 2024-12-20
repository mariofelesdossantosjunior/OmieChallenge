package com.mario.omiechallenge.presentation.screen.orderitem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.R
import com.mario.omiechallenge.presentation.screen.core.composables.buttons.OmieButton
import com.mario.omiechallenge.presentation.screen.core.composables.textFields.OmieTextField
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemUIEvent
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemUIState
import com.mario.omiechallenge.presentation.screen.theme.Colors

@Composable
fun FormInputValueItem(
    modifier: Modifier = Modifier,
    uiState: OrderItemUIState,
    onEvent: (OrderItemUIEvent) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {

        OmieTextField(
            label = stringResource(R.string.client),
            error = uiState.clientError,
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
            error = uiState.productError,
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
                error = uiState.quantityError,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = uiState.productQuantity,
                onValueChange = {
                    onEvent(
                        OrderItemUIEvent.OnProductQuantityChanged(
                            quantity = it
                        )
                    )
                }
            )

            OmieTextField(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.unit_value),
                value = uiState.productPrice,
                error = uiState.valueError,
                onValueChange = {
                    onEvent(
                        OrderItemUIEvent.OnProductPriceChanged(
                            price = it
                        )
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
        }

        OmieButton(
            text = stringResource(R.string.add),
            onClick = {
                onEvent(
                    OrderItemUIEvent.OnAddItem
                )
                focusRequester.requestFocus()
            }
        )
    }
}

@Preview
@Composable
private fun FormInputValueItemPreview() {
    FormInputValueItem(
        uiState = OrderItemUIState(),
        onEvent = {}
    )
}