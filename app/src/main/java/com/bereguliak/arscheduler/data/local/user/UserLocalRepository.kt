package com.bereguliak.arscheduler.data.local.user

interface UserLocalRepository {
    fun saveUserName(userName: String)
    fun loadUserName(): String?
}