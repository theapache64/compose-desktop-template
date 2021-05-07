package com.myapp.ui.feature.splash

import androidx.compose.runtime.*
import com.arkivanov.decompose.ComponentContext
import com.myapp.di.AppComponent
import com.myapp.ui.navigation.Component
import com.toxicbakery.logging.Arbor
import javax.inject.Inject

class SplashScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
    private val onSyncFinished: () -> Unit,
) : Component, ComponentContext by componentContext {

    @Inject
    lateinit var splashViewModel: SplashViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {

        val scope = rememberCoroutineScope()
        LaunchedEffect(splashViewModel) {
            Arbor.d("Syncing data...")
            splashViewModel.init(scope)
        }

        val isSyncFinished by splashViewModel.isSplashFinished.collectAsState()

        if (isSyncFinished) {
            onSyncFinished()
        }

        SplashScreen(
            viewModel = splashViewModel
        )
    }
}