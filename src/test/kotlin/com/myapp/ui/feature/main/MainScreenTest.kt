package com.myapp.ui.feature.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test


internal class MainScreenTest {
    @get:Rule
    val composeRule = createComposeRule()


    @Test
    fun textAndButtonDisplayed() {
        runBlocking(Dispatchers.Main) {
            composeRule.onNodeWithText(MainViewModel.INIT_WELCOME_MSG).assertExists()
        }
    }
}