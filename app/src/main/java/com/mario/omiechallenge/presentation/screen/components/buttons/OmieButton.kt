package com.mario.omiechallenge.presentation.screen.components.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.presentation.screen.theme.Colors

@Composable
fun OmieButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    color: Color = Colors.BlueDark,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(50.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text(
            text = text,
            color = Color.White
        )
    }
}