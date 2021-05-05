package com.tohandesign.spendingtrackingapp.Retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {
    @GET("latest")
    fun getMoviesList(@Query("year") year : String) : Call<CurrencyModel>
}