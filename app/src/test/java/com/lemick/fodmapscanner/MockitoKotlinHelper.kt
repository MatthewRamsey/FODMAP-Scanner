package com.lemick.fodmapscanner

import org.mockito.Mockito

/**
 * Provides ability to use Mockito.any() method without breaking Kotlin null-safety
 */
class MockitoKotlinHelper {

    companion object {
        fun <T> any(): T {
            Mockito.any<T>()
            return uninitialized()
        }

        private fun <T> uninitialized(): T = null as T
    }

    inline fun <reified T> mock(): T = Mockito.mock(T::class.java)
}