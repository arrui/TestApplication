package com.example.testapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val list: MutableList<String>) : RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        Log.d("Arrui", "onCreateViewHolder:")
        return MyHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        Log.d("Arrui", "onBindViewHolder:$position")
        holder.itemView.findViewById<TextView>(R.id.text).run {
            text = "$position"
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class MyHolder(view: View) : RecyclerView.ViewHolder(view)