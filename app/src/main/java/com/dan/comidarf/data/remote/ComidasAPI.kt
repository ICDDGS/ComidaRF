package com.dan.comidarf.data.remote

import com.dan.comidarf.data.remote.model.ComidaDetailDto
import com.dan.comidarf.data.remote.model.ComidaDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ComidasAPI {

    @GET("comidas/comidas_list")
    suspend fun getComidas(): List<ComidaDto>

    @GET("comidas/comida_detail/{id}")
    suspend fun getComidaDetail(
        @Path("id")
        id: String
    ): ComidaDetailDto

}