package com.dheril.intern_test.data.retrofit

import com.dheril.intern_test.data.response.UsersResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/api/users")
    suspend fun getUsers(
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): UsersResponse
}