package com.myapp.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@ExperimentalCoroutinesApi
class MainCoroutineRule(
    private val testDispatcher: TestDispatcher = StandardTestDispatcher(),
) : TestWatcher() {

    private val testScope = TestScope(testDispatcher)
    val scope: CoroutineScope = testScope

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }

    fun advanceTimeBy(delayTimeMillis: Long) {
        testScope.testScheduler.advanceTimeBy(delayTimeMillis.milliseconds)
        testScope.testScheduler.runCurrent()
    }

    fun runCurrent() {
        testScope.testScheduler.runCurrent()
    }
}
