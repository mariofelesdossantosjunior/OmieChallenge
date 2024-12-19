package com.mario.omiechallenge.presentation.screen.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.mario.omiechallenge.presentation.screen.theme.Colors.BlueBase
import com.mario.omiechallenge.presentation.screen.theme.Colors.BlueLight
import com.mario.omiechallenge.presentation.screen.theme.Colors.Gray100
import com.mario.omiechallenge.presentation.screen.theme.Colors.Gray200
import com.mario.omiechallenge.presentation.screen.theme.Colors.Gray600
import com.mario.omiechallenge.presentation.screen.theme.Colors.RedBase
import com.mario.omiechallenge.presentation.screen.theme.Colors.RedLight

private val LightColors = lightColorScheme(
    primary = BlueBase,
    onPrimary = Color.White,
    secondary = RedBase,
    onSecondary = Color.White,
    background = Gray100,
    onBackground = Gray600,
    surface = Gray100,
    onSurface = Gray600,
    error = RedBase,
    onError = Color.White
)

private val DarkColors = darkColorScheme(
    primary = BlueLight,
    onPrimary = Color.Black,
    secondary = RedLight,
    onSecondary = Color.Black,
    background = Gray600,
    onBackground = Gray100,
    surface = Gray600,
    onSurface = Gray200,
    error = RedBase,
    onError = Color.Black
)

@Composable
fun OmieTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}