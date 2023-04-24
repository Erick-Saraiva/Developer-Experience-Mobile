package com.example.dx_kotlin

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Apis {

    var BASE_URL = "http://192.168.15.129:8080/"


    fun getApiUsuario(): Api{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(Api::class.java)
    }
}