package com.myapp.ui.feature.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.myapp.di.AppComponent
import com.myapp.ui.value.R
import javax.inject.Inject

class MainScreen(
    appComponent: AppComponent,
) : Screen {
    @Inject
    lateinit var viewModel: MainViewModel

    init {
        appComponent.inject(this)
    }

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        LaunchedEffect(viewModel) {
            viewModel.init(scope)
        }

        val welcomeText by viewModel.welcomeText.collectAsState()

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = welcomeText,
                    style = MaterialTheme.typography.h3
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Button(
                    onClick = {
                        viewModel.onClickMeClicked()
                    }
                ) {
                    Text(text = R.string.ACTION_MAIN_CLICK_ME)
                }
            }
        }
    }
}