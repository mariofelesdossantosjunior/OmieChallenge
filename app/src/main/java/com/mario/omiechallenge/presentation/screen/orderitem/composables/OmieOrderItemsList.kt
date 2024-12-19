package com.mario.omiechallenge.presentation.screen.orderitem.composables

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.domain.model.OrderItem

@Composable
fun OmieOrderItemsList(
    items: List<OrderItem>,
    onDeleteItem: (OrderItem) -> Unit = {}
) {
    LazyColumn {
        items(items) { item ->
            OmieOrderItemCard(
                item = item,
                onDeleteItem = {
                    onDeleteItem(item)
                }
            )
        }
    }
}

@Preview
@Composable
private fun OmieOrderItemsListPreview() {
    OmieOrderItemsList(
        items = fakeOrderItems
    )
}