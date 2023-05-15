package com.example.proyecto_bienestarsv

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView

class MembresiaAdapter (private val membresiaList: ArrayList<MembresiaModel>) : RecyclerView.Adapter<MembresiaAdapter.ViewHolder>(){

//========================================================================
    private lateinit var mListener: onItemnClickListener

   interface onItemnClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemnClickListener){
        mListener=clickListener
    }
//========================================================================


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.membresia_list_item, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMembresia = membresiaList[position]

        holder.tvMembresiaName.text= currentMembresia.nombreCliente
    }

    override fun getItemCount(): Int {
        return membresiaList.size
    }
    class ViewHolder(itemView: View, clickListener: onItemnClickListener): RecyclerView.ViewHolder(itemView){

        val tvMembresiaName: TextView =itemView.findViewById(R.id.tvMembresiaName)

        init {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }
}