package com.mario.omiechallenge.presentation.screen.main

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.mario.omiechallenge.presentation.navigation.SetupNavGraph

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        SetupNavGraph(navController = navController)
    }
}