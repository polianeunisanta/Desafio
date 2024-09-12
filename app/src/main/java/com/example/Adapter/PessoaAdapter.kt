package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.example.desafio.R
import com.example.myapplication.Model.Filme

class PessoaAdapter(private val filmes: List<Filme>) : RecyclerView.Adapter<PessoaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtNome: TextView = itemView.findViewById(R.id.textName)
        val txtDsc: TextView = itemView.findViewById(R.id.txtDesc)
        val txtNota: TextView = itemView.findViewById(R.id.txtNota)
        val LinkVideo: VideoView = itemView.findViewById(R.id.videoView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.txtNome.text = filme.titulo
        holder.txtDsc.text = filme.desc
        holder.txtNota.text = filme.nota
        holder.LinkVideo.setVideoPath(filme.url)
        holder.LinkVideo.start()
    }
}
