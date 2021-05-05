package com.tohandesign.spendingtrackingapp.Database

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tohandesign.spendingtrackingapp.R
import kotlinx.android.synthetic.main.activity_add_spending.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
import org.w3c.dom.Text

class SpendingListAdapter():
    RecyclerView.Adapter<SpendingListAdapter.ViewHolder>() {

    private var spendingList = emptyList<Spending>()


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.recycler_item, p0, false))
    }

    override fun getItemCount(): Int {
        return spendingList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSpending = spendingList[position]
        holder.itemView.itemDesc.text = currentSpending.description.toString()
        holder.itemView.itemCount.text = currentSpending.cost.toString()

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val description = view.findViewById<TextView>(R.id.itemDesc)
        val cost = view.findViewById<TextView>(R.id.itemCount)
    }

    fun setData(spendings: List<Spending>){
        this.spendingList = spendings
        notifyDataSetChanged()
    }


}