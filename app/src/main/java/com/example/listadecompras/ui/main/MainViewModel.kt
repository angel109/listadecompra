package com.example.listadecompras.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.listadecompras.Compra
import com.example.listadecompras.CompraRepository

//import androidx.lifecycle.ViewModel

class MainViewModel (application: Application) : AndroidViewModel(application){

    private val repository:CompraRepository= CompraRepository(application)
    private val allCompra: LiveData<List<Compra>>?
    private val searchResult: MutableLiveData<List<Compra>>

    init {
        allCompra = repository.allCompra
        searchResult = repository.searchResults
    }

    fun insertCompra(compra: Compra) {
        repository.insertCompra(compra)
    }

    fun findCompra(name: String) {
        repository.findCompra(name)
    }

    fun deleteCompra(name: String) {
        repository.deleteCompra(name)
    }

    fun getSearchResults(): MutableLiveData<List<Compra>> {
        return searchResult
    }

    fun getAllCompra(): LiveData<List<Compra>>? {
        return allCompra
    }

}