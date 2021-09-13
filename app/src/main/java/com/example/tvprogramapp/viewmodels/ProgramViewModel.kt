package com.example.tvprogramapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.repository.MainRepository
import kotlinx.coroutines.flow.Flow


class ProgramViewModel(
    private val repository: MainRepository
) : ViewModel() {


    fun getProgram(): Flow<PagingData<Program>> {
        return repository.getProgram()
    }


}