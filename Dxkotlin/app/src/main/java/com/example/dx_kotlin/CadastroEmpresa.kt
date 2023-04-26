package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.Utilities.Apis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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