package com.example.tvprogramapp.repository

import androidx.paging.PagingData
import com.example.tvprogramapp.dto.Program
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    fun getProgram(): Flow<PagingData<Program>>
}