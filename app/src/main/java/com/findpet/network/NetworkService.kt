package com.findpet.network


import retrofit2.Response
import retrofit2.http.*

interface NetworkService {

    /* User */

    @POST("/createUser")
    suspend fun createUser(): Response<Any>

    @PUT("/update/{id}")
    suspend fun updateUser(@Path("id") id: String): Response<Any>

    @DELETE("/remove")
    suspend fun deleteUser(): Response<Any>

    /* Animals */
    @GET("/showAnimals")
    suspend fun getAnimals(): Response<Any>

    @POST("{userId}/createAnimal")
    suspend fun createAnimal(@Path("userId") userId: String): Response<Any>

    @PUT("/updateAnimal/{id}")
    suspend fun updateAnimal(@Path("id") id: String): Response<Any>

    @DELETE("/deleteAnimal")
    suspend fun deleteAnimal(): Response<Any>

    /* Occurrence */
    @POST("/{userId}/createAnimal")
    suspend fun createOccurrence(): Response<Any>

    @GET("/showOccurrences")
    suspend fun getOccurrences(): Response<Any>

    @PUT("/updateOccurrence/{id}")
    suspend fun updateOccurrence(@Path("id") id: String): Response<Any>

    @DELETE("/removeOccurrences")
    suspend fun deleteOccurrences(): Response<Any>


}