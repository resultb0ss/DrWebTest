package com.example.drwebtest.domain.repositories

import android.app.Application
import android.content.pm.PackageManager
import com.example.drwebtest.domain.models.InstalledApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    application: Application
) {

    val context = application.applicationContext

    suspend fun getInstalledApps(): MutableList<InstalledApp> {
        return withContext(Dispatchers.IO) {
            val apps = mutableListOf<InstalledApp>()
            val packageManager = context.packageManager
            val installedPackages =
                packageManager.getInstalledPackages(PackageManager.GET_META_DATA)

            installedPackages.forEach { packageInfo ->

                val appName =
                    packageManager.getApplicationLabel(packageInfo.applicationInfo!!).toString()
                val packageName = packageInfo.packageName
                val appVersion = packageInfo.versionName

                apps.add(
                    InstalledApp(
                        appName,
                        appVersion.toString(), packageName
                    )
                )
            }
            return@withContext apps
        }
    }
}