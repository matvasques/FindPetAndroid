package com.findpet.di

import com.findpet.animallist.view.AnimalRepository
import com.findpet.animallist.view.AnimalViewModel
import com.findpet.home.repository.HomeRepository
import com.findpet.home.view.HomeViewModel
import com.findpet.login.UserRepository
import com.findpet.login.UserViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnimalViewModel() }
    viewModel { UserViewModel() }
    viewModel { HomeViewModel() }
}

val repositoryModule = module {
    single { AnimalRepository() }
    single { HomeRepository() }
    single { UserRepository() }
}