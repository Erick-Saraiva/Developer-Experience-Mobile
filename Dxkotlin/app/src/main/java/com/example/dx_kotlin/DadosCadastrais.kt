package com.example.dx_kotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DadosCadastrais : AppCompatActivity() {

    lateinit var txtNome: TextView
    lateinit var txtCpf: TextView
    lateinit var txtEndereco: TextView
    lateinit var txtTelefone: TextView
    lateinit var txtUsername: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_cadastrais)
        txtNome = findViewById(R.id.txt_nome)
        txtCpf = findViewById(R.id.txt_responseCpf)
        txtEndereco = findViewById(R.id.txt_onclick_mudar_endereco)
        txtTelefone = findViewById(R.id.txt_mudar_telefo)
        txtUsername = findViewById(R.id.txt_usuario)
        sessaoUsuario();
    }

    fun sessaoUsuario(){
        val usuario = intent.getStringExtra("usuario")
        val apiUsuarios = Apis.getApiUsuario();
        val chamadaGetSessao = apiUsuarios.getUsuarioSession(usuario)

        chamadaGetSessao.enqueue(object :  retrofit2.Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    if (usuarios != null) {
                        txtNome.text = usuarios.nome
                        txtCpf.text = usuarios.cpf
                        txtEndereco.text = "${usuarios.rua}, ${usuarios.numero} - ${usuarios.bairro}"
                        txtTelefone.text = usuarios.telefone
                        txtUsername.text = usuarios.usuario

                    }
                } else {

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