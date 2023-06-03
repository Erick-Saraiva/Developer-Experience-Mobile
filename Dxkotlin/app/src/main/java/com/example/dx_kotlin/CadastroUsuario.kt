package com.example.dx_kotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Utilities.Apis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CadastroUsuario : AppCompatActivity() {
    lateinit var tvAutenticacao: TextView
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        tvAutenticacao = findViewById(R.id.tv_autenticacao)
        btnBack = findViewById(R.id.btn_back)

        btnBack.setOnClickListener(View.OnClickListener {
            finish() // chama o método finish() para fechar a Activity
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun cadastrarUsuario(view: View) {
        val tela2 = Intent(applicationContext, MainActivity::class.java)
        val apiUsuarios = Apis.getApiUsuario()
        var contador = 1


        val id = contador++
        val usuario = findViewById<EditText>(R.id.et_usuario).text.toString()
        val senha = findViewById<EditText>(R.id.et_senha).text.toString()
        val dataNascimento = findViewById<EditText>(R.id.et_data_nascimento).text.toString()
        val nome = findViewById<EditText>(R.id.et_nome).text.toString()
        val email = findViewById<EditText>(R.id.et_email).text.toString()
        val cpf = findViewById<EditText>(R.id.et_cpf).text.toString()
        val rua = findViewById<EditText>(R.id.et_rua).text.toString()
        val numero = findViewById<EditText>(R.id.et_numero).text.toString()
        val cep = findViewById<EditText>(R.id.et_cep).text.toString()
        val bairro = findViewById<EditText>(R.id.et_bairro).text.toString()
        val cidade = findViewById<EditText>(R.id.et_cidade).text.toString()
        val complemento = findViewById<EditText>(R.id.et_complemento).text.toString()
        val telefone = findViewById<EditText>(R.id.et_telefone).text.toString()


        val user = Usuario(id,
            usuario,
            senha,
            dataNascimento,
            nome,
            email,
            cpf,
            rua,
            numero,
            cep,
            bairro,
            cidade,
            complemento,
            telefone)

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
        val chamadaPOST = apiUsuarios.post(user)
        chamadaPOST.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                println("antes do response")
                println(call.request())
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    println("dps do response")
                    if (response.body() != null) {
                        tvAutenticacao.text = "Usuário cadastrado!"
                        startActivity(tela2)
                    } else {
                        tvAutenticacao.text = "Informações inválidas"
                    }
                } else {
                    println(response.body())
                    tvAutenticacao.text = "Informações outro texto"
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }

        })

    }

}