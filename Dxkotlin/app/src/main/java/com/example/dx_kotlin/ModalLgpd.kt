package com.example.dx_kotlin

import android.annotation.SuppressLint
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ModalLgpd : AppCompatActivity() {
    lateinit var btnBack: Button
    lateinit var dialog: Dialog

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBack.setOnClickListener {
            dialog.dismiss() // fecha o modal
        }

        dialog = Dialog(this)
        dialog.setContentView(R.layout.activity_modal_lgpd)

        val btnOk = dialog.findViewById<Button>(R.id.btn_ok_entendi)
        btnOk.setOnClickListener {
            dialog.dismiss() // fecha o modal
        }

        dialog.show()
    }


}