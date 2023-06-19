package com.example.listadecompras

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "compra")
class Compra {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "compraId")
    var id:Int =0

    @ColumnInfo(name = "compraName")
    var compraName:String?=null

    @ColumnInfo(name = "compraQuantity")
    var quantity:Int=0

    constructor(){}

    constructor(id:Int, compraName:String,quantity:Int){
        this.id = id
        this.compraName = compraName
        this.quantity = quantity

    }
    constructor(compraName: String, quantity: Int){
        this.compraName = compraName
        this.quantity = quantity
    }

}