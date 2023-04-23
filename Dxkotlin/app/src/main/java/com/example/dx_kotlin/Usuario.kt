package com.example.dx_kotlin

import java.time.LocalDateTime
import java.util.*

data class Usuario (
    val id:Int,
    val usuario:String,
    val senha:String,
    val dataNascimento: Date,
    val nome:String,
    val email:String,
    val cpf:String,
    val rua:String,
    val numero:Int,
    val cep:String,
    val bairro:String,
    val cidade:String,
    val complemento:String,
    val telefone:String
)

