package com.example.tvprogramapp.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.tvprogramapp.api.ApiService
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.paging.ProgramPagingSource
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(
    private val programPager: Pager<Int, Program>
) : MainRepository {
    override fun getProgram(): Flow<PagingData<Program>> {
        return programPager.flow
    }
}