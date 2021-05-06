package com.tohandesign.spendingtrackingapp.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpendingViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Spending>>
    private val repository: SpendingRepository

    init {
        val spendingDao = SpendingRoomDB.getDatabase(application).SpendingDao()
        repository = SpendingRepository(spendingDao)
        readAllData = repository.readAllData
    }

    fun addSpending(spending: Spending) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSpending(spending)
        }
    }

    fun deleteSpending(spending: Spending) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSpending(spending)
        }
    }

}