package com.example.milolibrairy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.milolibrairy.R
import com.example.milolibrairy.databinding.ActivityAdminBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminActivity : AppCompatActivity() {
    lateinit var b: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityAdminBinding = DataBindingUtil.setContentView(this,R.layout.activity_admin)



        val bottom=binding.bottomNavAdmin
        val navc=findNavController(R.id.navhostadmin)
        bottom.setupWithNavController(navc)


        b= intent.extras!!

    }


}