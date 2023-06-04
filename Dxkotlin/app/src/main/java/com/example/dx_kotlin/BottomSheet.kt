package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.example.dx_kotlin.Model.Vaga
import com.example.dx_kotlin.Utilities.Apis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomSheet: AppCompatActivity() {
    lateinit var btnBack: Button
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bottomsheet_layout)
        btnBack = findViewById(R.id.btn_back)
        btnBack.setOnClickListener(View.OnClickListener {
            finish()
        })
    }
    private var contador = 1

    @RequiresApi(Build.VERSION_CODES.O)
    fun cadastrarVaga(view: View) {
        val apiVaga = Apis.getApiVagas()

        val id = contador++
        val tituloVaga = findViewById<EditText>(R.id.et_titulo).text.toString()
        val descricaoVaga = findViewById<EditText>(R.id.et_descricao_vaga).text.toString()
        val linguagemVaga = findViewById<EditText>(R.id.et_linguagem).text.toString()
        val salarioVagaText = findViewById<EditText>(R.id.et_salario).text.toString()
        val salarioVaga = salarioVagaText.toDoubleOrNull() ?: 0.0
        val senioridadeVaga = findViewById<EditText>(R.id.et_senioridade).text.toString()

        val vaga = Vaga(
            id,
            descricaoVaga,
            salarioVaga,
            senioridadeVaga,
            tituloVaga,
            linguagemVaga,
            1,
            "teste"
        )

        apiVaga.postVaga(vaga).enqueue(object : Callback<Vaga> {
            override fun onResponse(call: Call<Vaga>, response: Response<Vaga>) {
                println(call.request())
                if (response.isSuccessful) {
                    val vaga = response.body()
                    println(response.body())
                    if (response.body() != null) {
                        exibirAlerta("Vaga publicada com sucesso")
                    } else {

                    }
                } else {

                }
            }

            override fun onFailure(call: Call<Vaga>, t: Throwable) {
                Toast.makeText(this@BottomSheet, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    fun exibirAlerta(mensagem: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Parabéns")
        alertDialogBuilder.setMessage(mensagem)
        alertDialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
