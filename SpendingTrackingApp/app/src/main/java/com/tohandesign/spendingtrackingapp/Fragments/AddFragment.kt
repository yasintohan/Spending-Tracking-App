package com.tohandesign.spendingtrackingapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.Database.SpendingViewModel
import com.tohandesign.spendingtrackingapp.HomeActivity
import com.tohandesign.spendingtrackingapp.R
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {

    lateinit var v: View

    private lateinit var mSpendingViewModel: SpendingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        v = inflater.inflate(R.layout.fragment_add, container, false)

        mSpendingViewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)


        v.addButton.setOnClickListener {
            insertDataToDatabase()
        }

        v.backIcon.setOnClickListener{
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        }


        return v
    }

    private fun insertDataToDatabase() {
        val descText: TextInputEditText = v.descriptionEditText
        val costText: TextInputEditText = v.costEditText
        val typeRadioGroup: RadioGroup = v.typeRadioGroup
        val currencyRadioGroup: RadioGroup = v.currencyRadioGroup

        if(inputCheck(descText.text.toString(), costText.text.toString(), typeRadioGroup.checkedRadioButtonId.toString(), currencyRadioGroup.checkedRadioButtonId.toString())) {
            var type: Int = 0
            when (typeRadioGroup.checkedRadioButtonId) {
                R.id.bill -> type = 1
                R.id.rent -> type = 2
                R.id.other -> type = 0
            }
            var currency: String = "USD"
            when (currencyRadioGroup.checkedRadioButtonId) {
                R.id.tl -> currency = "TRY"
                R.id.dolar -> currency = "USD"
                R.id.euro -> currency = "EUR"
                R.id.sterlin -> currency = "GBP"
            }


            val spending: Spending = Spending(descText.text.toString(),costText.text.toString().toDouble(),type, currency)
            mSpendingViewModel.addSpending(spending)
            Toast.makeText(requireContext(), "Succesfully Added", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(description: String, cost: String, type: String, currency: String): Boolean {
        return !(TextUtils.isEmpty(description) && TextUtils.isEmpty(cost) && TextUtils.isEmpty(type) && TextUtils.isEmpty(currency))
    }


}