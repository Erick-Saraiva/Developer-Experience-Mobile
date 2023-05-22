package com.example.dx_kotlin.Model

data class Empresa (
    val id:Int,
    val usuario:String,
    val senha:String,
    val dataNascimento: String,
    val nome:String,
    val email:String,
    val cnpj: String,
    val rua:String,
    val numero:String,
    val cep:String,
    val bairro:String,
    val cidade:String,
    val complemento:String,
    val telefone:String,
    val numFuncionario:String,
    var isEmpresa: Boolean,
)
