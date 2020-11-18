package com.findpet.network

import data.Error
import data.RequestAnimal
import data.ResponseAnimal
import retrofit2.Response
import retrofit2.http.*

interface AnimalApi {

    /* Animals */
    @GET("/showAnimals")
    suspend fun getAnimals(): Response<List<ResponseAnimal?>?>

    @POST("{userId}/createAnimal")
    suspend fun createAnimal(
        @Path("userId") userId: String,
        @Body requestAnimal: RequestAnimal
    ): Response<String?> //Todo:update response obj

    @PUT("/updateAnimal/{id}")
    suspend fun updateAnimal(
        @Path("id") id: String,
        @Body animal: RequestAnimal
    ): Response<Error?> //Todo:update response obj

    @DELETE("/deleteAnimal")
    suspend fun deleteAnimal(): Response<Any>
}