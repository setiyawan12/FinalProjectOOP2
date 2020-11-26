package com.setiyawan.uas_room.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class Person (
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name : String,
    @ColumnInfo(name = "email")
    var email: String,
    @ColumnInfo(name = "nim")
    val nim : String
)
