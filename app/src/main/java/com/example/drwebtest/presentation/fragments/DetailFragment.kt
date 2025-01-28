package com.example.drwebtest.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.drwebtest.databinding.FragmentDetailBinding
import com.example.drwebtest.domain.models.InstalledApp
import com.example.drwebtest.presentation.viewmodels.DetailFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private lateinit var app: InstalledApp
    private val viewModel: DetailFragmentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = DetailFragmentArgs.Companion.fromBundle(requireArguments()).installedApp

        viewModel.getChecksum(app.packageName)
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
        loadChecksum()
    }

    @SuppressLint("SetTextI18n")
    private fun initFields() {
        binding.apply {
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

    @SuppressLint("SetTextI18n")
    private fun loadChecksum() {
        lifecycleScope.launch {
            viewModel.checksum.collect { checkSum ->
                binding.detailFragmentAppChecksumText.text = "CheckSum: $checkSum"
            }
        }
    }


}