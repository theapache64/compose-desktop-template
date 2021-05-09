package com.myapp.ui.feature.splash

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


class SplashScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun logoDisplayed() {

        runBlocking(Dispatchers.Main) {
            composeRule.setContent {
                SplashScreen(mock())
            }

            composeRule.onNodeWithContentDescription("Logo").assertExists()
        }
    }
}