package com.example.milolibrairy.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.models.book
import java.util.ArrayList



class RvUpdateAdapter(val data: ArrayList<book>, val context: Context) : RecyclerView.Adapter<RvUpdateAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val title: TextView =view.findViewById<TextView>(R.id.title_book)
        val category: TextView =view.findViewById<TextView>(R.id.category_book)
        val author: TextView =view.findViewById<TextView>(R.id.author_book)
        val img: ImageView =view.findViewById<ImageView>(R.id.image_book)
        val btn: Button =view.findViewById<Button>(R.id.btn_get)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_rvhome, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val book = data[position]
        holder.title.text=book.title
        holder.category.text=book.category
        holder.author.text=book.author
        holder.img.setImageBitmap(ManageBook(context).getImage(book.image))
        holder.btn.text="Update"

        holder.itemView.setOnClickListener {
            val action=updateBookAdminDirections.actionUpdateBookAdminToBookDaitalsAdmin(book.id)
            it.findNavController().navigate(action)
        }



        holder.btn.setOnClickListener {

            val action=updateBookAdminDirections.actionUpdateBookAdminToFormUpdateAdmin(book.id)
            it.findNavController().navigate(action)
        }






    }

    override fun getItemCount(): Int {
        return data.size
    }


}