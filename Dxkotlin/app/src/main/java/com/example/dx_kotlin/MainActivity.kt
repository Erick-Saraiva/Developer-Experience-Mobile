package com.example.dx_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
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
    fun validarAutenticacao(view: View) {
        val tela2 = Intent(applicationContext, DadosCadastrais::class.java)
        val usuario = findViewById<EditText>(R.id.et_email).text.toString()
        val senha = findViewById<EditText>(R.id.et_senha).text.toString()
        val apiUsuarios = Apis.getApiUsuario()
        val chamadaPOST = apiUsuarios.postLogin(usuario, senha)

        chamadaPOST.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()

                    if (usuarios == true) {
                        tvAutenticacao.text = "Usuário autenticado!"
                        startActivity(tela2)
                    } else {
                        tvAutenticacao.text = "Login e/ou senha inválidos"
                    }
                } else {
                    tvAutenticacao.text = "Login e/ou senha "
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })

    }

    fun telaCadastro(view: View) {
        val telaCadastro = Intent(applicationContext, CadastroUsuario::class.java)
        startActivity(telaCadastro)
    }



}