package com.example.milolibrairy.controller

class fVefify {
    fun verifyEmail(str:String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(str).matches()

    }

    fun verifyPassword(str1:String,str2:String): Boolean {
        return str1 == str2
    }

    fun verifyLink(str:String): Boolean {
        return android.util.Patterns.WEB_URL.matcher(str).matches() && str.startsWith("http://")
    }
}