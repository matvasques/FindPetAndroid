package com.findpet.login

import com.findpet.UserRegister.datasource.UserLocalDataSource
import com.findpet.network.UserApi
import data.RequestUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val userApi: UserApi,
    private val userLocalDataSource: UserLocalDataSource
) {

    suspend fun onLogin(user: RequestUser) = withContext(Dispatchers.IO) {
        userApi.createUser(user)

    }

    fun saveUser(user: RequestUser) {
        try {
            userLocalDataSource.saveUser(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getUser() = userLocalDataSource.getUser()

}