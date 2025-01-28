package com.example.drwebtest.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.drwebtest.InstalledApp
import com.example.drwebtest.databinding.FragmentDetailBinding
import com.example.drwebtest.presentation.viewmodels.DetailFragmentViewModel
import com.example.drwebtest.presentation.viewmodels.DetailViewModelFactory


class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var app: InstalledApp
    private lateinit var viewModel: DetailFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = DetailFragmentArgs.Companion.fromBundle(requireArguments()).installedApp

        val factory = DetailViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[DetailFragmentViewModel::class.java]
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

    @SuppressLint("SetTextI18n")
    private fun initFields() {
        binding.apply {
            detailFragmentAppChecksumText.text = "CheckSum: ${app.appChecksum}"
            detailFragmentAppVersionText.text = "Version: ${app.appVersion}"
            detailFragmentAppNameText.text = app.appName
            detailFragmentAppPackageNameText.text = "Package: ${app.packageName}"

            detailFragmentBackButton.setOnClickListener {
                findNavController().popBackStack()
            }

            detailFragmentStartAppButton.setOnClickListener {
                viewModel.launchAppByPackageName(app.packageName)
            }
        }

    }


}