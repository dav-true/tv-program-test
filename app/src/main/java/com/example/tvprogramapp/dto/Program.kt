package com.example.tvprogramapp.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Program(
    val id: Int,
    val channel_name: String,
    val name: String,
    val icon: String,
    val start: String,
    @SerializedName("stop")
    val end: String,
    val description: String
) : Serializable