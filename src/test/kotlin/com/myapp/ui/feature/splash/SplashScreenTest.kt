package com.myapp.ui.feature.splash

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import cafe.adriel.voyager.navigator.Navigator
import com.myapp.di.AppComponent
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Rule
import org.junit.Test


class SplashScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    private val fakeSplashViewModel = SplashViewModel(mock())

    private val mockAppComponent = mock<AppComponent>().apply {
        doAnswer { invocation ->
            val splashScreen = invocation.arguments[0] as SplashScreen
            splashScreen.splashViewModel = fakeSplashViewModel
            null
        }.whenever(this).inject(any<SplashScreen>())
    }

    @Test
    fun `Logo displayed`() {
        composeRule.setContent {
            Navigator(SplashScreen(mockAppComponent, {}))
        }

        composeRule.onNodeWithContentDescription("Logo").assertExists()
    }
}
