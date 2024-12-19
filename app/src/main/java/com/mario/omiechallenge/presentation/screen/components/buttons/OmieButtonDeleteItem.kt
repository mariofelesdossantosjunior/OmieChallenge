package com.mario.omiechallenge.presentation.screen.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(10.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Icon(
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