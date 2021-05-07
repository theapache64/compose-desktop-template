package com.myapp.ui.feature.splash

import com.myapp.util.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    // Inject your repos here
) : ViewModel() {

    private val _isSplashFinished = MutableStateFlow(false)
    val isSplashFinished: StateFlow<Boolean> = _isSplashFinished

    override fun init(viewModelScope: CoroutineScope) {
        super.init(viewModelScope)

        viewModelScope.launch {
            delay(2000) // 2 seconds of splash delay
            _isSplashFinished.value = true
        }
    }
}