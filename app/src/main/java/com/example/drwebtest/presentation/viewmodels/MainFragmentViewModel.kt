package com.example.drwebtest.presentation.viewmodels

import android.app.Application
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.drwebtest.InstalledApp
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileInputStream
import java.security.MessageDigest

class MainFragmentViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val _installedAppsList = MutableLiveData<MutableList<InstalledApp>>()
    val installedAppsList: LiveData<MutableList<InstalledApp>> = _installedAppsList

    fun getInstalledApps() {
        val apps = mutableListOf<InstalledApp>()
        val packageManager = getApplication<Application>().packageManager
        viewModelScope.launch {

            val installedPackages =
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            installedPackages.forEach {
                if (it.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                    val appName = packageManager.getApplicationLabel(it).toString()
                    val packageName = it.packageName

                    val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
                    val appVersion = packageInfo.versionName

                    val apkPath = it.sourceDir
                    val appChecksum = getFileChecksum(apkPath)

                    apps.add(InstalledApp(appName,
                        appVersion.toString(), packageName, appChecksum))
                }
            }

            _installedAppsList.value = apps
        }

    }

    private fun getFileChecksum(filePath: String): String {
        val file = File(filePath)
        val inputStream = FileInputStream(file)
        val buffer = ByteArray(1024)
        val digest = MessageDigest.getInstance("SHA-256")

        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            digest.update(buffer, 0, bytesRead)
        }
        inputStream.close()

        val hashBytes = digest.digest()
        val stringBuilder = StringBuilder()
        hashBytes.forEach { stringBuilder.append(String.format("%02x", it)) }

        return stringBuilder.toString()
    }
}