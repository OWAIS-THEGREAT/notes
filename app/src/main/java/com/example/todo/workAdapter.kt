package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class workAdapter(private val worklist:ArrayList<worklistdataItem>) : RecyclerView.Adapter<workAdapter.myviewholder>() {

    class myviewholder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val workplace = itemView.findViewById<TextView>(R.id.data_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return myviewholder(view)
    }

    override fun getItemCount(): Int {
        return worklist.size
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {
        val workhold = worklist[position]

        holder.workplace.text = workhold.works
    }
}