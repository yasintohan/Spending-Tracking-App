package com.tohandesign.spendingtrackingapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HomeActivity : AppCompatActivity()  {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"
    val KEY_NAME = "NAME"
    val KEY_GENDER = "GENDER"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setName()


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


}

