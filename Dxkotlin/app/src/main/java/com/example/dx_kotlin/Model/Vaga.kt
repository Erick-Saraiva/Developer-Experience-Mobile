package com.example.dx_kotlin.Model

data class Vaga(
    val id: Int,
    val descricao: String,
    val valor: Double,
    val senioridade:String,
    val titulo:String,
    val tecnologia:String,
    val tempEstimado:Int,
    val urlImagem:String
) : java.io.Serializable


