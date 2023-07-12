package com.example.juegosapp.main.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_USERGAME")
data class UserGame (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "gameName")
    val gameName: String,
    @ColumnInfo(name = "realName")
    val realName: String,
    @ColumnInfo(name = "age")
    val age: Int?

)
