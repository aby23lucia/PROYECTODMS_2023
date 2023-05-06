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

class ProductActivity : AppCompatActivity() {

    private lateinit var btnInsertData:Button
    private lateinit var producRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var  productosList: ArrayList<ProductoModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        btnInsertData=findViewById(R.id.btnIngresar)

        btnInsertData.setOnClickListener {
            val intent=Intent(this,InsertionActivity::class.java)
            startActivity(intent)
        }

        producRecyclerView=findViewById(R.id.tvProduc)
        producRecyclerView.layoutManager=LinearLayoutManager(this)
        producRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        productosList = arrayListOf<ProductoModel>()

        getProductosData()

    }

    private fun getProductosData(){
        producRecyclerView.visibility=View.GONE
        tvLoadingData.visibility=View.VISIBLE

        dbRef=FirebaseDatabase.getInstance().getReference("Productos")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                productosList.clear()
                if(snapshot.exists()){
                    for(producSnap in snapshot.children){
                        val producData = producSnap.getValue(ProductoModel::class.java)
                        productosList.add(producData!!)
                    }
                    val mAdapter = ProducAdapter(productosList)
                    producRecyclerView.adapter = mAdapter

                    producRecyclerView.visibility= View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.main2_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_proveedor-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Proveedor", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProveedorActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_registro-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,RegistroActivity::class.java)
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
                    Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}