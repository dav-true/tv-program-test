package com.example.tvprogramapp.di

import android.content.Context
import androidx.paging.Pager
import com.example.tvprogramapp.api.ApiService
import com.example.tvprogramapp.dto.Program
import com.example.tvprogramapp.repository.MainRepository
import com.example.tvprogramapp.repository.MainRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<MainRepository> { provideRepository(get()) }
}

fun provideRepository(programPager: Pager<Int, Program>) =
    MainRepositoryImpl(programPager)