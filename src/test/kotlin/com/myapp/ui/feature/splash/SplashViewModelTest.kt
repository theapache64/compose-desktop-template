package com.myapp.ui.feature.splash

import com.github.theapache64.expekt.should
import com.myapp.test.MainCoroutineRule
import com.myapp.test.MyDaggerMockRule
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@ExperimentalCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SplashViewModelTest {

    @get:Rule
    val daggerMockRule = MyDaggerMockRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var splashViewModel: SplashViewModel

    @BeforeAll
    @Test
    fun beforeAll() {
        splashViewModel = SplashViewModel(mock())
    }

    @Test
    fun `Splash finished after delay`() {
        splashViewModel.init(coroutineRule)
        splashViewModel.isSplashFinished.value.should.`false` // Flag is false before delay
        coroutineRule.advanceTimeBy(SplashViewModel.SPLASH_DELAY)
        splashViewModel.isSplashFinished.value.should.`true` // Flag is true after delay
    }
}