package com.example.testgithuser.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testgithuser.Data.Model.Users
import com.example.testgithuser.R

class AdapterListUsers(private var listitem: List<Users>,
                       private val onItemClick: (Users) -> Unit): RecyclerView.Adapter<AdapterListUsers.ViewHolder> (){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgAvatar: ImageView = view.findViewById(R.id.img_avatar)
        val tvName: TextView = view.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listitem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = listitem.get(position).login
        Glide.with(holder.imgAvatar.context).load(listitem.get(position).avatar_url).into(holder.imgAvatar)

        holder.itemView.setOnClickListener { onItemClick(listitem.get(position)) }
    }
}