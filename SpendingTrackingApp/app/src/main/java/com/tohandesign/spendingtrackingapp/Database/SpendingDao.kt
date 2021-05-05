package com.tohandesign.spendingtrackingapp.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tohandesign.spendingtrackingapp.Database.Spending

@Dao
interface SpendingDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spending: Spending)

    @Query("SELECT * FROM spending")
    fun readAllData(): LiveData<List<Spending>>


    @Query("DELETE FROM spending")
    fun deleteAllSpendings()


}