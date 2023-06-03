package com.example.dx_kotlin.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dx_kotlin.Model.Empresa
import com.example.dx_kotlin.R

class EmpresaAdapter(
    var lista: List<Empresa>,
    val context: Context)
    :RecyclerView.Adapter<EmpresaAdapter.FilmeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EmpresaAdapter.FilmeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.card_empresa,
            parent, false
        )
        return EmpresaAdapter.FilmeViewHolder(itemView)
    }


    fun submitList(filteredList: List<Empresa>) {
        lista = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: EmpresaAdapter.FilmeViewHolder, position: Int) {
        val empresa = lista.get(position)
        holder.nomeEmpresa.text = empresa.nome
        holder.numeroDeFuncionariosEmpresa.text = "Número de funcionários: " + empresa.numFuncionario
//        Picasso.with(context).load(empresa.urlImagem).into(holder.logo)
    }

    override fun getItemCount(): Int {
        return lista.size
    }


    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeEmpresa = itemView.findViewById<TextView>(R.id.txt_nome_empresa)
        val numeroDeFuncionariosEmpresa = itemView.findViewById<TextView>(R.id.txt_numero_funcionario)
        val logo = itemView.findViewById<ImageView>(R.id.img_logo_empresa)
    }

}