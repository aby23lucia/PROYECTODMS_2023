package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class RegistroActivity : AppCompatActivity() {

    private lateinit var btnInsertData:Button
    private lateinit var pedidoRecyclerView: RecyclerView
    private lateinit var  tvLoadingData: TextView
    private  lateinit var pedidoList: ArrayList<PedidoModel>
    private lateinit var  dbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        btnInsertData=findViewById(R.id.btnIngresarPedido)
        btnInsertData.setOnClickListener {
            val intent=Intent(this,InsertionActivityPedidos::class.java)
            startActivity(intent)
        }

        pedidoRecyclerView = findViewById(R.id.tvPedido)
        pedidoRecyclerView.layoutManager = LinearLayoutManager(this)
        pedidoRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        pedidoList = arrayListOf<PedidoModel>()

        getPedidoData()
    }


    private fun getPedidoData(){
        pedidoRecyclerView.visibility=View.GONE
        tvLoadingData.visibility=View.VISIBLE
        dbRef=FirebaseDatabase.getInstance().getReference("Pedidos")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                pedidoList.clear()
                if(snapshot.exists()){
                    for(pedidoSnap in snapshot.children){
                        val pedidoData = pedidoSnap.getValue(PedidoModel::class.java)
                        pedidoList.add(pedidoData!!)
                    }
                    val mAdapter = PedidoAdapter(pedidoList)
                    pedidoRecyclerView.adapter = mAdapter



                     mAdapter.setOnItemClickListener(object :PedidoAdapter.onItemnClickListener{
                         override fun onItemClick(position: Int) {
                             val  intent =Intent (this@RegistroActivity,PedidoDetailsActivity::class.java)

                             intent.putExtra("pedidoid", pedidoList[position].pedidoid)
                             intent.putExtra("NombreCLiente", pedidoList[position].NombreCLiente)
                             intent.putExtra("TelefonoCliente", pedidoList[position].TelefonoCliente)
                             intent.putExtra("DireccionCliente", pedidoList[position].DireccionCliente)
                             intent.putExtra("CorreoCliente", pedidoList[position].CorreoCliente)
                             intent.putExtra("FechaPedido", pedidoList[position].FechaPedido)
                             startActivity(intent)

                         }

                     })
                    pedidoRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }









    // ===================MENU==========================================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.main2_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_product-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Producto", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProductActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            R.id.action_proveedor-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Proveedor", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProveedorActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_reporte-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Reporte", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ReporteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_user-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "User", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,PerfilActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_regresar-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Menù", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}