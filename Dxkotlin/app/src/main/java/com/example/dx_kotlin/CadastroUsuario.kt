package com.example.dx_kotlin

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CadastroUsuario : AppCompatActivity() {
    lateinit var tvAutenticacao: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_usuario)
        tvAutenticacao = findViewById(R.id.tv_autenticacao)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validarAutenticacao(view: View) {
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
        val numero = findViewById<EditText>(R.id.et_numero).text.toString().toInt()
        val cep = findViewById<EditText>(R.id.et_cep).text.toString()
        val bairro = findViewById<EditText>(R.id.et_bairro).text.toString()
        val cidade = findViewById<EditText>(R.id.et_cidade).text.toString()
        val complemento = findViewById<EditText>(R.id.et_complemento).text.toString()
        val telefone = findViewById<EditText>(R.id.et_telefone).text.toString()

        val data = LocalDate.parse(dataNascimento)

        val chamadaPOST = apiUsuarios.cadastroUsuario(Usuario(id, usuario, senha, data, nome, email, cpf, rua, numero, cep, bairro, cidade, complemento, telefone))

        chamadaPOST.enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()

                    if (usuarios?.id != 0) {
                        tvAutenticacao.text = "Usuário cadastrado!"
                        startActivity(tela2)
                    } else {
                        tvAutenticacao.text = "Informações inválidas"
                    }
                } else {
                    tvAutenticacao.text = "Informações inválidas"
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