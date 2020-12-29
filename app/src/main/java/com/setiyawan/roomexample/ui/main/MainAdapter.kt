package com.setiyawan.roomexample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.setiyawan.roomexample.R
import com.setiyawan.roomexample.data.model.Person

class MainAdapter (private val persons: MutableList<Person>, private val mainAdapterInterface: MainAdapterClick)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(persons[position])

    override fun getItemCount() = persons.size

    fun updateList(updatedPersons: List<Person>) {
        persons.clear()
        persons.addAll(updatedPersons)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val personNameTextView = itemView.findViewById<TextView>(R.id.person_name_textview)
        private val personEmailTextView = itemView.findViewById<TextView>(R.id.person_email_textview)
        private val personNimTextView = itemView.findViewById<TextView>(R.id.person_nim_textview)

        fun bind(person : Person){
            personNameTextView.text = person.name
            personEmailTextView.text = person.email
            personNimTextView.text=person.nim
            itemView.setOnClickListener {
                mainAdapterInterface.onTap(person)
            }
            itemView.setOnLongClickListener {
                mainAdapterInterface.onLongTap(person)
                return@setOnLongClickListener false
            }

        }

    }

}