package com.mario.omiechallenge.presentation.screen.orderitem.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.data.datasource.fakeOrderItems
import com.mario.omiechallenge.domain.model.OrderItem
import com.mario.omiechallenge.presentation.screen.core.composables.buttons.OmieButtonDeleteItem
import com.mario.omiechallenge.util.formatedToReal

@Composable
fun OmieOrderItemCard(
    item: OrderItem,
    onDeleteItem: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = item.quantity.toString(),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = " X ",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = item.unitPrice.formatedToReal(),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.End,
                        text = "Total: ${item.total.formatedToReal()}",
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            Column(
                modifier = Modifier.weight(1f)
            ) {
                OmieButtonDeleteItem(
                    onClick = onDeleteItem
                )
            }
        }
    }
}

@Preview
@Composable
private fun OmieOrderItemCardPreview() {
    OmieOrderItemCard(
        item = fakeOrderItems.first()
    )
}