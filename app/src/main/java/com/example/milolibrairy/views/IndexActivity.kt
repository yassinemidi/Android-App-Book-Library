package com.example.milolibrairy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.databinding.ActivityIndexBinding
import com.example.milolibrairy.models.book
import com.google.android.material.bottomnavigation.BottomNavigationView

class IndexActivity : AppCompatActivity() {
    lateinit var b: Bundle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityIndexBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_index
        )
        val bottom=binding.bottomNavigationView
        val navc=findNavController(R.id.fragmentContainerView)
        bottom.setupWithNavController(navc)

        b= intent.extras!!
        if(b!=null){
            if(b.getInt("type_user")==1){
                Toast.makeText(this, "Connected as Teacher", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Connected as student", Toast.LENGTH_SHORT).show()

            }
        }

    }
}