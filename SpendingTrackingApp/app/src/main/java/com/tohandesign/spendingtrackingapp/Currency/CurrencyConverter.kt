package com.tohandesign.spendingtrackingapp.Currency

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.tohandesign.spendingtrackingapp.Database.Spending
import com.tohandesign.spendingtrackingapp.HomeActivity

class CurrencyConverter(val context: Context) {

    val PREFS_FILENAME = "com.tohandesign.spendingtrackingapp"
    val KEY_BASE = "BASE"
    val KEY_EUR = "EUR"
    val KEY_TRY = "TRY"
    val KEY_USD = "USD"
    val KEY_GBP = "GBP"

    fun convert(base: String, target: String, value: Double): Double {

        val prefences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)
        val baseValueRate = prefences.getFloat(
                when(base){
                    "TRY" -> KEY_TRY
                    "USD" -> KEY_USD
                    "GBP" -> KEY_GBP
                    "EUR" -> KEY_EUR
                    else -> "KEY_EUR"
                }, when(base){
            "TRY" -> 10.0f
            "USD" -> 1.2f
            "GBP" -> 0.9f
            "EUR" -> 1.0f
            else -> 1.0f
        })

        val eurValue = value / baseValueRate

        val targetValueRate = prefences.getFloat(
                when(target){
                    "TRY" -> KEY_TRY
                    "USD" -> KEY_USD
                    "GBP" -> KEY_GBP
                    "EUR" -> KEY_EUR
                    else -> "KEY_EUR"
                }, when(base){
            "TRY" -> 10.0f
            "USD" -> 1.2f
            "GBP" -> 0.9f
            "EUR" -> 1.0f
            else -> 1.0f
        })

        return eurValue*targetValueRate
    }







}