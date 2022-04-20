package id.hikmah.binar.chapter5.helper

import android.content.Context
import id.hikmah.binar.chapter5.database.Student
import id.hikmah.binar.chapter5.database.StudentDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudentRepo(context: Context) {

    private val mDb = StudentDatabase.getInstance(context)

    suspend fun getDataStudent() = withContext(Dispatchers.IO){
        mDb?.studentDao()?.getAllStudent()
    }

    suspend fun updateStudent(student: Student) = withContext(Dispatchers.IO){
        mDb?.studentDao()?.updateStudent(student)
    }

    suspend fun deleteStudent(student: Student) = withContext(Dispatchers.IO){
        mDb?.studentDao()?.deleteStudent(student)
    }
}