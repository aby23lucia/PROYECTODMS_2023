package com.example.proyecto_bienestarsv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProveedorAdapter ( private val proveedorList: ArrayList<ProveedorModel>) : RecyclerView.Adapter<ProveedorAdapter.ViewHolder>(){







    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.proveedor_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProvee = proveedorList[position]
        holder.tvProveedorName.text= currentProvee.nombre
    }

    override fun getItemCount(): Int {
        return proveedorList.size
    }
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tvProveedorName: TextView =itemView.findViewById(R.id.tvProveedorName)

        }
    }