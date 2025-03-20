package org.akshev.health_score

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "HealthScore",
    ) {
        App()
    }
}