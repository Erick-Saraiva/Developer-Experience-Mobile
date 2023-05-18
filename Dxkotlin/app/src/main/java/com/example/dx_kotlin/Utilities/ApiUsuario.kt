package com.example.dx_kotlin.Utilities

import com.example.dx_kotlin.Model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiUsuario {


    @POST("usuarios/login/{usuario}/{senha}")
    fun postLogin(@Path("usuario")usuario:String, @Path("senha")senha:String) : Call<Usuario>

    @GET("usuarios/sessao/{usuario}")
    fun getUsuarioSession(@Path("usuario") usuario: String?) : Call<Usuario>

    @POST("usuarios/")
    fun post(@Body usuario: Usuario) : Call<Usuario>


}