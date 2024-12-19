package com.mario.omiechallenge.presentation.screen.core.composables.topbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.mario.omiechallenge.presentation.screen.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OmieTopBar(
    text: String,
    icon: ImageVector? = null,
    onClick: () -> Unit = {},
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Colors.BlueDark,
            titleContentColor = Color.White,
        ),
        title = {
            Text(
                text = text,
                color = Color.White
            )
        },
        navigationIcon = {
            icon?.let {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = it,
                        tint = Color.White,
                        contentDescription = "Icon"
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun OmieTopBarPreview() {
    OmieTopBar(
        text = "Title"
    )
}