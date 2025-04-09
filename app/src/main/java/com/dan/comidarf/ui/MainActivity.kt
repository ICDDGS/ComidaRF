package com.dan.comidarf.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.dan.comidarf.R
import com.dan.comidarf.data.ComidaRepository
import com.dan.comidarf.data.remote.RetrofitHelper
import com.dan.comidarf.databinding.ActivityMainBinding
import com.dan.comidarf.utils.Constants
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    /*
    private lateinit var retrofit: Retrofit
    private lateinit var repository: ComidaRepository*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
/*
        retrofit = RetrofitHelper().getRetrofit()
        repository = ComidaRepository(retrofit)

        lifecycleScope.launch {
            try {

                /*
                val comidas = repository.getComidas()
                Log.d(Constants.LOGTAG, "onCreate: $comidas")*/

                print("mensaje para separar")

                val comida = repository.getComidaDetail("1006")
                Log.d(Constants.LOGTAG, "onCreate: $comida")

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }


        }*/
    }
}