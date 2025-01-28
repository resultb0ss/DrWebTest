package com.example.drwebtest.presentation.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.drwebtest.domain.repositories.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailFragmentViewModel(application: Application, private val repository: DetailRepository) :
    AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    private var _checksum = MutableStateFlow<String?>(null)
    val checksum: StateFlow<String?> = _checksum

    fun launchAppByPackageName(packageName: String) {
        val packageManager: PackageManager = context.packageManager
        try {
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                context.startActivity(intent)
            } else {
                myToast("Приложение не установлено")
            }
        } catch (e: Exception) {
            myToast(e.message.toString())
        }

    }

    fun getChecksum(packageName: String) {
        viewModelScope.launch {
            _checksum.value = repository.calculateCheckSum(packageName)
        }
    }

    private fun myToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}