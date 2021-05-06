package com.tohandesign.spendingtrackingapp.Database

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tohandesign.spendingtrackingapp.Currency.CurrencyConverter
import com.tohandesign.spendingtrackingapp.Fragments.DeleteFragmentDirections
import com.tohandesign.spendingtrackingapp.Fragments.MainFragmentDirections
import com.tohandesign.spendingtrackingapp.R
import com.tohandesign.spendingtrackingapp.databinding.RecyclerItemBinding



class SpendingListAdapter(val context: Context, val base: String):
    RecyclerView.Adapter<SpendingListAdapter.MyViewHolder>() {

    private var spendingList = emptyList<Spending>()

    class MyViewHolder(val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        return MyViewHolder(RecyclerItemBinding.inflate(LayoutInflater.from(p0.context), p0, false))
    }

    override fun getItemCount(): Int {
        return spendingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currencyConverter = CurrencyConverter(context)

        val currentSpending = spendingList[position]
        holder.binding.itemDesc.text = currentSpending.description.toString()
        holder.binding.itemCount.text = String.format("%.2f", currencyConverter.convert(currentSpending.currency, base, currentSpending.cost)) + " " +  base
        holder.binding.itemIcon.setImageResource(
                when(currentSpending.type){
                    0 -> R.drawable.ic_shopping
                    1 -> R.drawable.ic_receipt
                    2 -> R.drawable.ic_home
                    else -> R.drawable.ic_shopping
                }
        )

        holder.binding.itemIcon.setColorFilter(ContextCompat.getColor(holder.itemView.context,
        when(currentSpending.type) {
            0 -> R.color.custom_blue
            1 -> R.color.custom_ping
            2 -> R.color.custom_rose
            else -> R.color.custom_blue
        }
        ), android.graphics.PorterDuff.Mode.SRC_IN)

        holder.binding.recyclerItem.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToDeleteFragment(currentSpending)
            holder.itemView.findNavController().navigate(action)
        }


    }

    fun setData(spendings: List<Spending>){
        this.spendingList = spendings
        notifyDataSetChanged()
    }


}