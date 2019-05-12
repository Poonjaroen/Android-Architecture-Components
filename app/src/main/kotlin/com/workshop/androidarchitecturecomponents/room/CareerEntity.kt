package com.workshop.androidarchitecturecomponents.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "career")
data class CareerEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "career_id") var id: Int = 0,
    var name: String? = ""
)