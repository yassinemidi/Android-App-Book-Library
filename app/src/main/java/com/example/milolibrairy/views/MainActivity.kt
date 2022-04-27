package com.example.milolibrairy.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.ManageFeedback
import com.example.milolibrairy.controller.ManageReservation
import com.example.milolibrairy.models.book
import com.example.milolibrairy.models.feedback
import android.graphics.Bitmap

import android.graphics.drawable.BitmapDrawable

import android.graphics.drawable.Drawable
import com.example.milolibrairy.controller.AssetDatabaseOpenHelper


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val adb = AssetDatabaseOpenHelper(this)
        adb.openDatabase()






    }


    fun tobitmap(i:Int):Bitmap{
        var d: Drawable? =getDrawable(i) // the drawable (Captain Obvious, to the rescue!!!)

        val bitmap = (d as BitmapDrawable).bitmap
        return bitmap
    }
}