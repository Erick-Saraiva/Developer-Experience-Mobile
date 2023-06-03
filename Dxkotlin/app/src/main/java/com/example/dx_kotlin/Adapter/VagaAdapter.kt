package com.example.dx_kotlin.Adapter

import com.example.dx_kotlin.R

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.dx_kotlin.Model.*
import com.example.dx_kotlin.Utilities.Apis
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VagaAdapter(
    var lista: List<Vaga>,
    val context: Context)
    : RecyclerView.Adapter<VagaAdapter.FilmeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VagaAdapter.FilmeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_vaga,
            parent, false
        )
        return FilmeViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: VagaAdapter.FilmeViewHolder, position: Int) {
        val vaga = lista.get(position)
        holder.tituloVaga.text = vaga.titulo
        holder.descricaoVaga.text = "Descrição: " + vaga.tecnologia
        holder.senioridadeVaga.text ="Senioridade: " + vaga.senioridade
        Picasso.with(context).load(vaga.urlImagem).into(holder.logo)
        holder.btnCandidatarse.setOnClickListener {
            candidatarse()
            print("dadlsdsd")
        }
    }

    override fun getItemCount(): Int {
        println(lista.size)
        return lista.size
    }

    fun submitList(filteredList: List<Vaga>) {
        lista = filteredList
        notifyDataSetChanged()
    }

    private fun obterCandidatura(): VagaEmpresaUsuario {
        val seCandidatar: VagaEmpresaUsuario = VagaEmpresaUsuario("", vaga = Vagateste(1), usuario = Usuarioteste(1), empresa = Empresateste(1))
        return seCandidatar;
    }

    private fun candidatarse(){
        val apiUsuario = Apis.getApiUsuario()

        val call = apiUsuario.canditarAVaga(obterCandidatura())
        call.enqueue(object : Callback <Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    val vagas = response.body()
                    println(response.body())

                    if (vagas != null) {

                    }

                    // Faça algo com as vagas retornadas
                } else {
                    // Lidar com o erro da resposta
                    println(response.body())
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(context, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }


        })
    }


    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloVaga = itemView.findViewById<TextView>(R.id.txt_titulo_da_vaga)
        val descricaoVaga = itemView.findViewById<TextView>(R.id.txt_descricao_vaga)
        val senioridadeVaga = itemView.findViewById<TextView>(R.id.txt_senioridade)
        val logo = itemView.findViewById<ImageView>(R.id.img_logo_empresa)
        val btnCandidatarse = itemView.findViewById<Button>(R.id.btn_candidatar_se)
    }



}