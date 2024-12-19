package com.mario.omiechallenge.presentation.screen.core.composables.indicator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mario.omiechallenge.presentation.screen.theme.Colors

@Composable
fun OmieProgressIndicator() {
    CircularProgressIndicator(
        modifier = Modifier.padding(16.dp),
        color = Colors.BlueBase
    )
}