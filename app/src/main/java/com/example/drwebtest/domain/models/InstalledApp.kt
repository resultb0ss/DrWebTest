package com.example.drwebtest.domain.models

import android.os.Parcel
import android.os.Parcelable


data class InstalledApp(
    val appName: String,
    val appVersion: String,
    val packageName: String,
    val appPath: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(appName)
        dest.writeString(appVersion)
        dest.writeString(packageName)
        dest.writeString(appPath)
    }

    companion object CREATOR : Parcelable.Creator<InstalledApp> {
        override fun createFromParcel(source: Parcel): InstalledApp? {
            return InstalledApp(source)
        }

        override fun newArray(size: Int): Array<out InstalledApp?>? {
            return arrayOfNulls(size)
        }

    }

}