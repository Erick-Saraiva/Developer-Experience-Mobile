package com.example.dx_kotlin

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface Api {


    @POST("usuarios/login/{usuario}/{senha}")
    fun postLogin(@Path("usuario")usuario:String, @Path("senha")senha:String) : Call<Boolean>

    @POST("usuarios/")
    fun post(@Body usuario: Usuario) : Call<Usuario>

}