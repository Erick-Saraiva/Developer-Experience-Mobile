package com.example.dx_kotlin.Adapter

import android.annotation.SuppressLint
import com.example.dx_kotlin.R

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dx_kotlin.Model.Vaga
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
    }

    override fun getItemCount(): Int {
        println(lista.size)
        return lista.size
    }

    fun submitList(filteredList: List<Vaga>) {
        lista = filteredList
        notifyDataSetChanged()
    }


    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloVaga = itemView.findViewById<TextView>(R.id.txt_titulo_da_vaga)
        val descricaoVaga = itemView.findViewById<TextView>(R.id.txt_descricao_vaga)
        val senioridadeVaga = itemView.findViewById<TextView>(R.id.txt_senioridade)
        val logo = itemView.findViewById<ImageView>(R.id.img_logo_empresa)
    }

}