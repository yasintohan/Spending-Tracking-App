package com.tohandesign.spendingtrackingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.room.Room
import com.google.android.material.textfield.TextInputEditText
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.Database.SpendingRoomDB

class AddSpendingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_spending)

        val descText: TextInputEditText = findViewById(R.id.descriptionEditText)
        val costText: TextInputEditText = findViewById(R.id.costEditText)
        val typeRadioGroup: RadioGroup = findViewById(R.id.typeRadioGroup)
        val currencyRadioGroup: RadioGroup = findViewById(R.id.currencyRadioGroup)


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




        }

    companion object {
        const val NEW_SPENDING = "new_spending"
        const val NEW_COST = "new_cost"
        const val NEW_TYPE = "new_type"
        const val NEW_CURRENCY = "new_currency"
    }

    }


