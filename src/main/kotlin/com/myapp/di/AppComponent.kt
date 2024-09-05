package com.myapp.di

import com.myapp.ui.feature.main.MainScreen
import com.myapp.ui.feature.splash.SplashScreen
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        // Add your modules here
    ]
)
interface AppComponent {
    fun inject(splashScreen: SplashScreen)
    fun inject(mainScreen: MainScreen)
}