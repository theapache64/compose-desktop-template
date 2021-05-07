package com.myapp


import com.myapp.model.AppArgs
import com.myapp.ui.feature.MainActivity
import com.theapache64.cyclone.core.Application
import com.toxicbakery.logging.Arbor
import com.toxicbakery.logging.Seedling


class App(
    appArgs: AppArgs
) : Application() {

    companion object {
        // GLOBAL CONFIGS
        const val CUSTOM_TOOLBAR = false // TODO: Implement a custom toolbar
        lateinit var appArgs: AppArgs
    }

    init {
        App.appArgs = appArgs
    }

    override fun onCreate() {
        super.onCreate()
        Arbor.sow(Seedling())

        val splashIntent = MainActivity.getStartIntent()
        startActivity(splashIntent)
    }
}

/**
 * The magic begins here
 */
fun main() {
    // Parsing application arguments

    val appArgs = AppArgs(
        appName = "My App",
        version = "v1.0.0",
        versionCode = 100
    )

    // Passing args
    App(appArgs).onCreate()
}