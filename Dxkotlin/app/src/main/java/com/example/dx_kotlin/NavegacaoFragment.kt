package com.example.dx_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.dx_kotlin.databinding.FragmentNavegacaoBinding

class NavegacaoFragment : Fragment(){

    private lateinit var binding: FragmentNavegacaoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivDados.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction!!.replace(R.id.fragment_tela, DadosFragment(), "FRAGMENT_DADOS")
            transaction.commit()
        }
        binding.ivEmpresas.setOnClickListener {
//            var teste = binding.ivEmpresas.findViewById<ImageView>(R.id.iv_empresas)
//
//            teste.setOnClickListener {
//                teste.setImageResource(R.drawable.icon_monitor_selected)
//            }
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction!!.replace(R.id.fragment_tela, EmpresasFragment(), "FRAGMENT_EMPRESA")
            transaction.commit()
        }
        binding.ivVagas.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction!!.replace(R.id.fragment_tela, VagasFragment(), "FRAGMENT_VAGAS")
            transaction.commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentNavegacaoBinding.inflate(inflater)
         return binding.root
    }

}