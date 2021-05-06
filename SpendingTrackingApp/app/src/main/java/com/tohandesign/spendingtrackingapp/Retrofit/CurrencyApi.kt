package com.tohandesign.spendingtrackingapp.Retrofit

import android.content.Context
import android.util.Log
import android.widget.TextView
import com.tohandesign.spendingtrackingapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CurrencyApi(val context: Context)  {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"
    val KEY_BASE = "BASE"
    val KEY_EUR = "EUR"
    val KEY_TRY = "TRY"
    val KEY_USD = "USD"
    val KEY_GBP = "GBP"

    var BaseUrl = "https://api.ratesapi.io/api/"

    fun getData(){

        val prefences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val editor = prefences.edit()

        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CurrencyService::class.java)

        val call = service.getCurrencyList("EUR")
        call.enqueue(object : Callback<CurrencyModel> {
            override fun onResponse(call: Call<CurrencyModel>, response: Response<CurrencyModel>) {
                if (response.code() == 200) {
                    val currencyModel = response.body()!!
                    editor.putString(KEY_BASE, currencyModel.base)
                    editor.putFloat(KEY_EUR, 1.0F)
                    editor.putFloat(KEY_TRY, currencyModel.rates.TRY.toString().toFloat())
                    editor.putFloat(KEY_USD, currencyModel.rates.USD.toString().toFloat())
                    editor.putFloat(KEY_GBP, currencyModel.rates.GBP.toString().toFloat())
                    editor.apply()
                }
            }
            override fun onFailure(call: Call<CurrencyModel>, t: Throwable) {

            }
        })

    }

}