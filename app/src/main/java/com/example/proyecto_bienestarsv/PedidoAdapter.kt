package com.example.proyecto_bienestarsv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Type
import java.text.FieldPosition

class PedidoAdapter ( private val pedidoList: ArrayList<PedidoModel>) :
    RecyclerView.Adapter<PedidoAdapter.ViewHolder>(){

    private lateinit var mListener: PedidoAdapter.onItemnClickListener

    interface onItemnClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: PedidoAdapter.onItemnClickListener){
        mListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): ViewHolder
    {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.pedido_list_item, parent, false)
        return PedidoAdapter.ViewHolder(itemView, mListener)

    }
    override fun onBindViewHolder(holder:ViewHolder, position: Int)
    {
        val currentPedido = pedidoList[position]
        holder.tvPedidoName.text= currentPedido.FechaPedido
    }


    override fun getItemCount(): Int
    {
        return pedidoList.size
    }

    class ViewHolder( itemView: View,clickListener: onItemnClickListener): RecyclerView.ViewHolder(itemView)
    {
        val tvPedidoName: TextView = itemView.findViewById(R.id.tvPedidoName)

        init
        {
            itemView.setOnClickListener{
                clickListener.onItemClick(adapterPosition)
            }
        }
    }



}
