package com.findpet.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.RequestUser
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    val liveData = MutableLiveData<Pair<String?, Boolean>>()

    fun onLogin(user: RequestUser) {
        viewModelScope.launch {
            userRepository.onLogin(user).let {
                liveData.postValue(Pair(it.body(), it.isSuccessful))
            }
        }
    }
}