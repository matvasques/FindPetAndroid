package com.findpet.network


import data.*
import retrofit2.Response
import retrofit2.http.*

interface NetworkService {



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

    /* Occurrence */
    @POST("/{userId}/createOccurrence")
    suspend fun createOccurrence(
        @Path("userId") userId: String,
        @Body requestOccurrence: RequestOccurrence
    ): Response<Error?> //Todo:update response obj

    @GET("/showOccurrences")
    suspend fun getOccurrences(): Response<List<ResponseOccurrence?>?>

    @PUT("/updateOccurrence/{id}")
    suspend fun updateOccurrence(@Path("id") id: String): Response<Any>//Falta atualizar

    @DELETE("/removeOccurrences")
    suspend fun deleteOccurrences(): Response<Any> //Falta atualizar


}