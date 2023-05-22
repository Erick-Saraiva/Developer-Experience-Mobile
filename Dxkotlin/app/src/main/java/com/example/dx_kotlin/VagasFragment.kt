package com.example.dx_kotlin

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import com.example.dx_kotlin.Adapter.VagaAdapter
import com.example.dx_kotlin.Model.Vaga
import com.example.dx_kotlin.Utilities.Apis
import com.example.dx_kotlin.databinding.FragmentVagasBinding

class VagasFragment : Fragment() {

    lateinit var vagaAdapter: VagaAdapter

    private lateinit var binding: FragmentVagasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    private val listaVagas = mutableListOf<Vaga>()

    fun carregarListaDeVagas() {

        listaVagas.add(Vaga(1, 100.0, "SR", "vaga loka", "c#", 2, "https://www.sptech.school/assets/images/logos/sptech_logo.png"))
        listaVagas.add(Vaga(11, 15450.0, "JR", "vaga rferfref", "JS", 2, "http://pudim.com.br/pudim.jpg"))
        listaVagas.add(Vaga(122, 6500.0, "PL", "va btr  tga l oka", "Cobol", 2, "https://www.sptech.school/assets/images/logos/parceiras/Accenture.png"))
        listaVagas.add(Vaga(122, 6500.0, "PL", "teste123  tga l oka", "Cobol", 2, "https://www.sptech.school/assets/images/logos/parceiras/Accenture.png"))
        vagaAdapter.notifyDataSetChanged()
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_vagas, container, false)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVagasBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configAdapter(view)

        carregarListaDeVagas()

        binding.btnPubliqueAqui.setOnClickListener{
            showDialog()
        }

        val sharedPref = activity?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val apiUsuarios = Apis.getApiUsuario();
        val usuario = sharedPref?.getString("usuario",null)


        var cpf = sharedPref?.getString("cpf", null)
        var cnpj = sharedPref?.getString("cnpj", null)
        var isEmpresa = sharedPref?.getBoolean("isEmpresa", false)
        println(cpf)
        println(cnpj)
        println(isEmpresa)

        if (isEmpresa == true){
            binding.btnPubliqueAqui.visibility = View.VISIBLE
        } else {
            binding.btnPubliqueAqui.visibility = View.GONE
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


    private fun showDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheet_layout)

        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)


    }

}


