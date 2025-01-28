package com.example.drwebtest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.drwebtest.InstalledApp
import com.example.drwebtest.databinding.FragmentDetailBinding


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var app: InstalledApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = DetailFragmentArgs.Companion.fromBundle(requireArguments()).installedApp
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(inflater, container, false)
    }

    override fun onResume() {
        super.onResume()
        initFields()
    }

    private fun initFields() {
        binding.detailFragmentAppNameText.text = app.appName
        binding.detailFragmentAppVersionText.text = app.appVersion
        binding.detailFragmentAppChecksumText.text = app.appChecksum
        binding.detailFragmentAppPackageNameText.text = app.packageName
    }

}