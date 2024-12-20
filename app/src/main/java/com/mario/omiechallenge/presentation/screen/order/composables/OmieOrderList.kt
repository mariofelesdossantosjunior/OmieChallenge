package com.mario.omiechallenge.presentation.screen.order.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mario.omiechallenge.data.datasource.fakeOrders
import com.mario.omiechallenge.domain.model.Order

@Composable
fun OmieOrderList(
    orders: List<Order>,
    onSelect: (Order) -> Unit = {}
) {
    LazyColumn {
        items(orders) { order ->
            OmieOrderCardItem(
                order = order,
                onSelect = onSelect
            )
        }
    }
}

@Preview
@Composable
private fun OmieOrderListPreview() {
    OmieOrderList(
        orders = fakeOrders
    )
}