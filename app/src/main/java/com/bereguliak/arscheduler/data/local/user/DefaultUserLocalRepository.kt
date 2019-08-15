package com.bereguliak.arscheduler.data.local.user

import android.annotation.SuppressLint
import android.content.Context
import com.bereguliak.arscheduler.di.AppContext
import javax.inject.Inject

class DefaultUserLocalRepository @Inject constructor(@AppContext context: Context) : UserLocalRepository {

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    //region UserLocalRepository
    override fun loadUserName(): String? = preferences.getString(KEY_USER_NAME, null)

    @SuppressLint("ApplySharedPref")
    override fun saveUserName(userName: String) {
        preferences.edit().putString(KEY_USER_NAME, userName).commit()
    }

    override fun clearUserInfo() {
        preferences.edit().remove(KEY_USER_NAME).apply()
    }
    //endregion

    //region Utility structure
    companion object {
        private const val PREF_NAME = "arscheduler"

        private const val KEY_USER_NAME = "user_name"
    }
    //endregion
}