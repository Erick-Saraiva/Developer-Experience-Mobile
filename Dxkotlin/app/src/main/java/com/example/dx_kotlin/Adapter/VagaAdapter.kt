package com.example.dx_kotlin.Adapter
import android.app.AlertDialog
import com.example.dx_kotlin.R
import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.dx_kotlin.Model.*
import com.example.dx_kotlin.Utilities.Apis
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception


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
        val sharedPref = context?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val idUsuario = sharedPref?.getInt("idUsuario", 0)
        val usuario = sharedPref?.getString("usuario", null)
        val senha = sharedPref!!.getString("senha", null)
        val idEmpresa = 1;
        val vaga = lista.get(position)
        var idVaga = vaga.id
        holder.tituloVaga.text = vaga.titulo
        holder.descricaoVaga.text = "Descrição: " + vaga.tecnologia
        holder.senioridadeVaga.text ="Senioridade: " + vaga.senioridade
        Picasso.with(context).load(vaga.urlImagem).into(holder.logo)
        holder.btnCandidatarse.setOnClickListener {
            if (idUsuario != null) {
                candidatarse(idVaga, idUsuario, idEmpresa)
                val color = ContextCompat.getColor(context, R.color.btn_candidatar_desativado)
                holder.btnCandidatarse.backgroundTintList = ColorStateList.valueOf(color)
                holder.btnCandidatarse.isEnabled = false
            }
        }
        holder.btnCancelar.setOnClickListener {
            if (usuario != null && senha != null) {
                desfazer()
                val color = ContextCompat.getColor(context, R.color.btn_candidatar)
                holder.btnCandidatarse.backgroundTintList = ColorStateList.valueOf(color)
                holder.btnCandidatarse.isEnabled = true
            }
        }


        val imageUrl = vaga.urlImagem
        val imageFile = getImageFile(position)

        if (imageFile.exists()) {
            // Se a imagem já existe no diretório local, carrega a partir do arquivo
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
            holder.logo.setImageBitmap(bitmap)
        } else {
            // Caso contrário, carrega a imagem da URL usando o Picasso
            Picasso.with(context).load(imageUrl).into(object : com.squareup.picasso.Target {
                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    if (bitmap != null) {
                        holder.logo.setImageBitmap(bitmap)
                        SaveImageTask(imageFile).execute(bitmap)
                    }
                }

                override fun onBitmapFailed(errorDrawable: Drawable?) {
                    Log.d(TAG, "Failed to load image from URL: $imageUrl")
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                    // Nada a ser feito
                }
            })
        }

    }

    private inner class SaveImageTask(private val imageFile: File) :
        AsyncTask<Bitmap, Void, Boolean>() {

        override fun doInBackground(vararg params: Bitmap?): Boolean {
            val bitmap = params[0]
            return try {
                val fileOutputStream = FileOutputStream(imageFile)
                bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                true
            } catch (e: Exception) {
                Log.e(TAG, "Failed to save image: ${imageFile.absolutePath}", e)
                false
            }
        }
    }

    private fun getImageFile(position: Int): File {
        val fileName = "image_$position.jpg"
        val directory = context.getExternalFilesDir(null)
        return File(directory, fileName)
    }

    companion object {
        private const val TAG = "VagaAdapter"
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun submitList(filteredList: List<Vaga>) {
        lista = filteredList
        notifyDataSetChanged()
    }

    private fun obterCandidatura(idVaga: Int, idUsuario: Int, idEmpresa: Int): VagaEmpresaUsuario {
        val seCandidatar: VagaEmpresaUsuario =
            VagaEmpresaUsuario("",
                vaga = Vagateste(idVaga),
                usuario = Usuarioteste(idUsuario),
                empresa = Empresateste(idEmpresa))
        return seCandidatar;
    }

    private fun candidatarse(idVaga: Int, idUsuario: Int, idEmpresa: Int){
        val apiUsuario = Apis.getApiUsuario()
        val call = apiUsuario.canditarAVaga(obterCandidatura(idVaga, idUsuario, idEmpresa))
        call.enqueue(object : Callback <Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.isSuccessful) {
                    exibirAlerta("Obrigado por se candidatar\na empresa entrará em contato")
                    val vagas = response.body()
                    if (vagas != null) {
                    }
                } else {
                }
            }
            override fun onFailure(call: Call<Any>, t: Throwable) {
                Toast.makeText(context, "Erro na API: ${t.message}",
                    Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }


        })
    }
    private fun desfazer() {
        val sharedPref = context?.getSharedPreferences("CONFIGS", Context.MODE_PRIVATE)
        val usuario = sharedPref?.getString("usuario", null)
        val senha = sharedPref?.getString("senha", "sem valor")
        val apiUsuario = Apis.getApiUsuario()

        if (usuario != null) {
            if (senha != null) {
                apiUsuario.desfazerPilha(usuario,senha).enqueue(object : Callback<Any> {
                    override fun onResponse(call: Call<Any>, response: Response<Any>) {
                        if (response.isSuccessful) {
                            val pilha = response.body()
                            exibirAlerta("Inscrição cancelada")
                            println(response.body())
                            if (pilha != null) {
                                // Lidar com a resposta bem-sucedida
                            }
                        } else {
                        }
                    }

                    override fun onFailure(call: Call<Any>, t: Throwable) {
                        Toast.makeText(context, "Erro na API: ${t.message}", Toast.LENGTH_SHORT).show()
                        t.printStackTrace()
                    }
                })
            }
        }
    }


    class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloVaga = itemView.findViewById<TextView>(R.id.txt_titulo_da_vaga)
        val descricaoVaga = itemView.findViewById<TextView>(R.id.txt_descricao_vaga)
        val senioridadeVaga = itemView.findViewById<TextView>(R.id.txt_senioridade)
        val logo = itemView.findViewById<ImageView>(R.id.img_logo_empresa)
        val btnCandidatarse = itemView.findViewById<Button>(R.id.btn_candidatar_se)
        val btnCancelar = itemView.findViewById<Button>(R.id.btn_cancelar)
    }
    fun exibirAlerta(mensagem: String) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setMessage(mensagem)
        alertDialogBuilder.setPositiveButton("Ok") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}