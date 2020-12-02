package com.findpet.animallist.view

import com.findpet.login.UserRepository
import com.findpet.network.AnimalApi
import data.RequestAnimal
import data.ResponseAnimal
import data.toRequestAnimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalRepository(
    private val animalApi: AnimalApi,
    private val userRepository: UserRepository
) {

    suspend fun createAnimal(userId: Int, requestAnimal: RequestAnimal) =
        withContext(Dispatchers.IO) {
            animalApi.createAnimal(userId.toString(), requestAnimal)
        }

    suspend fun getAnimals() = withContext(Dispatchers.IO) {
        animalApi.getAnimals(userRepository.getUserIdOrNull().toString())
    }

    suspend fun updateAnimal(animal: ResponseAnimal) = withContext(Dispatchers.IO) {
        animalApi.updateAnimal(animal.id.toString(), animal.toRequestAnimal())
    }

    suspend fun getAnimal(animalId: String) = withContext(Dispatchers.IO) {
        animalApi.getAnimal(animalId)
    }


}