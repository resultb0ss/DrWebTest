package com.example.drwebtest.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.drwebtest.domain.models.InstalledApp
import com.example.drwebtest.domain.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val repository = MainRepository(application)

    fun getInstalledApps(): Flow<List<InstalledApp>> = flow {
        emit(repository.getInstalledApps())
    }

}