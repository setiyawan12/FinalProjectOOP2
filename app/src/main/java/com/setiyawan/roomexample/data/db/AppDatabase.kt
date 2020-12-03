package com.setiyawan.roomexample.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.setiyawan.roomexample.data.dao.PersonDao
import com.setiyawan.roomexample.data.model.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personDao(): PersonDao
}