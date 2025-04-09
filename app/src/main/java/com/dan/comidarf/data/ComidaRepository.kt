package com.dan.comidarf.data

import com.dan.comidarf.data.remote.ComidasAPI
import com.dan.comidarf.data.remote.model.ComidaDetailDto
import com.dan.comidarf.data.remote.model.ComidaDto
import retrofit2.Retrofit

class ComidaRepository (
    private val retrofit: Retrofit,
) {
    private val comidaApi = retrofit.create(ComidasAPI::class.java)

    suspend fun getComidas(): List<ComidaDto> = comidaApi.getComidas()

    suspend fun getComidaDetail(id: String): ComidaDetailDto = comidaApi.getComidaDetail(id)

}