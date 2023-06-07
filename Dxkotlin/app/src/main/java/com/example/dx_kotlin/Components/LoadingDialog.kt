package com.example.dx_kotlin.Components

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.dx_kotlin.R

class LoadingDialog : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_dialog)
        Handler().postDelayed(Runnable { finish() }, 3000)
    }


}