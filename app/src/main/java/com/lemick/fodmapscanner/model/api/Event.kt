package com.lemick.fodmapscanner.model.api

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 */
class Event<T>(val content: T?) {

    private var hasBeenHandled = false

    fun contentIfNotHandled(): T? {
        if (hasBeenHandled) {
            return null
        } else {
            hasBeenHandled = true
            return content
        }
    }

    fun hasBeenHandled(): Boolean {
        return hasBeenHandled
    }
}