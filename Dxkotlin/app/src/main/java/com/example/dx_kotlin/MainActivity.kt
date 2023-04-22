package com.example.dx_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvAutenticacao: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun validarAutenticacao() {
        val usuario = findViewById<EditText>(R.id.et_email).text.toString()
        val senha = findViewById<EditText>(R.id.et_senha).text.toString()

        /*if (login == "lololo" && senha == "sesese") {
            tvAutenticacao.text = "Bem vindo, usuário Loko!"
        } else {
            tvAutenticacao.text = "Login e/ou senha inválidos!"
        }*/

        // instância do cliente da API
        val apiUsuarios = Apis.getApiUsuario()

        // instância do EndPoint (chamada) que busca p/ login e senha na API
        val chamadaPOST = apiUsuarios.postLogin(usuario, senha)

        // iniciando uma chamada ASSÍNCRONA na API
        chamadaPOST.enqueue(object : Callback<Boolean> { // do pacote retrofit2

            // quando houver comunicação com a API
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) { // status 2xx (200, 201, 204 etc)
                    val usuarios = response.body()
                    if (usuarios === true) {
                        tvAutenticacao.text = "Usuário autenticado!"
                    } else {
                        tvAutenticacao.text = "Login e/ou senha inválidos"
                    }
                } else {
                    tvAutenticacao.text = "Login e/ou senha inválidos"
                }
            }

            // quando não houver comunicação com a API
            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })

    }

}