package com.example.milolibrairy.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.ManageReservation
import com.example.milolibrairy.models.book
import com.example.milolibrairy.models.reservation
import java.util.*

class RvListbookAdapter(val data: ArrayList<book>,val context:Context,val activity: IndexActivity) : RecyclerView.Adapter<RvListbookAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val title: TextView =view.findViewById<TextView>(R.id.title_book)
        val category: TextView =view.findViewById<TextView>(R.id.category_book)
        val author: TextView =view.findViewById<TextView>(R.id.author_book)
        val img: ImageView =view.findViewById<ImageView>(R.id.image_book)
        val btn:Button=view.findViewById<Button>(R.id.btn_get)
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

        holder.btn.setOnClickListener {

            val myres=ManageReservation(context).userReservation(activity.b.getInt("id_user"))
            var test=true
            for(r:reservation in myres){
                if(r.id_book==book.id){
                    test=false
                }
            }

            if(test){
                ManageReservation(context).addReservation(reservation(0,book.id,activity.b.getInt("id_user")))
                Toast.makeText(context, book.title+" added to your librairy", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, book.title+" Is already in your librairy", Toast.LENGTH_SHORT).show()

            }
        }


        holder.itemView.setOnClickListener {
            val action=list_booksDirections.actionListBooksToBookDetails3(book.id)
            it.findNavController().navigate(action)
        }



    }

    override fun getItemCount(): Int {
        return data.size
    }


}