package com.example.drwebtest.domain.repositories

import android.app.Application
import android.content.pm.PackageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.security.MessageDigest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    application: Application
) {

    val context = application.applicationContext

    suspend fun calculateCheckSum(packageName: String): String {
        val packageManager = context.packageManager
        val packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        val apkFile = File(packageInfo.applicationInfo?.sourceDir)
        return getFileChecksum(apkFile)
    }

    suspend fun getFileChecksum(file: File): String {
        return withContext(Dispatchers.IO) {

            val md = MessageDigest.getInstance("SHA-256")
            file.inputStream().use { inputStream ->
                val buffer = ByteArray(8192)
                var bytesRead: Int
                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    md.update(buffer, 0, bytesRead)
                }
            }

            return@withContext md.digest().joinToString("") { "%02x".format(it) }
        }
    }
}

