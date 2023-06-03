package com.example.dx_kotlin.Utilities

import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Model.Vaga
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiVaga {


    @GET("vagas/")
    fun getVagas(): Call<List<Vaga>>


    @POST("vagas/")
    fun postVaga(@Body vaga: Vaga) : Call<Vaga>

}