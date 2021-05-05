package com.tohandesign.spendingtrackingapp.Retrofit

data class CurrencyModel(
    val base: String,
    val date: String,
    val rates: Rates
)