package id.hikmah.binar.chapter5.day4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hikmah.binar.chapter5.database.Student
import id.hikmah.binar.chapter5.helper.StudentRepo
import id.hikmah.binar.chapter5.service.APIService
import kotlinx.coroutines.launch

class Day4ViewModel(private val studentRepo: StudentRepo) : ViewModel() {

    val nilaiBaru = MutableLiveData<Int>(0)
    val students = MutableLiveData<List<Student>>()

    fun increment() {
        nilaiBaru.value = nilaiBaru.value?.plus(1)
    }

    fun decrement(){
        nilaiBaru.value = nilaiBaru.value?.minus(1)
    }

    //from db room
    fun getData(){
        //spt coroutine
        viewModelScope.launch {
            students.value = studentRepo.getDataStudent()
        }
    }
}