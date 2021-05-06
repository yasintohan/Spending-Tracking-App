package com.tohandesign.spendingtrackingapp.Database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tohandesign.spendingtrackingapp.Database.Spending

@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spending: Spending)

    @Query("SELECT * FROM spending ORDER BY spendingId DESC")
    fun readAllData(): LiveData<List<Spending>>


    @Query("DELETE FROM spending")
    fun deleteAllSpendings()

    @Delete
    suspend fun deleteSpending(spending: Spending)


}