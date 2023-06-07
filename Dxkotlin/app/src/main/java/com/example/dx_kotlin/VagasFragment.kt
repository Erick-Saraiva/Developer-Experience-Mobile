package com.example.dx_kotlin

import android.app.ActionBar
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import com.example.dx_kotlin.Adapter.VagaAdapter
import com.example.dx_kotlin.Model.Usuario
import com.example.dx_kotlin.Model.Vaga
import com.example.dx_kotlin.Utilities.ApiVaga
import com.example.dx_kotlin.Utilities.Apis
import com.example.dx_kotlin.databinding.FragmentVagasBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Response

class VagasFragment : Fragment() {

    lateinit var vagaAdapter: VagaAdapter

    private lateinit var binding: com.example.dx_kotlin.databinding.FragmentVagasBinding

    private val listaVagas = mutableListOf<Vaga>()

    fun carregarListaDeVagas() {
        val apiVagas = Apis.getApiVagas()
        val call = apiVagas.getVagas()
        call.enqueue(object : Callback<List<Vaga>> {
            override fun onResponse(call: Call<List<Vaga>>, response: Response<List<Vaga>>) {
                if (response.isSuccessful) {
                    val vagas = response.body()
                    println(response.body())
                    if (vagas != null) {
                        // Popule sua lista de forma dinâmica
                        for (vaga in vagas) {
                            listaVagas.add(vaga) // Substitua "dadoDinamico" pelo atributo correto da classe Vaga
                        }
                        vagaAdapter.notifyDataSetChanged()
                        // Faça algo com a lista dinâmica
                    }

                } else {
                    // Lidar com o erro da resposta
                }
            }
            override fun onFailure(call: Call<List<Vaga>>, t: Throwable) {
                Toast.makeText(context, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVagasBinding.inflate(inflater)
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
            listaVagas.filter { vaga ->
                vaga.titulo.contains(searchText, ignoreCase = true)
            }
        } else {
            listaVagas
        }
        vagaAdapter.submitList(filteredList)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configAdapter(view)
        carregarListaDeVagas()

        binding.btnPubliqueAqui.setOnClickListener {
            versaoActivity()
        }
        val sharedPrefUsuario = activity?.getSharedPreferences("CONFIGS-USUARIO", Context.MODE_PRIVATE)
        val sharedPrefEmpresa = activity?.getSharedPreferences("CONFIGS-EMPRESA", Context.MODE_PRIVATE)
        val sharedPref = activity?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val usuario = sharedPrefUsuario?.getString("cpf",null)
        val empresa = sharedPrefEmpresa?.getString("cnpj",null)

        println(usuario)
        println(empresa)
//
        if (usuario != null){
            binding.btnPubliqueAqui.visibility = View.GONE
        }
        if (empresa != null) {
            binding.btnPubliqueAqui.visibility = View.VISIBLE
        }

        // recuperar a informacao do usuario da shared preferences

       // view.findViewById<Button>(R.id.btn_publicar_vaga).visibility = if (isEmpresa) View.VISIBLE else View.GONE
        // se ficar estranho com GONE, usar INVISIBLE
    }

    private fun configAdapter(view: View) {
        val vagaRV = view?.findViewById<RecyclerView>(R.id.rv_vagas)!!
        vagaAdapter = VagaAdapter(listaVagas, view.context)

        val layoutManager = LinearLayoutManager(view.context)

        vagaRV.layoutManager = layoutManager

        vagaRV.adapter = vagaAdapter
    }


    private fun versaoActivity() {
        val intent = Intent(requireContext(), BottomSheet::class.java)
        startActivity(intent)

    }

//    private fun bottomSheetCadastroVaga() {
//        val dialog = Dialog(requireContext())
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog.setContentView(R.layout.bottomsheet_layout)
//
//        dialog.show()
//        dialog.window!!.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
//        dialog.window!!.setGravity(Gravity.BOTTOM)
//    }

}





