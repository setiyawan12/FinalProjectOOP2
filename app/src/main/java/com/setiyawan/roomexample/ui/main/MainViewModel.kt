package com.setiyawan.roomexample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setiyawan.roomexample.data.db.AppDatabase
import com.setiyawan.roomexample.data.model.Person

class MainViewModel (private val appDatabase : AppDatabase) : ViewModel() {
    private val state  = MutableLiveData<MainState>()
    private val persons = MutableLiveData<List<Person>>(arrayListOf())

    private fun showMessage(message: String) {
        state.value = MainState.ShowToast(message)
    }

    fun allPerson() {
        persons.postValue(appDatabase.personDao().all())
    }

    fun getState() = state
    fun getPersons() = persons

}

sealed class MainState {
    data class ShowToast(val message : String) : MainState()
}