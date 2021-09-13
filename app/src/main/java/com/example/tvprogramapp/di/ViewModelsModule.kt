package com.example.tvprogramapp.di

import com.example.tvprogramapp.repository.MainRepository
import com.example.tvprogramapp.viewmodels.ProgramViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { provideProgramViewModel(get()) }
}
fun provideProgramViewModel(
    repository: MainRepository
) =
    ProgramViewModel(repository)