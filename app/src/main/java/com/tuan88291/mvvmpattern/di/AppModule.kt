package com.tuan88291.mvvmpattern.di

import androidx.room.Room
import com.tuan88291.mvvmpattern.data.local.room.AppDatabase
import com.tuan88291.mvvmpattern.data.local.room.livedata.DBRepository
import com.tuan88291.mvvmpattern.data.local.room.livedata.DBmodel
import com.tuan88291.mvvmpattern.view.fragment.homefragment.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dbModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "user-database").build() }
    single { get<AppDatabase>().queries() }
    single { DBRepository(get()) }
    viewModel { DBmodel(get()) }
}
val mvvmModule = module {
    viewModel { HomeViewModel() }
}