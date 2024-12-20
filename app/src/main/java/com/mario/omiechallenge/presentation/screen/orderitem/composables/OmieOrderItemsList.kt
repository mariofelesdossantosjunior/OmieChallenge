package com.mario.omiechallenge.presentation.screen.orderitem.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.domain.model.OrderItem

@Composable
fun OmieOrderItemsList(
    items: List<OrderItem>,
    onDeleteItem: (OrderItem) -> Unit = {}
) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
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