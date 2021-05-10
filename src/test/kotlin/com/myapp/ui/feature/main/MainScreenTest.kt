package com.myapp.ui.feature.main

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.myapp.data.repo.MyRepo
import com.myapp.ui.value.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal class MainScreenTest {

    companion object {
        private const val FAKE_WELCOME_MSG = "Hello Desktop!"
    }

    @get:Rule
    val composeRule = createComposeRule()

    private val fakeRepo = mock<MyRepo>().apply {
        whenever(getClickedWelcomeText()).thenReturn(FAKE_WELCOME_MSG)
    }

    @Before
    fun beforeEvery() {
        composeRule.setContent {
            MainScreen(
                MainViewModel(fakeRepo)
            )
        }
    }

    @Test
    fun `Click changes the text`() {
        runBlocking(Dispatchers.Main) {
            composeRule.onNodeWithText(MainViewModel.INIT_WELCOME_MSG).assertExists()
            composeRule.onNodeWithText(R.string.ACTION_MAIN_CLICK_ME).performClick()
            composeRule.awaitIdle()
            composeRule.onNodeWithText(FAKE_WELCOME_MSG).assertExists()
        }
    }

}