package com.tohandesign.spendingtrackingapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Spending::class], version = 1, exportSchema = false)
abstract class SpendingRoomDB: RoomDatabase(){
    abstract fun SpendingDao(): SpendingDao


    companion object{
        @Volatile
        private  var INSTANCE: SpendingRoomDB? = null

        fun getDatabase(context: Context): SpendingRoomDB{

            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpendingRoomDB::class.java,
                    "spending_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }

    }

}