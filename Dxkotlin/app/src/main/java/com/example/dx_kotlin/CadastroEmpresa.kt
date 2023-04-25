package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroEmpresa : AppCompatActivity() {
    lateinit var tvResultado: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_empresa)
        tvResultado = findViewById(R.id.tv_resultado)
    }

    
    fun cadastrarEmpresa(view: View) {
        val tela2 = Intent(applicationContext, MainActivity::class.java)
        val apiEmpresa = Apis.getApiEmpresa()
        var contador = 1


        val id = contador++
        val usuario = findViewById<EditText>(R.id.et_usuario).text.toString()
        val senha = findViewById<EditText>(R.id.et_senha).text.toString()
        val nome = findViewById<EditText>(R.id.et_nome).text.toString()
        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val cnpj = findViewById<EditText>(R.id.et_cnpj).text.toString()
        val rua = findViewById<EditText>(R.id.et_rua).text.toString()
        val numero = findViewById<EditText>(R.id.et_numero).text.toString()
        val cep = findViewById<EditText>(R.id.et_cep).text.toString()
        val bairro = findViewById<EditText>(R.id.et_bairro).text.toString()
        val cidade = findViewById<EditText>(R.id.et_cidade).text.toString()
        val numFuncionario = findViewById<EditText>(R.id.et_funcionarios).text.toString()
        val complemento = findViewById<EditText>(R.id.et_complemento).text.toString()
        val dataNascimento = findViewById<EditText>(R.id.et_data_nascimento).text.toString()
        val telefone = findViewById<EditText>(R.id.et_telefone).text.toString()


        val empresa = Empresa(id, usuario, senha,  dataNascimento,  nome, email, cnpj, rua, numero, cep, bairro, cidade, complemento, telefone, numFuncionario)

//        val myPost = Usuario(id = 1,"Vinicius",
//            "senha123","2002-10-12",
//            "vini",
//            "vinicius@gmail.com"
//            ,"365.359.248-82",
//            "teste",
//            "245",
//            "06411300","tests",
//            "teste",
//            "teste",
//            "11 90223-7823")
        val chamadaPOST = apiEmpresa.post(empresa)
        chamadaPOST.enqueue(object : Callback<Empresa> {
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                println("antes do response")
                println(call.request())
                if (response.isSuccessful) {
                    println("dps do response")
                    if (response.body() != null) {
                        tvResultado.text = "Empresa cadastrada!"
                        startActivity(tela2)
                    } else {
                        tvResultado.text = "Informações inválidas"
                    }
                } else {
                    tvResultado.text = "Informações outro texto"
                }
            }

            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })

    }
}