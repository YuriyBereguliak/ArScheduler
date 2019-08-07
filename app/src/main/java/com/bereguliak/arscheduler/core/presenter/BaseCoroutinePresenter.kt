package com.bereguliak.arscheduler.core.presenter

import android.support.annotation.CallSuper
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * This presenter encapsulates the logic of CoroutineScope implementation and Job handling.
 */
abstract class BaseCoroutinePresenter : CoroutineScope {

    private var job: Job = SupervisorJob()

    protected val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("Error", exception.message)
    }

    //region CoroutineScope
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //endregion

    //region BaseCoroutinePresenter
    protected suspend fun <T> withDispatcherIO(block: suspend CoroutineScope.() -> T): T {
        return onDispatcher(Dispatchers.IO, block)
    }

    @CallSuper
    fun unSubscribe() {
        coroutineContext.cancelChildren()
    }
    //endregion

    //region Utility API
    private suspend fun <T> onDispatcher(dispatcher: CoroutineDispatcher, block: suspend CoroutineScope.() -> T): T {
        return withContext(dispatcher) {
            block()
        }
    }
    //endregion
}