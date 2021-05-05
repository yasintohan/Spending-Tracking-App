package com.tohandesign.spendingtrackingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.Database.SpendingListAdapter
import com.tohandesign.spendingtrackingapp.Database.SpendingRoomDB
import com.tohandesign.spendingtrackingapp.Database.SpendingViewModel

class HomeActivity : AppCompatActivity()  {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"
    val KEY_NAME = "NAME"
    val KEY_GENDER = "GENDER"


    private lateinit var mSpendingViewModel: SpendingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setName()

        val adapter = SpendingListAdapter()
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager=LinearLayoutManager(this)

        mSpendingViewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)
        mSpendingViewModel.readAllData.observe(this, Observer {spending ->
            adapter.setData(spending)

        })

        findViewById<ExtendedFloatingActionButton>(R.id.add_fab).setOnClickListener{
            val intent = Intent(this, AddSpendingActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }



    }



    fun setName() {
        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val nameText: TextView = findViewById<TextView>(R.id.nameText)
        if(prefences.getInt(KEY_GENDER, 0) == 1) {
            nameText.text = "Mr. " + prefences.getString(KEY_NAME,"Yasin")
        } else if(prefences.getInt(KEY_GENDER, 0) == 2)  {
            nameText.text = "Mrs. " + prefences.getString(KEY_NAME,"Merve")
        } else {
            nameText.text = prefences.getString(KEY_NAME,"Yasin")
        }
        nameText.setOnClickListener { onLogin() }
    }

    fun onLogin() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.change_name_dialog, null)
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView).show()

        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefences.edit()

        val nameText: TextInputEditText = mDialogView.findViewById(R.id.edittext)
        val radioGroup: RadioGroup = mDialogView.findViewById(R.id.radioGroup)

        mDialogView.findViewById<Button>(R.id.saveButton).setOnClickListener {
            editor.putString(KEY_NAME, nameText.text.toString())
            if(radioGroup.checkedRadioButtonId == R.id.male)
                editor.putInt(KEY_GENDER, 1)
            else if(radioGroup.checkedRadioButtonId == R.id.female)
                editor.putInt(KEY_GENDER, 2)
            else
                editor.putInt(KEY_GENDER, 0)
            editor.apply()

            mBuilder.dismiss()
            setName()
        }
        mDialogView.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            mBuilder.dismiss()

        }
    }

    companion object {
        private const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
    }


}

