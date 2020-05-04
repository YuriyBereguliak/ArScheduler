package com.bereguliak.arscheduler.domain.user

interface UserOrchestrator {
    fun saveUserName(userName: String)

    fun loadUserName(): String?

    fun logout()

    fun isUserSignedIn(): Boolean
}