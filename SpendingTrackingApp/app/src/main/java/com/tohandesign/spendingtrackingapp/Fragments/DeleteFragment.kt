package com.tohandesign.spendingtrackingapp.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tohandesign.spendingtrackingapp.R
import kotlinx.android.synthetic.main.fragment_add.view.*
import kotlinx.android.synthetic.main.fragment_delete.view.*
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tohandesign.spendingtrackingapp.Database.SpendingViewModel


class DeleteFragment : Fragment() {

    lateinit var v: View

    private val args by navArgs<DeleteFragmentArgs>()

    private lateinit var mSpendingViewModel: SpendingViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.fragment_delete, container, false)

        mSpendingViewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)

        getCurrentData()

        v.deletebackIcon.setOnClickListener{
            findNavController().navigate(R.id.action_deleteFragment_to_mainFragment)
        }


        v.deleteBtn.setOnClickListener{ deleteAlert() }




        return v
    }

    fun deleteAlert(){

        val builder = AlertDialog.Builder(requireContext())

        with(builder)
        {
            setTitle("Delete Alert")
            setMessage("Are you sure you want to delete it?")
            setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener(function = positiveButtonClick))
            setNeutralButton(android.R.string.no, null)
            show()
        }


    }

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        mSpendingViewModel.deleteSpending(args.currentSpending)
        Toast.makeText(requireContext(),
            "Succesfully Deleted", Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_deleteFragment_to_mainFragment)
    }

    private fun getCurrentData() {

        v.itemDesc.text = args.currentSpending.description.toString()
        v.itemCost.text = String.format("%.2f", args.currentSpending.cost) + " " +  args.currentSpending.currency
        v.itemIcon.setImageResource(
            when(args.currentSpending.type){
                0 -> R.drawable.ic_shopping
                1 -> R.drawable.ic_receipt
                2 -> R.drawable.ic_home
                else -> R.drawable.ic_shopping
            }
        )

        v.itemIcon.setColorFilter(
            ContextCompat.getColor(v.context,
                when(args.currentSpending.type) {
                    0 -> R.color.custom_blue
                    1 -> R.color.custom_ping
                    2 -> R.color.custom_rose
                    else -> R.color.custom_blue
                }), android.graphics.PorterDuff.Mode.SRC_IN)

    }


}