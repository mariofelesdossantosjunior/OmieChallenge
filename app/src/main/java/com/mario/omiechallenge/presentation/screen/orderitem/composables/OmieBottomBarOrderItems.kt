package com.mario.omiechallenge.presentation.screen.orderitem.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.R
import com.mario.omiechallenge.presentation.screen.core.composables.buttons.OmieButton
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemUIEvent
import com.mario.omiechallenge.presentation.screen.orderitem.OrderItemUIState
import com.mario.omiechallenge.presentation.screen.theme.Colors
import com.mario.omiechallenge.util.formatedToReal


@Composable
fun OmieBottomBarOrderItems(
    uiState: OrderItemUIState,
    goBackToOrders: () -> Unit,
    onEvent: (OrderItemUIEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(16.dp)
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
                enabled = uiState.isValid,
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

@Preview
@Composable
private fun OmieBottomBarOrderItemsPreview() {
    OmieBottomBarOrderItems(
        uiState = OrderItemUIState(),
        goBackToOrders = {},
        onEvent = {}
    )
}