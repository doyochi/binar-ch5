package id.hikmah.binar.chapter5.service

import id.hikmah.binar.chapter5.model.CarItem
import retrofit2.Call
import retrofit2.http.GET

interface APIService {

    // untuk mendapatkan list semua mobil
    @GET("/admin/car")
    fun getAllCar() : Call<List<CarItem>>
}