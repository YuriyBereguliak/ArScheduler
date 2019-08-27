package com.bereguliak.arscheduler.core.ui

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.bereguliak.arscheduler.R

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getContentViewId(): Int

    abstract fun initView()

    //region AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId())

        initView()
    }
    //endregion

    //region BaseActivity
    fun replaceFragment(@IdRes containerViewId: Int, fragment: Fragment, addToBackStack: Boolean) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right)

        fragmentTransaction.replace(containerViewId, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.javaClass.name)
        }

        fragmentTransaction.commitAllowingStateLoss()
    }
    //endregion
}