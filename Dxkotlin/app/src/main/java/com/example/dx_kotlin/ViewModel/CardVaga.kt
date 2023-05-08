package com.example.dx_kotlin.ViewModel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Model.Vaga
import com.example.dx_kotlin.R
import com.example.dx_kotlin.Utilities.Apis
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardVaga : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_vaga)
        getVagas()
    }

    lateinit var txtTituloDaVaga: TextView
    lateinit var txtDescricaoDaVaga: TextView

    fun getVagas() {
        val apiVagas = Apis.getApiVagas()
        val chamadaGetVagas = apiVagas.getVagas()

        val txtTituloDaVaga = findViewById<TextView>(R.id.txt_titulo_da_vaga)
        val txtDescricaoDaVaga = findViewById<TextView>(R.id.txt_descricao_vaga)

        chamadaGetVagas.enqueue(object : Callback<List<Vaga>> {
            override fun onResponse(call: Call<List<Vaga>>, response: Response<List<Vaga>>) {
                if (response.isSuccessful) {
                    val vagas = response.body()
                    if (vagas != null && vagas.isNotEmpty()) {
                        txtTituloDaVaga.text = vagas.first().titulo.toString()
                        txtDescricaoDaVaga.text = vagas.first().tecnologia.toString()
                    } else {
                        Toast.makeText(baseContext, "A lista de vagas está vazia", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(baseContext, "Erro na resposta da API", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Vaga>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na comunicação com a API: ${t.message}", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

}