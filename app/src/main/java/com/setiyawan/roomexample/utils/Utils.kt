package com.setiyawan.roomexample.utils

object Utils {
    fun isValidEmail(email : String) : Boolean = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}