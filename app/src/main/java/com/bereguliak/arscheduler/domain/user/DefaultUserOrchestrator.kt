package com.bereguliak.arscheduler.domain.user

import com.bereguliak.arscheduler.data.local.user.UserLocalRepository
import javax.inject.Inject

class DefaultUserOrchestrator @Inject constructor(private val userLocalRepository: UserLocalRepository) : UserOrchestrator {

    //region UserOrchestrator
    override fun saveUserName(userName: String) {
        userLocalRepository.saveUserName(userName)
    }

    override fun loadUserName(): String? {
        return userLocalRepository.loadUserName()
    }

    override fun logout() {
        userLocalRepository.clearUserInfo()
    }

    override fun isUserSignedIn(): Boolean {
        return !loadUserName().isNullOrEmpty()
    }
    //endregion
}