package com.tohandesign.spendingtrackingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.Database.SpendingRoomDB
import com.tohandesign.spendingtrackingapp.Database.SpendingViewModel
import kotlinx.android.synthetic.main.activity_add_spending.view.*

class AddSpendingActivity : AppCompatActivity() {


    private lateinit var mSpendingViewModel: SpendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_spending)

        mSpendingViewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)


        findViewById<Button>(R.id.addButton).setOnClickListener {
            insertDataToDatabase()
        }


        }

    private fun insertDataToDatabase() {
        val descText: TextInputEditText = findViewById(R.id.descriptionEditText)
        val costText: TextInputEditText = findViewById(R.id.costEditText)
        val typeRadioGroup: RadioGroup = findViewById(R.id.typeRadioGroup)
        val currencyRadioGroup: RadioGroup = findViewById(R.id.currencyRadioGroup)

        if(inputCheck(descText.text.toString(), costText.text.toString(), typeRadioGroup.checkedRadioButtonId.toString(), currencyRadioGroup.checkedRadioButtonId.toString())) {
            var type: Int = 0
            when (typeRadioGroup.checkedRadioButtonId) {
                R.id.bill -> type = 1
                R.id.rent -> type = 2
                R.id.other -> type = 0
            }
            var currency: Int = 1
            when (currencyRadioGroup.checkedRadioButtonId) {
                R.id.tl -> currency = 1
                R.id.dolar -> currency = 2
                R.id.euro -> currency = 3
                R.id.sterlin -> currency = 4
            }


            val spending:Spending= Spending(descText.text.toString(),costText.text.toString().toDouble(),type, currency)
            mSpendingViewModel.addSpending(spending)
            Toast.makeText(this, "Succesfully Added", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, HomeActivity::class.java)
            startActivityForResult(intent, 1)
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(description: String, cost: String, type: String, currency: String): Boolean {
        return !(TextUtils.isEmpty(description) && TextUtils.isEmpty(cost) && TextUtils.isEmpty(type) && TextUtils.isEmpty(currency))
    }


    }


