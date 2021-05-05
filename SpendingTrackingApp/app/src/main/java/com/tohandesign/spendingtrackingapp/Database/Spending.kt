package com.tohandesign.spendingtrackingapp.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spending")
data class Spending(


    @ColumnInfo(name="description")
    val description: String,

    @ColumnInfo(name="cost")
    val cost: Double,

    @ColumnInfo(name="type")
    val type: Int,

    @ColumnInfo(name="currency")
    val currency: Int,

    @PrimaryKey(autoGenerate = true)
    val spendingId: Int = 0



) {

}