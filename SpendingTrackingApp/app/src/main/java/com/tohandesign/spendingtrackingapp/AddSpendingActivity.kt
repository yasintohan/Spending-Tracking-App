package com.tohandesign.spendingtrackingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.Database.SpendingRoomDB
import com.tohandesign.spendingtrackingapp.Database.SpendingViewModel

class AddSpendingActivity : AppCompatActivity() {


    private lateinit var mSpendingViewModel: SpendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_spending)

        mSpendingViewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)



/*
        val db:SpendingRoomDB= Room.databaseBuilder(applicationContext,SpendingRoomDB::class.java,"Spending")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
        findViewById<Button>(R.id.addButton).setOnClickListener {
            val description = descText.text.toString()
            Toast.makeText(this, description, Toast.LENGTH_SHORT).show()
            val cost = costText.text.toString()
            val type = typeRadioGroup.checkedRadioButtonId.toString()
            Toast.makeText(this, type, Toast.LENGTH_SHORT).show()
            val currency = currencyRadioGroup.checkedRadioButtonId.toString()
            Toast.makeText(this, currency, Toast.LENGTH_SHORT).show()
            val spending:Spending= Spending(description,123.0,type, currency)
            //db.SpendingDao().insert(spending)
           // startActivity(Intent(this@AddSpendingActivity,HomeActivity::class.java))
           // finish()
        }
*/
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
            val spending:Spending= Spending(descText.text.toString(),123.0,typeRadioGroup.checkedRadioButtonId.toString(), currencyRadioGroup.checkedRadioButtonId.toString())
            mSpendingViewModel.addSpending(spending)
        }
    }

    private fun inputCheck(description: String, cost: String, type: String, currency: String): Boolean {
        return !(TextUtils.isEmpty(description) && TextUtils.isEmpty(cost) && TextUtils.isEmpty(type) && TextUtils.isEmpty(currency))
    }

    companion object {
        const val NEW_SPENDING = "new_spending"
        const val NEW_COST = "new_cost"
        const val NEW_TYPE = "new_type"
        const val NEW_CURRENCY = "new_currency"
    }

    }


