package id.hikmah.binar.chapter5.service

import id.hikmah.binar.chapter5.model.CarItem
import id.hikmah.binar.chapter5.model.RegisterRequest
import id.hikmah.binar.chapter5.model.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    // untuk mendapatkan list semua mobil
    @GET("/admin/car")
    fun getAllCar() : Call<List<CarItem>>

    @POST("/admin/auth/register")
    fun registerAdmin(@Body request: RegisterRequest) : Call<RegisterResponse>
}