package com.example.drwebtest.domain.repositories

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.example.drwebtest.domain.models.InstalledApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(private val context: Context) {

    suspend fun getInstalledApps(): MutableList<InstalledApp> {
        return withContext(Dispatchers.IO) {
            val apps = mutableListOf<InstalledApp>()
            val packageManager = context.packageManager
            val installedPackages =
                packageManager.getInstalledApplications(PackageManager.GET_META_DATA)

            installedPackages.forEach {

                if (it.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                    val appName = packageManager.getApplicationLabel(it).toString()
                    val packageName = it.packageName

                    val packageInfo: PackageInfo = packageManager.getPackageInfo(packageName, 0)
                    val appVersion = packageInfo.versionName

                    val apkPath = it.sourceDir
                    apps.add(
                        InstalledApp(
                            appName,
                            appVersion.toString(), packageName, apkPath
                        )
                    )

                }
            }
            return@withContext apps

        }

    }
}