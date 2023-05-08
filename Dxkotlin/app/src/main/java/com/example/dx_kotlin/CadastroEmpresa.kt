package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class CadastroEmpresa : AppCompatActivity() {
    lateinit var tvResultado: TextView


    lateinit var btnBack: Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_empresa)
        tvResultado = findViewById(R.id.tv_resultado)
        btnBack = findViewById(R.id.btn_back)

        btnBack.setOnClickListener(View.OnClickListener {
            finish() // chama o método finish() para fechar a Activity
        })
    }



}