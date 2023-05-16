package com.example.dx_kotlin

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
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

        sessaoUsuario()

    }

    fun showLoading() {
        val loadingScreen = Intent(this, LoadingDialog::class.java)
        startActivity(loadingScreen)
    }

    fun sessaoUsuario(){

    }


}