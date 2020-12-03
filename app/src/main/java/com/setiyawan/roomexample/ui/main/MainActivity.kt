package com.setiyawan.roomexample.ui.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setiyawan.roomexample.R
import com.setiyawan.roomexample.data.model.Person
import com.setiyawan.roomexample.extensions.showToast
import com.setiyawan.roomexample.ui.create_and_update.CreateAndUpdateActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), MainAdapterClick {
    private lateinit var fab : FloatingActionButton
    private lateinit var mainRecyclerView : RecyclerView
    private val mainViewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))
        setupUI()
        onFabTap()
        setupAdapter()
        observe()
    }

    private fun observe() {
        mainViewModel.getPersons().observe(this, { handlePersonChange(it) })
        mainViewModel.getState().observe(this, { handleMainState(it) })
    }

    private fun handlePersonChange(persons: List<Person>){
        mainRecyclerView.adapter?.let { adapter ->
            if(adapter is MainAdapter){
                adapter.updateList(persons)
            }
        }
    }

    private fun handleMainState(state: MainState){
        when(state){
            is MainState.ShowToast -> showToast(state.message)
        }
    }

    private fun setupUI(){
        fab = findViewById(R.id.fab)
        mainRecyclerView = findViewById(R.id.person_recyclerview)
    }

    private fun setupAdapter() {
        mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainAdapter(mutableListOf(), this@MainActivity)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        mainViewModel.allPerson()
    }

    override fun onTap(person: Person) {
        startActivity(Intent(this, CreateAndUpdateActivity::class.java).apply {
            putExtra("is_new", false)
            putExtra("id", person.id)
        })
    }

    private fun onFabTap(){
        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateAndUpdateActivity::class.java))
        }
    }

    override fun onLongTap(person: Person) {
        showToast("on long tap")
    }
}