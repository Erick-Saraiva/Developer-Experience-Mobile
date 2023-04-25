package com.example.dx_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botao = findViewById<Button>(R.id.btn_cadastro)
        val btnLogin = findViewById<Button>(R.id.btn_Login)

        botao.setOnClickListener{
            cadastroUsuario()
        }

        btnLogin.setOnClickListener{
            showLoading()
        }
    }

    fun showLoading() {
        val loadingScreen = Intent(this,LoadingDialog::class.java)
        startActivity(loadingScreen)
    }
     fun cadastroUsuario() {
        val cadastroUsuario = Intent(this,CadastroUsuario::class.java)
        startActivity(cadastroUsuario)
    }
}