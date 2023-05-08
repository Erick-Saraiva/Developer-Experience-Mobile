package com.example.dx_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.dx_kotlin.Components.LoadingDialog
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Utilities.Apis
import retrofit2.Call
import retrofit2.Response

class DadosCadastrais : AppCompatActivity() {

    private lateinit var txtNome: TextView
    private lateinit var txtCpf: TextView
    private lateinit var txtEndereco: TextView
    private lateinit var txtTelefone: TextView
    private lateinit var txtUsername: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_cadastrais)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, NavegacaoFragment(), "FRAGMENT_MENU")
        transaction.replace(R.id.fragment_tela, DadosFragment(), "FRAGMENT_DADOS")
        transaction.commit()
        showLoading()

    }

    override fun onResume() {
        super.onResume()
        sessaoUsuario();
    }
    fun showLoading() {
        val loadingScreen = Intent(this, LoadingDialog::class.java)
        startActivity(loadingScreen)
    }

    fun sessaoUsuario(){
        val usuario = intent.getStringExtra("usuario")
        val apiUsuarios = Apis.getApiUsuario();
        val chamadaGetSessao = apiUsuarios.getUsuarioSession(usuario)

        chamadaGetSessao.enqueue(object :  retrofit2.Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    txtNome = findViewById(R.id.txt_nome)
                    txtCpf = findViewById(R.id.txt_responseCpf)
                    txtEndereco = findViewById(R.id.txt_onclick_mudar_endereco)
                    txtTelefone = findViewById(R.id.txt_mudar_telefo)
                    txtUsername = findViewById(R.id.txt_usuario)
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