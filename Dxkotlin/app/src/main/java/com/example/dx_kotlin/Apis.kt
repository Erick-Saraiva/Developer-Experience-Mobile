package com.example.dx_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apis {

    var BASE_URL = "http://10.18.7.22:8080/"


    fun getApiUsuario(): Api{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(Api::class.java)
    }

    fun getApiEmpresa(): ApiEmpresa{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(ApiEmpresa::class.java)
    }
}