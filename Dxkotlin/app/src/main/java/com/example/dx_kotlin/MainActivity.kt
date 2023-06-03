package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import com.example.dx_kotlin.Adapter.VagaAdapter
import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.Model.Usuario
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

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_modal_lgpd)
        dialog.show()


        txtCadastroEmpresa.setOnClickListener {
            telaCadastroEmpresa()
        }
        botao.setOnClickListener {
            cadastroUsuario()
        }
    }

    fun cadastroUsuario() {
        val cadastroUsuario = Intent(this, CadastroUsuario::class.java)
        startActivity(cadastroUsuario)
    }

    fun validarAutenticacao(view: View) {
        val tela2 = Intent(applicationContext, DadosCadastrais::class.java)
        val vagaAdapter = Intent(applicationContext, VagaAdapter::class.java)
        val usuario = findViewById<EditText>(R.id.et_email).text.toString()
        val senha = findViewById<EditText>(R.id.et_senha).text.toString()
        val sharedPref = getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val apiUsuarios = Apis.getApiUsuario()
        val apiEmpresa = Apis.getApiEmpresa()
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val selectedRadioButtonId = radioGroup.checkedRadioButtonId

        vagaAdapter.putExtra("senha", senha)
        sharedPref.edit().putString("senha", senha).apply()
        if (selectedRadioButtonId == R.id.radioEmpresa) {
            // Item "Empresa" selecionado
            val callbackEmpresa = object : Callback<Empresa> {
                override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            tvAutenticacao.text = "Empresa autenticada!"
                            tela2.putExtra("empresa", usuario)
                            sharedPref.edit().putString("empresa", usuario).apply()
                            startActivity(tela2)
                        } else {
                            tvAutenticacao.text = "Login e/ou senha inválidos"
                        }
                    } else {
                        tvAutenticacao.text = "Erro na API: ${response.message()}"
                    }
                }
                override fun onFailure(call: Call<Empresa>, t: Throwable) {
                    tvAutenticacao.text = "Erro na API: ${t.message}"
                    Toast.makeText(baseContext, "Erro na API: ${t.message}", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            }

            apiEmpresa.postLogin(usuario, senha).enqueue(callbackEmpresa)

        } else if (selectedRadioButtonId == R.id.radioUsuario) {
            // Item "Usuário" selecionado
            val callbackUsuario = object : Callback<Usuario> {
                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {

                        if (response.body() != null) {
                            tvAutenticacao.text = "Usuário autenticado!"

                            tela2.putExtra("usuario", usuario)
                            sharedPref.edit().putString("usuario", usuario).apply()
                            vagaAdapter.putExtra("idUsuario", response.body()!!.idUsuario)
                            sharedPref.edit().putInt("idUsuario", response.body()!!.idUsuario).apply()
                            startActivity(tela2)
                        } else {
                            tvAutenticacao.text = "Login e/ou senha inválidos"
                        }
                    } else {
                        tvAutenticacao.text = "Erro na API: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    tvAutenticacao.text = "Erro na API: ${t.message}"
                    Toast.makeText(baseContext, "Erro na API: ${t.message}", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            }
            apiUsuarios.postLogin(usuario, senha).enqueue(callbackUsuario)
        } else {
            // Nenhum item selecionado
            tvAutenticacao.text = "Selecione uma opção (Empresa/Usuário)"
        }
    }

    fun telaCadastroEmpresa() {
        val telaCadastroEmpresa = Intent(applicationContext, CadastroEmpresa::class.java)
        startActivity(telaCadastroEmpresa)
    }

}