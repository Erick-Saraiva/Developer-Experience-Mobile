package com.example.dx_kotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Utilities.Apis
import com.example.dx_kotlin.databinding.FragmentDadosBinding
import retrofit2.Call
import retrofit2.Response


class DadosFragment : Fragment() {

    private lateinit var binding: FragmentDadosBinding

    var isEmpresa: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDadosBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPrefUsuario = activity?.getSharedPreferences("CONFIGS-USUARIO", Context.MODE_PRIVATE)
        val sharedPrefEmpresa = activity?.getSharedPreferences("CONFIGS-EMPRESA", Context.MODE_PRIVATE)
        val sharedPref = activity?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val usuario = sharedPrefUsuario?.getString("cpf", null)
        val empresa = sharedPrefEmpresa?.getString("cnpj", null)
        val nomeUsuario = sharedPrefUsuario?.getString("usuario", null)
        val nomeUsuarioEmpresa = sharedPrefEmpresa?.getString("usuario", null)

        println(usuario)
        println(empresa)
        println(nomeUsuario)
        println(nomeUsuarioEmpresa)

        if (nomeUsuario != null) {
            val apiUsuarios = Apis.getApiUsuario();
            val chamadaGetSessao = apiUsuarios.getUsuarioSession(nomeUsuario)
            chamadaGetSessao.enqueue(object : retrofit2.Callback<Usuario> {

                override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                    if (response.isSuccessful) {
                        val usuarioBody = response.body()
                        println(usuarioBody)
                        if (usuarioBody != null) {

                            binding.txtNome.text = usuarioBody.nome
                            binding.txtCpf.text = "CPF: "
                            binding.txtCpf.append(usuarioBody.cpf)
                            binding.txtOnclickMudarEndereco.text =
                                "${usuarioBody.rua}, ${usuarioBody.bairro} - ${usuarioBody.numero} "
                            binding.txtMudarTelefo.text = usuarioBody.telefone
                            binding.txtNomeAtual.text = usuarioBody.usuario
                        }
                    }
                }
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }
        else if (nomeUsuarioEmpresa != null) {
            val apiEmpresa = Apis.getApiEmpresa();
            val chamadaGetSessaoEmpresa = apiEmpresa.getEmpresaSession(nomeUsuarioEmpresa)

            chamadaGetSessaoEmpresa.enqueue(object : retrofit2.Callback<Empresa> {

                override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                    if (response.isSuccessful) {
                        val empresaBody = response.body()
                        println(empresaBody)
                        if (empresaBody != null) {
                            binding.txtNome.text = empresaBody.nome
                            binding.txtCpf.text = "CNPJ: "
                            binding.txtCpf.append(empresaBody.cnpj)
                            binding.txtOnclickMudarEndereco.text =
                                "${empresaBody.rua}, ${empresaBody.bairro} - ${empresaBody.numero} "
                            binding.txtMudarTelefo.text = empresaBody.telefone
                            binding.txtNomeAtual.text = empresaBody.usuario
                        }
                    }
                }

                override fun onFailure(call: Call<Empresa>, t: Throwable) {
                    t.printStackTrace()
                }
            })

        }
    }
}