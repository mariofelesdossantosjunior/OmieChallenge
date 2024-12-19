package com.mario.omiechallenge.presentation.screen.core.composables.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.R
import com.mario.omiechallenge.presentation.screen.theme.Colors

@Composable
fun OmieButtonDeleteItem(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = Colors.RedDark,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        modifier = modifier
            .clip(CircleShape)
            .size(40.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Icon(
            modifier = Modifier.padding(8.dp),
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = "icon delete",
            tint = Color.White
        )
    }
}

@Preview
@Composable
private fun OmieButtonDeleteItemPreview() {
    OmieButtonDeleteItem(
        onClick = {}
    )
}