package id.hikmah.binar.chapter5.day4

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hikmah.binar.chapter5.database.Student
import id.hikmah.binar.chapter5.helper.StudentRepo
import id.hikmah.binar.chapter5.model.CarItem
import id.hikmah.binar.chapter5.service.APIService
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Day4ViewModel(private val studentRepo: StudentRepo, private val apiService: APIService) : ViewModel() {

    val nilaiBaru = MutableLiveData<Int>(0)
    val students = MutableLiveData<List<Student>>() //digunakan untuk menampung list student yang didapat dari repo
    val addData = MutableLiveData<Boolean>()
    val cars = MutableLiveData<List<CarItem>>()

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

    fun addData(){
        viewModelScope.launch {
            val student = Student(null, "nama saya", "email saya")
            studentRepo.insertStudent(student)
            addData.value = true
        }
    }

    fun getDataFromNetwork(){
        apiService.getAllCar().enqueue(object : Callback<List<CarItem>>{
            override fun onResponse(call: Call<List<CarItem>>, response: Response<List<CarItem>>) {
                if (response.isSuccessful){
                    cars.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<CarItem>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}