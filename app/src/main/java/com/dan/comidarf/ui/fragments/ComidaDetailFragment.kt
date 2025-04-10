package com.dan.comidarf.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.dan.comidarf.R
import com.dan.comidarf.application.ComidaRFApp
import com.dan.comidarf.data.ComidaRepository
import com.dan.comidarf.databinding.FragmentComidaDetailBinding
import com.dan.comidarf.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch


private const val ARG_COMIDAID = "id"



class ComidaDetailFragment : Fragment() {
    private var _binding: FragmentComidaDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: ComidaRepository

    private var comidaId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            comidaId = it.getString(ARG_COMIDAID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentComidaDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = (requireActivity().application as ComidaRFApp).repository

        lifecycleScope.launch {
            try {
                val comidaDetail = repository.getComidaDetail(comidaId!!)
                binding.apply {
                    tvTitle.text = comidaDetail.title
                    tvLongDesc.text = comidaDetail.longDesc
                    tvOrigin.text = comidaDetail.originCountry
                    tvPrice.text = comidaDetail.price.toString()
                    tvCategory.text = comidaDetail.category
                    Picasso.get().load(comidaDetail.image).into(ivImage)
                }

            }catch (e: Exception){
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

    companion object {

        @JvmStatic
        fun newInstance(id: String) =
            ComidaDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_COMIDAID, id)
                }
            }
    }
}