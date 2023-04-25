package com.example.dx_kotlin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiEmpresa {
    @POST("empresas/")
    fun post(@Body empresa: Empresa) : Call<Empresa>
}