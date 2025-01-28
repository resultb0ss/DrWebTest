package com.example.drwebtest.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drwebtest.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

}