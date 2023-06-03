package com.example.dx_kotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dx_kotlin.Adapter.EmpresaAdapter
import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.Model.Vaga
import com.example.dx_kotlin.Utilities.Apis
import com.example.dx_kotlin.databinding.FragmentEmpresasBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmpresasFragment : Fragment() {


    lateinit var empresaAdapter: EmpresaAdapter

    private lateinit var binding: FragmentEmpresasBinding

    private val listaEmpresa = mutableListOf<Empresa>()

    val filteredList = mutableListOf<Vaga>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmpresasBinding.inflate(inflater)
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Chamado quando o usuário pressionar o botão de busca
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = newText?.trim() ?: ""
                filterList(searchText)
                return true
            }
        })

        return binding.root
    }

    private fun filterList(searchText: String) {
        val filteredList = if (searchText.isNotEmpty()) {
            listaEmpresa.filter { empresa ->
                empresa.nome.contains(searchText, ignoreCase = true)
            }
        } else {
            listaEmpresa
        }
        empresaAdapter.submitList(filteredList)
    }


    fun carregarListaDeEmpresas() {
        val apiEmpresa = Apis.getApiEmpresa()
        val call = apiEmpresa.getEmpresas()
        call.enqueue(object : Callback<List<Empresa>> {
            override fun onResponse(call: Call<List<Empresa>>, response: Response<List<Empresa>>) {
                if (response.isSuccessful) {
                    val empresas = response.body()
                    println(response.body())
                    if (empresas != null) {
                        // Popule sua lista de forma dinâmica
                        for (empresa in empresas) {
                            listaEmpresa.add(empresa) // Substitua "dadoDinamico" pelo atributo correto da classe Vaga
                        }
                        empresaAdapter.notifyDataSetChanged()
                        // Faça algo com a lista dinâmica
                    }


                    // Faça algo com as vagas retornadas
                } else {
                    // Lidar com o erro da resposta
                }
            }
            override fun onFailure(call: Call<List<Empresa>>, t: Throwable) {
                Toast.makeText(context, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configAdapter(view)
        carregarListaDeEmpresas()

//        binding.btnPubliqueAqui.setOnClickListener{
//            showDialog()
//        }

        val sharedPref = activity?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val apiUsuarios = Apis.getApiUsuario();
        val usuario = sharedPref?.getString("usuario",null)

        var cpf = sharedPref?.getString("cpf", null)
        var cnpj = sharedPref?.getString("cnpj", null)
        var isEmpresa = sharedPref?.getBoolean("isEmpresa", false)
        println(cpf)
        println(cnpj)
        println(isEmpresa)
//
//        if (isEmpresa == true){
//            binding.btnPubliqueAqui.visibility = View.VISIBLE
//        } else {
//            binding.btnPubliqueAqui.visibility = View.GONE
//        }

        // recuperar a informacao do usuario da shared preferences

        // view.findViewById<Button>(R.id.btn_publicar_vaga).visibility = if (isEmpresa) View.VISIBLE else View.GONE
        // se ficar estranho com GONE, usar INVISIBLE
    }

    private fun configAdapter(view: View) {
        val empresaRV = view?.findViewById<RecyclerView>(R.id.rv_empresas)!!
        empresaAdapter = EmpresaAdapter(listaEmpresa, view.context)

        val layoutManager = LinearLayoutManager(view.context)

        empresaRV.layoutManager = layoutManager

        empresaRV.adapter = empresaAdapter
    }

}