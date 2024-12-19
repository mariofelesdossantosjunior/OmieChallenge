package com.mario.omiechallenge.presentation.screen.order.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.data.datasource.fakeOrders
import com.mario.omiechallenge.domain.model.Order
import com.mario.omiechallenge.util.formatedDate
import com.mario.omiechallenge.util.formatedToReal

@Composable
fun OmieOrderCardItem(order: Order) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Id: #${order.id.substring(0, 5)}",
                style = MaterialTheme.typography.titleSmall
            )
            Text(
                text = "Cliente: ${order.clientName}",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Row {
                Text(
                    text = order.total.formatedToReal(),
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    text = order.date.formatedDate(),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Preview
@Composable
private fun OrderCardItemPreview() {
    OmieOrderCardItem(
        order = fakeOrders.first()
    )
}