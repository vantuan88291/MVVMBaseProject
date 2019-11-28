package com.tuan88291.mvvmpattern.di

import com.tuan88291.mvvmpattern.view.fragment.homefragment.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mvvmModule = module {
    viewModel { HomeViewModel() }
}