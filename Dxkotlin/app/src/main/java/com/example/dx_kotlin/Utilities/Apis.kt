package com.example.dx_kotlin.Utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apis {

    var BASE_URL = "http://192.168.15.129:8080/"
    fun getApiUsuario(): ApiUsuario {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiUsuario::class.java)
    }

    fun getApiEmpresa(): ApiEmpresa {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiEmpresa::class.java)
    }
}