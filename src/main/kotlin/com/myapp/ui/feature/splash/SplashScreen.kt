package com.myapp.ui.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.myapp.di.AppComponent
import javax.inject.Inject

class SplashScreen @Inject constructor(
    appComponent: AppComponent,
    private val onSplashFinished: (navigator: Navigator) -> Unit
) : Screen {

    @Inject
    lateinit var splashViewModel: SplashViewModel


    init {
        appComponent.inject(this)
    }

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(splashViewModel) {
            splashViewModel.init(scope)
        }

        val isSplashFinished by splashViewModel.isSplashFinished.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        LaunchedEffect(isSplashFinished, navigator) {
            if (isSplashFinished) {
                onSplashFinished(navigator)
            }
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource("drawables/logo.png"),
                modifier = Modifier.size(100.dp),
                contentDescription = "Logo"
            )
        }
    }

}