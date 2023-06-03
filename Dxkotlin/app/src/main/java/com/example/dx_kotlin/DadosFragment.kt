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

        val sharedPref = activity?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)

        val usuario = sharedPref?.getString("usuario", null)


        val apiUsuarios = Apis.getApiUsuario();
        val chamadaGetSessao = apiUsuarios.getUsuarioSession(usuario)


        chamadaGetSessao.enqueue(object :  retrofit2.Callback<Usuario> {

            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    val usuarios = response.body()
                    if (usuarios != null) {
                        binding.txtNome.text = usuarios.nome
                        binding.txtCpf.append(usuarios.cpf)
                        binding.txtOnclickMudarEndereco.text = "${usuarios.rua}, ${usuarios.bairro} - ${usuarios.numero} "
                        binding.txtMudarTelefo.text = usuarios.telefone
                        binding.txtNomeAtual.text = usuarios.usuario
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                t.printStackTrace()
            }
        })

        val empresa = sharedPref?.getString("usuario", null)
        val apiEmpresa = Apis.getApiEmpresa();
        val chamadaGetSessaoEmpresa = apiEmpresa.getEmpresaSession(empresa)
        chamadaGetSessaoEmpresa.enqueue(object :  retrofit2.Callback<Empresa> {
            override fun onResponse(call: Call<Empresa>, response: Response<Empresa>) {
                if (response.isSuccessful) {
                    val empresa = response.body()
                    if (empresa != null) {
                        empresa.isEmpresa = true
                        //var isEmpresa = sharedPref?.edit()?.putBoolean("isEmpresa",true)?.apply()
                       // var cpnj = sharedPref?.edit()?.putString("cnpj",usuarios.cnpj)?.apply()

//                        binding.txtNome.text = usuarios.nome
//                        binding.txtCpf.append(usuarios.cpf)
//                        binding.txtOnclickMudarEndereco.text = "${usuarios.rua}, ${usuarios.bairro} - ${usuarios.numero} "
//                        binding.txtMudarTelefo.text = usuarios.telefone
//                        binding.txtNomeAtual.text = usuarios.usuario
                    }
                } else {

                }
            }

            override fun onFailure(call: Call<Empresa>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}