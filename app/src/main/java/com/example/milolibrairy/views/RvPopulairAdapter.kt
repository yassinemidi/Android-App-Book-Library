package com.example.milolibrairy.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.models.book
import java.util.ArrayList


class RvPopulairAdapter(val data: ArrayList<book>,val context: Context) : RecyclerView.Adapter<RvPopulairAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val title: TextView =view.findViewById<TextView>(R.id.titlemybook)
        val img: ImageView =view.findViewById<ImageView>(R.id.imagemybook)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_rvpopulaire, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val book = data[position]
        holder.title.text=book.title
        holder.img.setImageBitmap(ManageBook(context).getImage(book.image))

        holder.itemView.setOnClickListener {
            val action=populaireDirections.actionPopulaireToBookDetails(book.id)
            it.findNavController().navigate(action)
        }






    }

    override fun getItemCount(): Int {
        return data.size
    }



}