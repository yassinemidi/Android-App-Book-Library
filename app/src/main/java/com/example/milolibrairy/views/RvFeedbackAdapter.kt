package com.example.milolibrairy.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.milolibrairy.R
import com.example.milolibrairy.controller.ManageBook
import com.example.milolibrairy.controller.ManageReservation
import com.example.milolibrairy.controller.ManageUser
import com.example.milolibrairy.models.book
import com.example.milolibrairy.models.feedback
import com.example.milolibrairy.models.reservation
import java.util.ArrayList



class RvFeedbackAdapter(val data: ArrayList<feedback>, val context: Context) : RecyclerView.Adapter<RvFeedbackAdapter.MyViewHolder>(){

    class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val username: TextView =view.findViewById<TextView>(R.id.username)
        val note: TextView =view.findViewById<TextView>(R.id.note)
        val coment: TextView =view.findViewById<TextView>(R.id.coment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_rvfeedbacks, parent, false)
        return MyViewHolder(layout)
    }
    override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val fd = data[position]
        val user=ManageUser(context).finduser(fd.id_user)
        if(user!=null){
            holder.username.text=user.nom+" "+user.prenom
        }
        holder.coment.text=fd.comment
        holder.note.text=fd.note.toString()


    }

    override fun getItemCount(): Int {
        return data.size
    }


}