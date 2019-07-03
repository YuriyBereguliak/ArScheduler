package com.bereguliak.arscheduler.core.presenter

import android.support.annotation.CallSuper
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * This presenter encapsulates the logic of CoroutineScope implementation and Job handling.
 */
abstract class BaseCoroutinePresenter : CoroutineScope {

    private var job: Job = SupervisorJob()

    //region CoroutineScope
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //endregion

    //region BaseCoroutinePresenter
    @CallSuper
    fun unSubscribe() {
        coroutineContext.cancelChildren()
    }
    //endregion
}