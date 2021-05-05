package com.tohandesign.spendingtrackingapp.Database

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        holder.itemView.itemIcon.setImageResource(
                when(currentSpending.type){
                    0 -> R.drawable.ic_shopping
                    1 -> R.drawable.ic_receipt
                    2 -> R.drawable.ic_home
                    else -> R.drawable.ic_shopping
                }
        )

        holder.itemView.itemIcon.setColorFilter(ContextCompat.getColor(holder.itemView.context,
        when(currentSpending.type) {
            0 -> R.color.custom_blue
            1 -> R.color.custom_ping
            2 -> R.color.custom_rose
            else -> R.color.custom_blue
        }
        ), android.graphics.PorterDuff.Mode.SRC_IN)


    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val description = view.findViewById<TextView>(R.id.itemDesc)
        val cost = view.findViewById<TextView>(R.id.itemCount)
        val icon = view.findViewById<ImageView>(R.id.itemIcon)
    }

    fun setData(spendings: List<Spending>){
        this.spendingList = spendings
        notifyDataSetChanged()
    }


}