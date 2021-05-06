package com.tohandesign.spendingtrackingapp.Database

import androidx.lifecycle.LiveData

class SpendingRepository(private val spendingDao: SpendingDao) {
    val readAllData: LiveData<List<Spending>> = spendingDao.readAllData()

    suspend fun addSpending(spending: Spending) {
        spendingDao.insert(spending)
    }

    suspend fun deleteSpending(spending: Spending){
        spendingDao.deleteSpending(spending)
    }

}