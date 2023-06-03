package com.example.dx_kotlin.Model

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

data class Usuario (
    val idUsuario:Int,
    val usuario:String,
    val senha:String,
    val dataNascimento: String,
    val nome:String,
    val email:String,
    val cpf:String,
    val rua:String,
    val numero:String,
    val cep:String,
    val bairro:String,
    val cidade:String,
    val complemento:String,
    val telefone:String
)

