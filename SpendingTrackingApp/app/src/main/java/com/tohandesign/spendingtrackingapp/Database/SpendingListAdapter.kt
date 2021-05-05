package com.tohandesign.spendingtrackingapp.Database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tohandesign.spendingtrackingapp.R
import org.w3c.dom.Text

class SpendingListAdapter(private val spendings : List<Spending>, private val context: Context):
    RecyclerView.Adapter<SpendingListAdapter.ViewHolder>() {




    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, p0, false))
    }

    override fun getItemCount(): Int=spendings.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.description.text = (spendings[position].description)
        holder.cost.text = (spendings[position].cost.toString())

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val description = view.findViewById<TextView>(R.id.itemDesc)
        val cost = view.findViewById<TextView>(R.id.itemCount)
    }



}