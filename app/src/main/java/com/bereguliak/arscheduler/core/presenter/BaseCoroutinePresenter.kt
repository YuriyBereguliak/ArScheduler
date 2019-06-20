package com.bereguliak.arscheduler.core.presenter

import android.support.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * This presenter encapsulates the logic of CoroutineScope implementation and Job handling.
 */
abstract class BaseCoroutinePresenter : CoroutineScope {

    private lateinit var job: Job

    //region CoroutineScope
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
    //endregion

    //region BaseCoroutinePresenter
    @CallSuper
    fun onSubscribe() {
        job = Job()
    }

    @CallSuper
    fun unSubscribe() {
        job.cancel()
    }
    //endregion
}