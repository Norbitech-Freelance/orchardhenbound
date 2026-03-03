<<<<<<< HEAD
@file:Suppress("DEPRECATION")

=======
>>>>>>> 9934ae4c0a8beaca4a779d9d471a4b4300bcf9a8
package com.example.orchardhenbound.di

import androidx.room.Room
import com.example.orchardhenbound.data.local.AppDatabase
import com.example.orchardhenbound.data.repository.RecordsRepository
import com.example.orchardhenbound.data.repository.RecordsRepositoryImpl
import com.example.orchardhenbound.presentation.game.GameViewModel
import com.example.orchardhenbound.presentation.records.RecordsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "orchard_db"
        ).build()
    }

    single { get<AppDatabase>().recordDao() }

    single<RecordsRepository> { RecordsRepositoryImpl(get()) }

    viewModel { GameViewModel(get()) }
    viewModel { RecordsViewModel(get()) }
}
