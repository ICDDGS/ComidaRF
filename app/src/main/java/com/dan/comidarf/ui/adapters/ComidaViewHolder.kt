package com.dan.comidarf.ui.adapters

import androidx.recyclerview.widget.RecyclerView
import com.dan.comidarf.data.remote.model.ComidaDto
import com.dan.comidarf.databinding.ComidaElementBinding
import com.squareup.picasso.Picasso

class ComidaViewHolder(
    private val binding:ComidaElementBinding,
) : RecyclerView.ViewHolder(binding.root
) {
    fun bind(comida: ComidaDto) {
        binding.tvTitle.text = comida.title
        binding.tvCountryOrigin.text = comida.originCountry

        Picasso.get()
            .load(comida.thumbnail)
            .into(binding.ivThumbnail)

    }

}