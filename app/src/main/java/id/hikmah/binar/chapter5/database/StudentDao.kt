package id.hikmah.binar.chapter5.database

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface StudentDao {
    @Query("SELECT * FROM Student")
    fun getAllStudent() : List<Student>

    @Query("SELECT * FROM Student WHERE nama = :name AND email = :email LIMIT 1")
    fun getRegisteredStudent(name: String, email: String) : List<Student>

    @Insert(onConflict = REPLACE)
    fun insertStudent(student: Student) : Long

    @Update
    fun updateStudent(student: Student) : Int

    @Delete
    fun deleteStudent(student: Student) : Int
}