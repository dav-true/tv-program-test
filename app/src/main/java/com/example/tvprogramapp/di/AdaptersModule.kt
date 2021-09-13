package com.example.tvprogramapp.di

import android.content.Context
import com.example.tvprogramapp.adapters.ProgramRecyclerViewAdapter
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val adaptersModule = module {
    factory { provideProgramRecyclerViewAdapter(androidApplication()) }
}


fun provideProgramRecyclerViewAdapter(context: Context) =
    ProgramRecyclerViewAdapter(context)

