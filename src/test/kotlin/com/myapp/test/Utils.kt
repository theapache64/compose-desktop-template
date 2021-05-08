package com.myapp.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import java.io.File
import java.net.URL

fun runBlockingUnitTest(block: suspend CoroutineScope.() -> Unit) = runBlocking {
    block()
}

fun getTestResource(name: String): File {
    val url: URL = Thread.currentThread().contextClassLoader.getResource(name)!!
    return File(url.path)
}