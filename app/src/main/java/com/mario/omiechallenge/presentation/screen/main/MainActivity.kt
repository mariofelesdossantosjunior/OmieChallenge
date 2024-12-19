package com.mario.omiechallenge.presentation.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mario.omiechallenge.presentation.screen.theme.OmieTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OmieTheme {
                App()
            }
        }
    }
}