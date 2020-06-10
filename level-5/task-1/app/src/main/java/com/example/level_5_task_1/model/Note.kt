package com.example.level_5_task_1.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "noteTable")
data class Note(
    var title: String,
    var lastUpdated: Date,
    var text: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
): Parcelable