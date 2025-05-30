package com.dan.comidarf.data.remote.model

import com.google.gson.annotations.SerializedName

data class ComidaDetailDto(
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("image")
    var image: String? = null,
    @SerializedName("long_desc")
    var longDesc: String? = null,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("origin_country")
    var originCountry: String? = null,
    @SerializedName("price")
    var price: String? = null,
    @SerializedName("video_url")
    val video_url: String


)
