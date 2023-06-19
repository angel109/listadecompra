package com.example.listadecompras.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.Compra
import com.example.listadecompras.R

class CompraListAdapter(private val compraItemLayout:Int): RecyclerView.Adapter<CompraListAdapter.ViewHolder>()  {
    private var CompratList:List<Compra>?=null
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var item: TextView = itemView.findViewById(R.id.compra_row)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item= holder.item
        CompratList.let {
            item.text = it!![position].compraName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(compraItemLayout,parent,false)
        return ViewHolder(view)
    }

    fun setProductList(compra: List<Compra>){
        CompratList=compra
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (CompratList== null) 0 else CompratList!!.size
    }

}