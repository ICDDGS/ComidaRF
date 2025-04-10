package com.dan.comidarf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dan.comidarf.R
import com.dan.comidarf.application.ComidaRFApp
import com.dan.comidarf.data.ComidaRepository
import com.dan.comidarf.databinding.FragmentComidaListBinding
import com.dan.comidarf.ui.MainActivity
import com.dan.comidarf.ui.adapters.ComidasAdapter
import com.dan.comidarf.utils.Constants
import kotlinx.coroutines.launch


class ComidaListFragment : Fragment() {

    private var _binding : FragmentComidaListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: ComidaRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentComidaListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = (requireActivity().application as ComidaRFApp).repository

        lifecycleScope.launch {

            try {
                val comidas = repository.getComidas()

                binding.rvComida.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = ComidasAdapter(comidas){ selectedComida ->
                        //Log.d(Constants.LOGTAG, "Clicked: $selectedComida")
                        selectedComida.id?.let {id ->
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(
                                    R.id.fragment_container,
                                    ComidaDetailFragment.newInstance(id)
                                )
                                .addToBackStack(null)
                                .commit()

                        }

                    }

                }


            }catch ( e: Exception){
                e.printStackTrace()
                Toast.makeText(requireActivity(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()

            }finally {
                binding.pbLoading.visibility = View.GONE
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}