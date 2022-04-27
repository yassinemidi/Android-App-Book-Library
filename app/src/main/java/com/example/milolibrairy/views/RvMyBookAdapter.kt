package com.example.milolibrairy.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.models.book
import java.util.ArrayList


class RvMyBookAdapter(val data: ArrayList<book>,val context:Context,val activity: IndexActivity) : RecyclerView.Adapter<RvMyBookAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val title: TextView =view.findViewById<TextView>(R.id.titlemybook)
        val img: ImageView =view.findViewById<ImageView>(R.id.imagemybook)
        val btnFeed:Button=view.findViewById(R.id.give_feedback)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_rcmybook, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val book = data[position]
        holder.title.text=book.title
        holder.img.setImageBitmap(ManageBook(context).getImage(book.image))



        holder.btnFeed.setOnClickListener {

            val action=MyBooksDirections.actionMyBooksToFeedback2(book.id)
            it.findNavController().navigate(action)

        }


        holder.itemView.setOnClickListener {
            val link=book.link
            val i=Intent(Intent.ACTION_VIEW, Uri.parse(link))
            activity.startActivity(i)
        }





    }

    override fun getItemCount(): Int {
        return data.size
    }



}