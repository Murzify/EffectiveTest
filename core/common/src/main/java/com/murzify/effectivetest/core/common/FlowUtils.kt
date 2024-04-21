package com.murzify.effectivetest.core.common

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

inline fun Fragment.launchStarted(crossinline block: suspend CoroutineScope.() -> Unit) {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            block()
        }
    }
}

inline fun <T> Fragment.collectStarted(
    flow: StateFlow<T>,
    crossinline block: suspend (T) -> Unit
) {
    launchStarted {
        flow.collect{
            block(it)
        }
    }
}