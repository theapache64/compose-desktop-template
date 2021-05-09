package com.myapp.ui.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.arkivanov.decompose.ComponentContext
import com.myapp.di.AppComponent
import com.myapp.ui.navigation.Component
import com.toxicbakery.logging.Arbor
import javax.inject.Inject

class MainScreenComponent(
    appComponent: AppComponent,
    private val componentContext: ComponentContext,
) : Component, ComponentContext by componentContext {
    @Inject
    lateinit var viewModel: MainViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun render() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            Arbor.d("Syncing data...")
            viewModel.init(scope)
        }

        MainScreen(viewModel)
    }
}