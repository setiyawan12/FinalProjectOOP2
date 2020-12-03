package com.setiyawan.roomexample.ui.main

import com.setiyawan.roomexample.data.model.Person


interface MainAdapterClick {
    fun onTap(person: Person)
    fun onLongTap(person: Person)
}