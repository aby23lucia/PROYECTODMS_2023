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

class ProveedorActivity : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var proveeRecyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var proveedorList: ArrayList<ProveedorModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor)
        btnInsertData=findViewById(R.id.btnIngresarProveedor)

        btnInsertData.setOnClickListener {
            val intent=Intent(this,InsertionActivityProveedor::class.java)
            startActivity(intent)
        }

        proveeRecyclerView=findViewById(R.id.tvProveedor)
        proveeRecyclerView.layoutManager= LinearLayoutManager(this)
        proveeRecyclerView.setHasFixedSize(true)
        tvLoadingData=findViewById(R.id.tvLoadingData)

        proveedorList = arrayListOf<ProveedorModel>()

        getProveedorData()

    }

    private fun getProveedorData(){
        proveeRecyclerView.visibility=View.GONE
        tvLoadingData.visibility=View.VISIBLE

        dbRef=FirebaseDatabase.getInstance().getReference("Proveedores")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                proveedorList.clear()
                if(snapshot.exists()){
                    for(proveeSnap in snapshot.children){
                        val proveeData = proveeSnap.getValue(ProveedorModel::class.java)
                        proveedorList.add(proveeData!!)
                    }
                    val mAdapter = ProveedorAdapter(proveedorList)
                    proveeRecyclerView.adapter = mAdapter



                    proveeRecyclerView.visibility= View.VISIBLE
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
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sing_out -> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, SignInActivity::class.java)
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

                    val intent = Intent(this,PerfilAcitivty::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_regresar-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "User", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}