package com.example.listadecompras.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadecompras.Compra
import com.example.listadecompras.R
import com.example.listadecompras.databinding.FragmentMainBinding
import java.util.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private var adapter:CompraListAdapter?=null
    //private lateinit var viewModel: MainViewModel
    private val viewModel:MainViewModel by viewModels()
    private var _binding:FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
        return  binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }
    //Clear visual components on UI
    private fun clearFields(){
        binding.compraMsg.text=""
        binding.compraName.setText("")
        binding.compraQuantity.setText("")
    }

    //Adding the Button Listeners
    private fun listenerSetup(){
        binding.addBtn.setOnClickListener {
            val name = binding.compraName.text.toString()
            val quantity = binding.compraQuantity.text.toString()
            if(name != "" && quantity != ""){
                val compra= Compra(name,Integer.parseInt(quantity))
                viewModel.insertCompra(compra)
                clearFields()
            }else{
                binding.compraMsg.text ="Incompleteinformation"
            }
        }

        binding.findBtn.setOnClickListener {
            viewModel.findCompra(binding.compraName.text.toString())
        }

        binding.deleteBtn.setOnClickListener {
            viewModel.deleteCompra(binding.compraName.text.toString())
            clearFields()
        }



    }

    //Adding LiveData Observers
    private fun observerSetup(){
        viewModel.getAllCompra()?.observe(viewLifecycleOwner){
                compra->compra.let{adapter?.setProductList(it)}
        }

        viewModel.getSearchResults().observe(viewLifecycleOwner){
                compra-> compra.let {
            if(it.isNotEmpty()){
                binding.compraMsg.text= String.format(Locale.US,"%d",it[0].id)
                binding.compraName.setText(it[0].compraName)
                binding.compraQuantity.setText(String.format(Locale.US,"%d",it[0].quantity))

            }else{
                binding.compraMsg.text="No Match"
            }
        }
        }


    }

    //Initializing the RecyclerView
    private fun recyclerSetup(){
        adapter = CompraListAdapter(R.layout.compra_list_item)
        binding.compraRecycler.layoutManager = LinearLayoutManager(context)
        binding.compraRecycler.adapter = adapter
    }



}