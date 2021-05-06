package com.tohandesign.spendingtrackingapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.tohandesign.spendingtrackingapp.Currency.CurrencyConverter
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.Database.SpendingListAdapter
import com.tohandesign.spendingtrackingapp.Database.SpendingViewModel
import com.tohandesign.spendingtrackingapp.Network.NetworkConnection
import com.tohandesign.spendingtrackingapp.Retrofit.CurrencyApi
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity(), View.OnClickListener {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"
    val KEY_NAME = "NAME"
    val KEY_GENDER = "GENDER"


    private lateinit var mSpendingViewModel: SpendingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        CheckConnection()
        setName()
        setRecyclerView("TRY")




    }

    fun CheckConnection(){
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, Observer { isconnected ->
            if (isconnected){
                var currencyApi = CurrencyApi(this)
                currencyApi.getData()
                Log.v("MainActivity", "Connected")
            } else {
                Log.v("MainActivity", "Not Connected")
            }
        })
    }


    private fun setRecyclerView(base: String) {


        val adapter = SpendingListAdapter(this, base)
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        val currencyConverter = CurrencyConverter(this)


        mSpendingViewModel = ViewModelProvider(this).get(SpendingViewModel::class.java)
        mSpendingViewModel.readAllData.observe(this, Observer { spending ->
            adapter.setData(spending)
            var value:Double = 0.0;
            for(spent in spending) {
                value += currencyConverter.convert(spent.currency, base, spent.cost)

            }
            SpendingCost.setText(String.format("%.2f", value) + " " + base)
        })

        findViewById<ExtendedFloatingActionButton>(R.id.add_fab).setOnClickListener {
            val intent = Intent(this, AddSpendingActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
            finish()
        }


    }

    private fun setName() {
        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val nameText: TextView = findViewById<TextView>(R.id.nameText)
        if (prefences.getInt(KEY_GENDER, 0) == 1) {
            nameText.text = "Mr. " + prefences.getString(KEY_NAME, "Yasin")
        } else if (prefences.getInt(KEY_GENDER, 0) == 2) {
            nameText.text = "Mrs. " + prefences.getString(KEY_NAME, "Merve")
        } else {
            nameText.text = prefences.getString(KEY_NAME, "Yasin")
        }
        nameText.setOnClickListener { setNameDialog() }
    }

    private fun setNameDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.change_name_dialog, null)
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView).show()

        val prefences = getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefences.edit()

        val nameText: TextInputEditText = mDialogView.findViewById(R.id.edittext)
        nameText.setText(prefences.getString(KEY_NAME, "Yasin"))
        val radioGroup: RadioGroup = mDialogView.findViewById(R.id.radioGroup)

        mDialogView.findViewById<Button>(R.id.saveButton).setOnClickListener {
            editor.putString(KEY_NAME, nameText.text.toString())
            if (radioGroup.checkedRadioButtonId == R.id.male)
                editor.putInt(KEY_GENDER, 1)
            else if (radioGroup.checkedRadioButtonId == R.id.female)
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

    fun changeBase(view: View) {
        when (view.id) {
            R.id.tlBtn -> {
                tlBtn.setTextColor(resources.getColor(R.color.custom_rose))
                sterlinBtn.setTextColor(resources.getColor(R.color.custom_charch))
                dolarBtn.setTextColor(resources.getColor(R.color.custom_charch))
                euroBtn.setTextColor(resources.getColor(R.color.custom_charch))
                setRecyclerView("TRY")}
            R.id.sterlinBtn -> {
                tlBtn.setTextColor(resources.getColor(R.color.custom_charch))
                sterlinBtn.setTextColor(resources.getColor(R.color.custom_rose))
                dolarBtn.setTextColor(resources.getColor(R.color.custom_charch))
                euroBtn.setTextColor(resources.getColor(R.color.custom_charch))
                setRecyclerView("GBP")}
            R.id.dolarBtn -> {
                tlBtn.setTextColor(resources.getColor(R.color.custom_charch))
                sterlinBtn.setTextColor(resources.getColor(R.color.custom_charch))
                dolarBtn.setTextColor(resources.getColor(R.color.custom_rose))
                euroBtn.setTextColor(resources.getColor(R.color.custom_charch))
                setRecyclerView("USD")}
            R.id.euroBtn -> {
                tlBtn.setTextColor(resources.getColor(R.color.custom_charch))
                sterlinBtn.setTextColor(resources.getColor(R.color.custom_charch))
                dolarBtn.setTextColor(resources.getColor(R.color.custom_charch))
                euroBtn.setTextColor(resources.getColor(R.color.custom_rose))
                setRecyclerView("EUR")}

        }

    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                //kodlar

            }
        }


    }
}
