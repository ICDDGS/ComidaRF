package com.dan.comidarf.application

import android.app.Application
import com.dan.comidarf.data.ComidaRepository
import com.dan.comidarf.data.remote.RetrofitHelper

class ComidaRFApp: Application() {

    private val retrofit by lazy {
        RetrofitHelper().getRetrofit()
    }

    val repository by lazy {
        ComidaRepository(retrofit)
    }
}