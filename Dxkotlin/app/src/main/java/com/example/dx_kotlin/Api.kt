package com.example.dx_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @GET("usuarios")
    fun getTodos() : Call<List<Usuario>>

    @POST("usuarios/login/{usuario}/{senha}")
    fun postLogin(@Query("usuario")usuario:String, @Query("senha")senha:String) : Call<Boolean>
}