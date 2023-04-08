package com.example.projetoformulario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
        fun Cadastrar(componente: View){
            Toast.makeText(baseContext, "e nos no android", Toast.LENGTH_SHORT).show()

            val etNome = findViewById<EditText>(R.id.et_nome)
            val etIdade = findViewById<EditText>(R.id.et_idade)
            val etPeso = findViewById<EditText>(R.id.et_peso)
            val etAltura = findViewById<EditText>(R.id.et_altura)
            val tvResultado = findViewById<TextView>(R.id.tv_resultado)
            var valido = true;

            if(etNome.text.isBlank()){
                valido = false;
                etNome.error = "O nome e obrigatorio"
            }

            if (etNome.text.length < 5){
                valido = false
                etNome.error = "O nome deve ter pelo menos 5 caracteres"
            }

            if (etIdade.text.isBlank()){
                valido = false;
                etIdade.error = "A idade e obrigatoria"
            } else if(etIdade.text.toString().toInt() > 120){
                valido = false;
                etIdade.error = "A idade deve ser ate 120"
            }

            if (etAltura.text.isBlank()){
                valido = false
                etAltura.error = ""
            }


            if(valido){
                tvResultado.text = "Cadastro efetuado com sucesso com nome ${etNome.text} e idade ${etIdade.text}"
            } else{
                tvResultado.text = "Cadastro invalido!"
            }



        }

}
