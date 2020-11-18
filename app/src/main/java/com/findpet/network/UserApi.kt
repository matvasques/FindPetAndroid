package com.findpet.network

import data.DeleteUserRequest
import data.Error
import data.RequestUser
import retrofit2.Response
import retrofit2.http.*

interface UserApi {
    /* User */

    @POST("/createUser")
    suspend fun createUser(@Body user: RequestUser): Response<String?> //Todo:update response obj

    @PUT("/update/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: RequestUser
    ): Response<Error?> //Todo:update response obj

    @DELETE("/remove")
    suspend fun deleteUser(@Body deleteUserRequest: DeleteUserRequest): Response<String?> //Todo:update response obj
}