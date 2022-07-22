package com.myapp.ui.feature

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.lifecycle.LifecycleController
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.myapp.App
import com.myapp.ui.navigation.NavHostComponent
import com.myapp.ui.value.MyAppTheme
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import androidx.compose.ui.window.application as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {
    private val lifecycle = LifecycleRegistry()

    private val navHostComponent = NavHostComponent(DefaultComponentContext(lifecycle))

    companion object {
        fun getStartIntent(): Intent {
            return Intent(MainActivity::class).apply {
                // data goes here
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        setContent {
            val windowState = rememberWindowState(width = 1024.dp, height = 600.dp)
            LifecycleController(lifecycle, windowState)

            Window(
                onCloseRequest = ::exitApplication,
                title = "${App.appArgs.appName} (${App.appArgs.version})",
                icon = painterResource("drawables/launcher_icons/system.png"),
                state = windowState,
            ) {
                MyAppTheme {
                    navHostComponent()
                }
            }

        }

    }
}