package com.tohandesign.spendingtrackingapp.Database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "spending")
data class Spending(


    @ColumnInfo(name="description")
    val description: String,

    @ColumnInfo(name="cost")
    val cost: Double,

    @ColumnInfo(name="type")
    val type: Int,

    @ColumnInfo(name="currency")
    val currency: String,

    @PrimaryKey(autoGenerate = true)
    val spendingId: Int = 0



) : Parcelable