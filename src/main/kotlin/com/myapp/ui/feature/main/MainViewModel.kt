package com.myapp.ui.feature.main

import com.myapp.data.repo.MyRepo
import com.myapp.util.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    // Inject your repos here
    private val myRepo: MyRepo,
    // Inject your repos here...
) : ViewModel() {
    private val _welcomeText = MutableStateFlow("Hello World!")
    val welcomeText: StateFlow<String> = _welcomeText

    fun onClickMeClicked() {
        _welcomeText.value = "Hello Desktop!"
    }
}