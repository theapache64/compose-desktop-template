package com.myapp.data.repo

import javax.inject.Inject

class MyRepo @Inject constructor() {
    fun getClickedWelcomeText() = "Hello Desktop!"
}