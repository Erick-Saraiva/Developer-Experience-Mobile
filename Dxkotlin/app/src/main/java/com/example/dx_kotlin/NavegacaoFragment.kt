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
            transaction?.replace(R.id.fragment_tela, DadosFragment(), "FRAGMENT_DADOS")
            transaction?.commit()

            resetImageViews()
            binding.ivDados.setBackgroundResource(R.drawable.icon_user)
        }

        binding.ivEmpresas.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_tela, EmpresasFragment(), "FRAGMENT_EMPRESA")
            transaction?.commit()

            resetImageViews()
            binding.ivEmpresas.setBackgroundResource(R.drawable.icon_empresa_selecionado)
        }

        binding.ivVagas.setOnClickListener {
            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragment_tela, VagasFragment(), "FRAGMENT_VAGAS")
            transaction?.commit()

            resetImageViews()
            binding.ivVagas.setBackgroundResource(R.drawable.icon_monitor_selected)
        }
    }

    fun resetImageViews() {
        binding.ivDados.setBackgroundResource(R.drawable.user)
        binding.ivEmpresas.setBackgroundResource(R.drawable.icon_grid)
        binding.ivVagas.setBackgroundResource(R.drawable.icon_monitor)
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