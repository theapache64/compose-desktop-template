package com.myapp.test

import com.myapp.data.di.module.MyModule
import com.myapp.data.repo.MyRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MyModule::class
        // Add your modules here
    ]
)
interface TestComponent {
    fun myRepo(): MyRepo
}