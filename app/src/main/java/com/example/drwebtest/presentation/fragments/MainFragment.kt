package com.example.drwebtest.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drwebtest.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

}