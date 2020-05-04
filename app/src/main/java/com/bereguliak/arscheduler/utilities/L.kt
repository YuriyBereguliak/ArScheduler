package com.bereguliak.arscheduler.utilities

import android.util.Log
import com.bereguliak.arscheduler.BuildConfig

object L {

    /**
     * Prints error message.
     *
     * @param msg message value
     */
    fun e(msg: String) {
        if (BuildConfig.DEBUG) {
            val t = Throwable()
            val elements = t.stackTrace

            val callerClassName = elements[1].className
            val callerMethodName = elements[1].methodName

            Log.e(callerClassName, "$callerMethodName :: $msg")
        }
    }

    /**
     * Prints debug message.
     *
     * @param msg message value
     */
    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            val t = Throwable()
            val elements = t.stackTrace

            val callerClassName = elements[1].className
            val callerMethodName = elements[1].methodName

            Log.d(callerClassName, "$callerMethodName :: $msg")
        }
    }
}