package com.example.dx_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Model.Vaga
import com.example.dx_kotlin.Utilities.Apis
import com.example.dx_kotlin.ViewModel.CardVaga
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class VagasFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vagas, container, false)
    }


}


