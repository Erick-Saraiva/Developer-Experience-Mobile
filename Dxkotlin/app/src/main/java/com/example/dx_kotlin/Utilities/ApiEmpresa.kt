package com.example.dx_kotlin.Utilities
import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Model.Vaga
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiEmpresa {
    @POST("empresas/")
    fun post(@Body empresa: Empresa) : Call<Empresa>

    @POST("empresas/login/{usuario}/{senha}")
    fun postLogin(@Path("usuario")usuario:String, @Path("senha")senha:String) : Call<Empresa>

    @GET("empresas/sessao/{usuario}")
    fun getEmpresaSession(@Path("usuario") usuario: String?) : Call<Empresa>

    @GET("empresas/")
    fun getEmpresas(): Call<List<Empresa>>


}