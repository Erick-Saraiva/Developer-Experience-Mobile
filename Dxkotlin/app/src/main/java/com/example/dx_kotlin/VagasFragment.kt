package com.example.dx_kotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.dx_kotlin.Adapter.VagaAdapter
import com.example.dx_kotlin.Model.Vaga

class VagasFragment : Fragment() {

    lateinit var vagaAdapter: VagaAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val listaVagas = mutableListOf<Vaga>()


    fun carregarListaDeVagas() {
        listaVagas.add(Vaga(1, 100.0, "SR", "vaga loka", "c#", 2, "https://www.sptech.school/assets/images/logos/sptech_logo.png"))
        listaVagas.add(Vaga(11, 15450.0, "JR", "vaga rferfref", "JS", 2, "http://pudim.com.br/pudim.jpg"))
        listaVagas.add(Vaga(122, 6500.0, "PL", "va btr  tga l oka", "Cobol", 2, "https://www.sptech.school/assets/images/logos/parceiras/Accenture.png"))
        vagaAdapter.notifyDataSetChanged()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vagas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configAdapter(view)

        carregarListaDeVagas()

        // recuperar a informacao do usuario da shared preferences
        val isEmpresa = false

        view.findViewById<Button>(R.id.btn_publicar_vaga).visibility = if (isEmpresa) View.VISIBLE else View.GONE
        // se ficar estranho com GONE, usar INVISIBLE
    }

    private fun configAdapter(view: View) {
        val vagaRV = view?.findViewById<RecyclerView>(R.id.rv_vagas)!!

        vagaAdapter = VagaAdapter(listaVagas, view.context)

        val layoutManager = LinearLayoutManager(view.context)

        vagaRV.layoutManager = layoutManager

        vagaRV.adapter = vagaAdapter
    }


}


