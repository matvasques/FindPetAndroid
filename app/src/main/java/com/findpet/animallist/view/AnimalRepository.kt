package com.findpet.animallist.view

import com.findpet.network.AnimalApi
import data.RequestAnimal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalRepository(private val animalApi: AnimalApi) {

    suspend fun createAnimal(userId: Int, requestAnimal: RequestAnimal) =
        withContext(Dispatchers.IO) {
            animalApi.createAnimal(userId.toString(), requestAnimal)
        }
}