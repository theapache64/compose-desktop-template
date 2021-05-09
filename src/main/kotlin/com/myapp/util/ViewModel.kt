package com.myapp.util

import kotlinx.coroutines.CoroutineScope

abstract class ViewModel {

    protected lateinit var viewModelScope: CoroutineScope

    open fun init(viewModelScope: CoroutineScope) {
        this.viewModelScope = viewModelScope
    }
}