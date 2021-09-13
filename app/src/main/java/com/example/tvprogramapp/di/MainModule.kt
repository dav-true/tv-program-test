package com.example.tvprogramapp.di

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.tvprogramapp.api.ApiService
import com.example.tvprogramapp.helpers.SharedPreferencesStore
import com.example.tvprogramapp.paging.ProgramPagingSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single { provideSharedPreferencesStore(androidApplication()) }
    single { provideProgramPager(get(), get()) }
}


fun provideSharedPreferencesStore(context: Context) =
    SharedPreferencesStore(context)

fun provideProgramPager(apiService: ApiService, sharedPreferencesStore: SharedPreferencesStore) =
    Pager(
        config = PagingConfig(
            pageSize = 40,
            prefetchDistance = 2,
            enablePlaceholders = false
        ),
        pagingSourceFactory = {
            ProgramPagingSource(
                service = apiService,
                sharedPreferencesStore = sharedPreferencesStore
            )
        }
    )

