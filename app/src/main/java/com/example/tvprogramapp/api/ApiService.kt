package com.example.tvprogramapp.api

import android.provider.Settings
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.dto.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.programUrl)
    suspend fun getProgram(
        @Query("serial_number") serialNumber: String = Settings.Secure.ANDROID_ID,
        @Query("borderId") borderId: Int,
        @Query("direction") direction: Int,
    ): ResponseResult<List<Program>>
}