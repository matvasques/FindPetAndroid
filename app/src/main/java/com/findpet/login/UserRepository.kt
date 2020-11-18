package com.findpet.login

import com.findpet.network.UserApi
import data.RequestUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val userApi: UserApi) {

    suspend fun onLogin(user: RequestUser) = withContext(Dispatchers.IO) {
        userApi.createUser(user)
    }
}