package com.myapp.ui.feature.splash

import com.github.theapache64.expekt.should
import com.myapp.test.MainCoroutineRule
import com.myapp.test.MyDaggerMockRule
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SplashViewModelTest {

    @get:Rule
    val daggerMockRule = MyDaggerMockRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private val splashViewModel by lazy {
        SplashViewModel(mock()).apply {
            init(coroutineRule)
        }
    }

    @Test
    fun `Splash finished after delay`() {
        splashViewModel.isSplashFinished.value.should.`false` // Flag should be false before delay
        coroutineRule.advanceTimeBy(SplashViewModel.SPLASH_DELAY)
        splashViewModel.isSplashFinished.value.should.`true` // Flag should be true after delay
    }
}