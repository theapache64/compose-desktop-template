package com.myapp.ui.feature

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import cafe.adriel.voyager.navigator.Navigator
import com.myapp.App
import com.myapp.di.AppComponent
import com.myapp.di.DaggerAppComponent
import com.myapp.ui.feature.main.MainScreen
import com.myapp.ui.feature.splash.SplashScreen
import com.myapp.ui.value.MyAppTheme
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import androidx.compose.ui.window.application as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {
    companion object {
        fun getStartIntent(): Intent {
            return Intent(MainActivity::class).apply {
                // data goes here
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        val appComponent: AppComponent = DaggerAppComponent.create()

        setContent {
            Window(
                onCloseRequest = ::exitApplication,
                title = "${App.appArgs.appName} (${App.appArgs.version})",
                icon = painterResource("drawables/launcher_icons/system.png"),
                state = rememberWindowState(width = 1024.dp, height = 600.dp),
            ) {
                MyAppTheme {
                    Navigator(
                        screen = SplashScreen(
                            appComponent = appComponent,
                            onSplashFinished = { navigator ->
                                navigator.push(MainScreen(appComponent))
                            }
                        )
                    )
                }
            }

        }

    }
}