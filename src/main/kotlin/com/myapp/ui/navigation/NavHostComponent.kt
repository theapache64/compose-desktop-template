package com.myapp.ui.navigation

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.plus
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.scale
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.essenty.parcelable.Parcelable
import com.myapp.di.AppComponent
import com.myapp.di.DaggerAppComponent
import com.myapp.ui.feature.main.MainScreenComponent
import com.myapp.ui.feature.splash.SplashScreenComponent

/**
 * All navigation decisions are made from here
 */
class NavHostComponent(
    private val componentContext: ComponentContext,
) : ComponentContext by componentContext {

    /**
     * Available screensSelectApp
     */
    private sealed class Config : Parcelable {
        object Splash : Config()
        object Main : Config()
    }

    private val appComponent: AppComponent = DaggerAppComponent
        .create()

    /**
     * Navigation configuration
     */
    private val navigation = StackNavigation<Config>()

    /**
     * Stack configuration
     */
    private val stack = childStack(
        source = navigation,
        initialConfiguration = Config.Splash,
        childFactory = ::createScreenComponent
    )

    /**
     * When a new navigation request made, the screen will be created by this method.
     */
    private fun createScreenComponent(config: Config, componentContext: ComponentContext): Component {
        return when (config) {
            is Config.Splash -> SplashScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext,
                onSplashFinished = ::onSplashFinished,
            )
            is Config.Main -> MainScreenComponent(
                appComponent = appComponent,
                componentContext = componentContext
            )
        }
    }

    @Composable
    operator fun invoke() {
        Children(
            stack = stack,
            animation = stackAnimation(fade() + scale())
        ) { child ->
            child.instance.render()
        }
    }

    /**
     * Invoked when splash finish data sync
     */
    private fun onSplashFinished() {
        navigation.replaceCurrent(Config.Main)
    }
}