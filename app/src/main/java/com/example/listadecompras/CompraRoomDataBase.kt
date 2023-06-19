package com.example.listadecompras

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Compra::class)], version = 1)
abstract class CompraRoomDataBase: RoomDatabase() {

    abstract  fun compraDao():CompraDao

    companion object{
        private var INSTANCE:CompraRoomDataBase?=null

        internal fun getDataBase(context: Context):CompraRoomDataBase?{
            if (INSTANCE == null){
                synchronized((CompraRoomDataBase::class.java)){
                    if (INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            CompraRoomDataBase::class.java,
                            "compra_database").build()
                    }
                }
            }
            return INSTANCE
        }
    }
}