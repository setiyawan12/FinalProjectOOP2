package com.setiyawan.roomexample

import android.app.Application
import androidx.room.Room
import com.setiyawan.roomexample.data.db.AppDatabase
import com.setiyawan.roomexample.ui.create_and_update.CreateUpdateViewModel
import com.setiyawan.roomexample.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApp: Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApp)
            modules(listOf(appDatabase, viewModelModule))
        }
    }
}



val appDatabase = module {
    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "mydb")
            .allowMainThreadQueries().build()
    }
}

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
    viewModel {
        CreateUpdateViewModel(get())
    }
}