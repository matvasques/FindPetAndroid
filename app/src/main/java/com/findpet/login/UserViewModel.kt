package com.findpet.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.RequestUser
import data.ResponseUser
import data.Status
import data.ViewData
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val liveData = MutableLiveData<ViewData<String?>>()

    val getUserLiveData = MutableLiveData<ViewData<ResponseUser>>()

    fun onLogin(user: RequestUser) {
        viewModelScope.launch {
            try {
                liveData.postValue(ViewData(Status.LOADING))
                userRepository.onLogin(user).let {
                    if (it.isSuccessful) {
                        liveData.postValue(ViewData(Status.SUCCESS))
                        it.body()?.firstOrNull()?.let { responseUser ->
                            saveUser(responseUser)
                        }
                    } else {
                        liveData.postValue(ViewData(Status.ERROR))
                    }
                }
            } catch (e: Exception) {
                liveData.postValue(ViewData(Status.ERROR))
            }
        }
    }

    fun saveUser(user: ResponseUser) {
        userRepository.saveUser(user)
    }

    fun getUser() = userRepository.getUser()

    fun getUserFistName(): String? = userRepository.getUser()?.name?.substringBefore(" ")

    fun clearUserSession() = userRepository.clearUserSession()

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            try {
                getUserLiveData.postValue(ViewData(Status.LOADING))
                userRepository.getUserById(userId)?.let {
                    it.firstOrNull()?.let { user ->
                        getUserLiveData.postValue(ViewData(Status.SUCCESS, user))
                    } ?: run { getUserLiveData.postValue(ViewData(Status.ERROR)) }
                } ?: run { getUserLiveData.postValue(ViewData(Status.ERROR)) }
            } catch (e: Exception) {
                getUserLiveData.postValue(ViewData(Status.ERROR))
            }
        }
    }
}