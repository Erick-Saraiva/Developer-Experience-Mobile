package com.example.dx_kotlin.Utilities
import com.example.dx_kotlin.Model.Empresa
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiEmpresa {
    @POST("empresas/")
    fun post(@Body empresa: Empresa) : Call<Empresa>
}