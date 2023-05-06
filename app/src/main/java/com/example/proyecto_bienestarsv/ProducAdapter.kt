package com.example.proyecto_bienestarsv

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView

class ProducAdapter ( private val producList: ArrayList<ProductoModel>) : RecyclerView.Adapter<ProducAdapter.ViewHolder>(){

    private lateinit var mListener: onItemnClickListener


    interface onItemnClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemnClickListener){
        mListener=clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.emp_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduc = producList[position]
        holder.tvProductoName.text= currentProduc.empNomProducto
    }

    override fun getItemCount(): Int {
        return producList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemnClickListener): RecyclerView.ViewHolder(itemView){

        val tvProductoName: TextView =itemView.findViewById(R.id.tvProducName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}