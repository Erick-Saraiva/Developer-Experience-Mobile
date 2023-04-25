package com.example.dx_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DadosCadastrais : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dados_cadastrais)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_menu, NavegacaoFragment(), "FRAGMENT_MENU")
        transaction.replace(R.id.fragment_tela, DadosFragment(), "FRAGMENT_HOME")
        transaction.commit()
    }


}