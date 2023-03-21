package com.example.demo.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.demo.R
import com.example.demo.databinding.FragmentIntroductionBinding
import com.example.demo.network.response.AttractionsItem

class IntroductionFragment(val attractionsItem: AttractionsItem) : Fragment() {

    private lateinit var binding: FragmentIntroductionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntroductionBinding.inflate(LayoutInflater.from(context))
        binding.attractionsItem = attractionsItem
        return binding.root
    }

}