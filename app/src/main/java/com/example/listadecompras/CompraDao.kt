package com.example.listadecompras

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CompraDao {
    @Insert
    fun insertCompra(compra: Compra)

    @Query("SELECT * FROM products WHERE productName= :name")
    fun findCompra(name:String):List<Compra>

    @Query("DELETE * FROM products WHERE productName= :name")
    fun deleteCompra(name: String)

    @Query("SELECT * FROM products ")
    fun getAllCompra(): LiveData<List<Compra>>

}