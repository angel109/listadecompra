package com.example.listadecompras

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class CompraRepository (application: Application) {
    val searchResults = MutableLiveData<List<Compra>>()
    private var compraDao:CompraDao?
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    val allCompra:LiveData<List<Compra>>?

    init {

        val db:CompraRoomDataBase? = CompraRoomDataBase.getDataBase(application)
        compraDao=db?.compraDao()
        allCompra =compraDao?.getAllCompra()
    }
    fun insertCompra(newCompra:Compra){
        coroutineScope.launch(Dispatchers.IO){
            asyncInsert(newCompra)
        }
    }

    private fun asyncInsert(compra: Compra){
        compraDao?.insertCompra(compra)
    }

    fun deleteCompra(name:String){
        coroutineScope.launch(Dispatchers.IO){
            asyncDelete(name)
        }
    }

    private fun asyncDelete(name:String){
        compraDao?.deleteCompra(name)
    }

    fun findCompra(name:String){
        coroutineScope.launch(Dispatchers.IO){
            searchResults.value = asyncFind(name).await()
        }
    }

    private fun asyncFind(name:String): Deferred<List<Compra>?> =
        coroutineScope.async(Dispatchers.IO) {
            return@async compraDao?.findCompra(name)
        }


}