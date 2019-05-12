package com.workshop.androidarchitecturecomponents.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_profile")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "first_name") var firstName: String? = null,
    var lastName: String? = null,
    var birthDay: Date? = null,
    @Embedded var address: AddressEntity? = null
)