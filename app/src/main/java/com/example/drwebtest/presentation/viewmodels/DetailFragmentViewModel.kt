package com.example.drwebtest.presentation.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import java.lang.Exception

class DetailFragmentViewModel(application: Application) : AndroidViewModel(application) {

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

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

    private fun myToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}