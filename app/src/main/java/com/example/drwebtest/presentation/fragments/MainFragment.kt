package com.example.drwebtest.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drwebtest.databinding.FragmentMainBinding
import com.example.drwebtest.domain.models.InstalledApp
import com.example.drwebtest.presentation.adapters.MainAdapter
import com.example.drwebtest.presentation.viewmodels.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainAdapter = MainAdapter { app -> toDetailFragment(app) }
    }


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater, container, false)
    }

    override fun onResume() {
        super.onResume()
        setUpRecyclerView()
        observeInstalledApps()

    }

    private fun setUpRecyclerView() {
        binding.mainFragmentRecyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeInstalledApps() {
        lifecycleScope.launch {
            binding.mainFragmentProgressBar.visibility = View.VISIBLE
            viewModel.getInstalledApps().collect { apps ->
                mainAdapter.updateInstalledApps(apps)

                binding.mainFragmentProgressBar.visibility = View.GONE
            }

        }
    }

    private fun toDetailFragment(app: InstalledApp) {
        val action = MainFragmentDirections.Companion.actionMainFragmentToDetailFragment(app)
        findNavController().navigate(action)
    }

}