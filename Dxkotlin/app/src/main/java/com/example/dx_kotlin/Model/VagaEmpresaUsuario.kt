package com.example.dx_kotlin.Model

data class VagaEmpresaUsuario(
    val dataVaga: String,
    val vaga: Vagateste,
    val usuario: Usuarioteste,
    val empresa: Empresateste
)

data class Vagateste(
    val id: Int
)

data class Usuarioteste(
    val idUsuario: Int
)

data class Empresateste(
    val idEmpresa: Int
)
