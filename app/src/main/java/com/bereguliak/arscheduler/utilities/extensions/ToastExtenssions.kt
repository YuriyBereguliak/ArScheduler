package com.bereguliak.arscheduler.utilities.extensions

import android.content.Context
import android.content.ContextWrapper
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast

fun ContextWrapper.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    toast(this.applicationContext, message, duration)
}

fun ContextWrapper.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(this.applicationContext, messageResId, duration)
}

fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    this.context?.let { toast(it, message, duration) }
}

fun Fragment.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    this.context?.let { toast(it, messageResId, duration) }
}

fun View.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    toast(this.context, message, duration)
}

fun View.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    toast(this.context, messageResId, duration)
}

fun toast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, message, duration).show()
}

fun toast(context: Context, @StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, messageResId, duration).show()
}
