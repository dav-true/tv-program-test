package com.example.tvprogramapp.dto

import com.google.gson.annotations.SerializedName


data class ResponseResult<T>(
    val offset: Int,
    val total: Int,
    val hasMore: Int,
    val items: T,

    )