package com.example.task2.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Link (
    var link: String,
    var header: String
): Parcelable