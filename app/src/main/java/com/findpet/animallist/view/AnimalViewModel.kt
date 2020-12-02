package com.findpet.animallist.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findpet.login.UserRepository
import data.RequestAnimal
import data.ResponseAnimal
import data.Status
import data.ViewData
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val animalRepository: AnimalRepository,
    private val userRepository: UserRepository
) : ViewModel() {

    val createAnimalLiveData = MutableLiveData<ViewData<Any?>>()

    val getAnimalsLiveData = MutableLiveData<ViewData<List<ResponseAnimal?>?>>()

    val updateAnimalLiveData = MutableLiveData<ViewData<Unit>>()

    val getAnimalLiveData = MutableLiveData<ViewData<ResponseAnimal>>()

    fun createAnimal(userId: Int, requestAnimal: RequestAnimal) {
        viewModelScope.launch {
            try {
                animalRepository.createAnimal(userId, requestAnimal).let {
                    if (it.isSuccessful) {
                        createAnimalLiveData.postValue(ViewData(Status.SUCCESS))
                    } else {
                        createAnimalLiveData.postValue(ViewData(status = Status.ERROR))
                    }
                }
            } catch (e: Exception) {
                createAnimalLiveData.postValue(ViewData(status = Status.ERROR))
            }
        }
    }

    fun getAnimals() {
        viewModelScope.launch {
            try {
                getAnimalsLiveData.postValue(ViewData(Status.LOADING))
                val response = animalRepository.getAnimals()
                if (response.isSuccessful) {
                    animalRepository.getAnimals().body()?.let {
                        if (it.isNotEmpty()) {
                            getAnimalsLiveData.postValue(ViewData(Status.SUCCESS, it))
                        } else {
                            getAnimalsLiveData.postValue(ViewData(Status.EMPTY))
                        }
                    }
                } else {
                    getAnimalsLiveData.postValue(ViewData(Status.ERROR))
                }
            } catch (e: Exception) {
                getAnimalsLiveData.postValue(ViewData(Status.ERROR))
            }
        }
    }

    fun updateAnimal(animal: ResponseAnimal?) {
        animal?.let {
            viewModelScope.launch {
                try {
                    updateAnimalLiveData.postValue(ViewData(Status.LOADING))
                    val response = animalRepository.updateAnimal(animal)
                    if (response.isSuccessful) {
                        updateAnimalLiveData.postValue(ViewData(Status.SUCCESS))
                    } else {
                        updateAnimalLiveData.postValue(ViewData(Status.ERROR))
                    }
                } catch (e: Exception) {
                    updateAnimalLiveData.postValue(ViewData(Status.ERROR))
                }
            }
        }
    }

    fun getAnimal(animalId: String) {
        viewModelScope.launch {
            try {
                getAnimalLiveData.postValue(ViewData(Status.LOADING))
                val response = animalRepository.getAnimal(animalId)
                if (response.isSuccessful){
                    response.body()?.firstOrNull()?.let {
                        getAnimalLiveData.postValue(ViewData(Status.SUCCESS, it))
                    }
                } else {
                    getAnimalLiveData.postValue(ViewData(Status.ERROR))
                }
            } catch (e: Exception) {
                getAnimalLiveData.postValue(ViewData(Status.ERROR))
            }
        }
    }

}