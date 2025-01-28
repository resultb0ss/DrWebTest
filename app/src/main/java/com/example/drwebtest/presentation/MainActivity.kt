package com.example.drwebtest.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.drwebtest.databinding.ActivityMainBinding
import com.example.drwebtest.utilits.getDefaultAlertDialog


class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val permissionLauncherSingle =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {}

        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.QUERY_ALL_PACKAGES
            ) == PackageManager.PERMISSION_GRANTED
        ) {

        } else if (shouldShowRequestPermissionRationale(Manifest.permission.QUERY_ALL_PACKAGES)) {
            getDefaultAlertDialog { permissionLauncherSingle.launch(Manifest.permission.QUERY_ALL_PACKAGES) }
        } else {
            permissionLauncherSingle.launch(Manifest.permission.QUERY_ALL_PACKAGES)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}