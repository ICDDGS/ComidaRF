package com.dan.comidarf.data.remote.model

import com.google.gson.annotations.SerializedName

data class ComidaDto (
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("origin_country")
    var originCountry: String? = null,
)