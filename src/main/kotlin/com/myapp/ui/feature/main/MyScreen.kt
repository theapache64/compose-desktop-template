package com.myapp.ui.feature.main

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*


@Composable
fun MyScreen() {
    var welcomeText by remember { mutableStateOf("Hello Compose!") }

    Column {
        Text(text = welcomeText)

        Button(
            onClick = {
                welcomeText = "Hello Desktop!"
            }
        ) {
            Text(text = "Click Me")
        }
    }
}