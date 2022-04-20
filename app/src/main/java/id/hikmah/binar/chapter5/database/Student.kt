package id.hikmah.binar.chapter5.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true) val id : Int?,
    @ColumnInfo(name = "nama") val nama: String,
    @ColumnInfo(name = "email") val email: String
)
