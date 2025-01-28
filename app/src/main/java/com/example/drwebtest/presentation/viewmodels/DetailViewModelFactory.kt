package com.example.drwebtest.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drwebtest.domain.repositories.DetailRepository

class DetailViewModelFactory(
    private val application: Application,
    private val repository: DetailRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailFragmentViewModel::class.java)) {
            return DetailFragmentViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Неизвестный класс ViewModel")
    }
}
