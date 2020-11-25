package com.findpet.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.RequestUser
import data.Status
import data.ViewData
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val liveData = MutableLiveData<ViewData<String?>>()

    fun onLogin(user: RequestUser) {
        viewModelScope.launch {
            try {
                liveData.postValue(ViewData(Status.LOADING))
                userRepository.onLogin(user).let {
                    if (it.isSuccessful) {
                        liveData.postValue(ViewData(Status.SUCCESS, it.body()))
                        saveUser(user)
                    } else {
                        liveData.postValue(ViewData(Status.ERROR))
                    }

                }
            } catch (e: Exception) {
                liveData.postValue(ViewData(Status.ERROR))
            }
        }
    }

    fun saveUser(user: RequestUser) {
        userRepository.saveUser(user)
    }

    fun getUser() = userRepository.getUser()

    fun getUserFistName(): String? = userRepository.getUser()?.name?.substringBefore(" ")
}