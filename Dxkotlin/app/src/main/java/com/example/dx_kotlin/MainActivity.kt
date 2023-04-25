package com.example.dx_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        tvAutenticacao = findViewById(R.id.tv_autenticacao)
    }
    fun validarAutenticacao(view: View) {
        val tela2 = Intent(applicationContext, DadosCadastrais::class.java)
        val usuario = findViewById<EditText>(R.id.et_email).text.toString();
        val senha = findViewById<EditText>(R.id.et_senha).text.toString();
        val apiUsuarios = Apis.getApiUsuario();
        val chamadaPOST = apiUsuarios.postLogin(usuario, senha);

        chamadaPOST.enqueue(object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()

                    if (usuarios == true) {
                        tvAutenticacao.text = "Usuário autenticado!"
                        tela2.putExtra("usuario", usuario)
                        startActivity(tela2)
                    } else {
                        tvAutenticacao.text = "Login e/ou senha inválidos"
                    }
                } else {
                    tvAutenticacao.text = "Login e/ou senha inválidos"
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

    fun telaCadastroEmpresa (view: View) {
        val telaCadastroEmpresa = Intent(applicationContext, CadastroEmpresa::class.java)
        startActivity(telaCadastroEmpresa)
    }

}