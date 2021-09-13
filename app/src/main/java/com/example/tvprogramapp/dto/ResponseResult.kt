package com.example.tvprogramapp.dto

import com.google.gson.annotations.SerializedName


data class ResponseResult<T>(
    val offset: Int,
    val total: Int,
    @SerializedName("items_number")
    val itemsNumber: Int,
    val items: T,
    val total_pages: Int,
    val total_results: Int
)