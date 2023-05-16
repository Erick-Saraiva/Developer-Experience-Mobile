package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dx_kotlin.Utilities.Apis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvAutenticacao: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val botao = findViewById<Button>(R.id.btn_cadastro)
        val btnLogin = findViewById<Button>(R.id.btn_Login)
        tvAutenticacao = findViewById(R.id.tv_autenticacao)
        val txtCadastroEmpresa = findViewById<TextView>(R.id.txt_cadastro_empresa)


        txtCadastroEmpresa.setOnClickListener {
            telaCadastroEmpresa()
        }
        botao.setOnClickListener{
            cadastroUsuario()
        }
//
//        btnLogin.setOnClickListener{
//        }
    }


     fun cadastroUsuario() {
        val cadastroUsuario = Intent(this,CadastroUsuario::class.java)
        startActivity(cadastroUsuario)
    }

    fun validarAutenticacao(view: View) {
        val tela2 = Intent(applicationContext, DadosCadastrais::class.java)
        val usuario = findViewById<EditText>(R.id.et_email).text.toString()
        val senha = findViewById<EditText>(R.id.et_senha).text.toString()
        val sharedPref = getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val apiUsuarios = Apis.getApiUsuario()
        val apiEmpresa = Apis.getApiEmpresa()

        val callback = object : Callback<Boolean> {
            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful) {
                    val isAuthenticated = response.body() ?: false

                    if (isAuthenticated) {
                        tvAutenticacao.text = "Usuário autenticado!"
                        tela2.putExtra("usuario", usuario)
                        sharedPref.edit().putString("usuario", usuario).apply()
                        startActivity(tela2)
                    } else {
                        tvAutenticacao.text = "Login e/ou senha inválidos"
                    }
                } else {
                    tvAutenticacao.text = "Erro na API: ${response.message()}"
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                tvAutenticacao.text = "Erro na API: ${t.message}"
                Toast.makeText(baseContext, "Erro na API: ${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        }

        apiUsuarios.postLogin(usuario, senha).enqueue(callback)
        apiEmpresa.postLogin(usuario, senha).enqueue(callback)
    }

//    fun validarAutenticacao(view: View) {
//        val tela2 = Intent(applicationContext, DadosCadastrais::class.java)
//        val usuario = findViewById<EditText>(R.id.et_email).text.toString();
//        val senha = findViewById<EditText>(R.id.et_senha).text.toString();
//        val apiUsuarios = Apis.getApiUsuario();
//        val chamadaPOST = apiUsuarios.postLogin(usuario, senha);
//        val apiEmpresa = Apis.getApiEmpresa()
//        apiEmpresa.postLogin(usuario,senha)
//        val sharedPref = getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
//
//        chamadaPOST.enqueue(object : Callback<Boolean> {
//            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
//                if (response.isSuccessful) {
//                    val usuarios = response.body()
//
//                    if (usuarios == true) {
//                        tvAutenticacao.text = "Usuário autenticado!"
//                        tela2.putExtra("usuario", usuario)
//
//                        sharedPref.edit().putString("usuario", usuario).apply()
//
//                        startActivity(tela2)
//
//                    } else {
//                        tvAutenticacao.text = "Login e/ou senha inválidos"
//                    }
//                } else {
//                    tvAutenticacao.text = "Login e/ou senha inválidos"
//                }
//            }
//
//
//            override fun onFailure(call: Call<Boolean>, t: Throwable) {
//                Toast.makeText(baseContext, "Erro na API: ${t.message}",
//                    Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//
//        })
//
//        apiEmpresa.postLogin(usuario,senha).enqueue(object : Callback<Boolean> {
//            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
//                if (response.isSuccessful) {
//                    val empresas = response.body()
//
//                    if (empresas == true) {
//                        tvAutenticacao.text = "Usuário autenticado!"
//                        tela2.putExtra("empresa", usuario)
//
//                        sharedPref.edit().putString("empresa", usuario).apply()
//
//                        startActivity(tela2)
//
//                    } else {
//                        tvAutenticacao.text = "Login e/ou senha inválidos"
//                    }
//                } else {
//                    tvAutenticacao.text = "Login e/ou senha inválidos"
//                }
//            }
//
//
//            override fun onFailure(call: Call<Boolean>, t: Throwable) {
//                Toast.makeText(baseContext, "Erro na API: ${t.message}",
//                    Toast.LENGTH_SHORT).show()
//                t.printStackTrace()
//            }
//
//        })
//
//    }

    fun telaCadastro(view: View) {
        val telaCadastro = Intent(applicationContext, CadastroUsuario::class.java)
        startActivity(telaCadastro)
    }

    fun telaCadastroEmpresa () {
        val telaCadastroEmpresa = Intent(applicationContext, CadastroEmpresa::class.java)
        startActivity(telaCadastroEmpresa)
    }

}