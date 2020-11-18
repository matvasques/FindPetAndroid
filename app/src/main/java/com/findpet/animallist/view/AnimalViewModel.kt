package com.findpet.animallist.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.RequestAnimal
import kotlinx.coroutines.launch

class AnimalViewModel(private val animalRepository: AnimalRepository) : ViewModel() {

    val liveData = MutableLiveData<Pair<String?, Boolean>>()

    fun createAnimal(userId: Int, requestAnimal: RequestAnimal) {
        viewModelScope.launch {
            animalRepository.createAnimal(userId, requestAnimal).let {
                liveData.postValue(Pair(it.body(), it.isSuccessful))
            }
        }
    }

}