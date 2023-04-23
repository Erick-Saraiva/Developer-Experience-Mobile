package com.example.dx_kotlin

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("usuarios")
    fun getTodos() : Call<List<Usuario>>

    @POST("usuarios/login/{usuario}/{senha}")
    fun postLogin(@Path("usuario")usuario:String, @Path("senha")senha:String) : Call<Boolean>

}