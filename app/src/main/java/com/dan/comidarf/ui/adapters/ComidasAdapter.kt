package com.dan.comidarf.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dan.comidarf.data.remote.model.ComidaDto
import com.dan.comidarf.databinding.ComidaElementBinding

class ComidasAdapter(
    private val comidas: List<ComidaDto>,
    private val onComidaClick: (ComidaDto) -> Unit
) : RecyclerView.Adapter<ComidaViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int
    ): ComidaViewHolder {
        val binding = ComidaElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComidaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ComidaViewHolder,
        position: Int
    ) {
        val comida = comidas[position]
        holder.bind(comida)
        holder.itemView.setOnClickListener {
            onComidaClick(comida)
        }
    }

    override fun getItemCount(): Int = comidas.size
}