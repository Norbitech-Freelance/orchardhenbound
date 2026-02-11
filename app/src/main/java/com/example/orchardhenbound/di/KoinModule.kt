package com.example.orchardhenbound.di

import androidx.room.Room
import com.example.orchardhenbound.data.local.AppDatabase
import com.example.orchardhenbound.data.repository.RecordsRepository
import com.example.orchardhenbound.data.repository.RecordsRepositoryImpl
import com.example.orchardhenbound.presentation.game.GameViewModel
import com.example.orchardhenbound.presentation.records.viewmodel.RecordsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "orchard_db_v2" // новое имя, чтобы создать чистую БД
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    single { get<AppDatabase>().recordDao() }

    single<RecordsRepository> { RecordsRepositoryImpl(get()) }

    viewModelOf(::GameViewModel)
    viewModelOf(::RecordsViewModel)
}
