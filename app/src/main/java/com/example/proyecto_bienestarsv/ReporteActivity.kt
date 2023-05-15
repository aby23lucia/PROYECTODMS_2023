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
import java.util.*
import kotlin.collections.ArrayList

class ReporteActivity : AppCompatActivity() {

    private lateinit var btnInsertData: Button
    private lateinit var MembresiaRecyclerView:RecyclerView
    private lateinit var tvLoadingDat:TextView
    private lateinit var membresiaList:ArrayList<MembresiaModel>

    private lateinit var  dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte)

        btnInsertData=findViewById(R.id.btnIngresarMembresia)
        btnInsertData.setOnClickListener{
            val intent=Intent(this,InsertionActivityMembresia::class.java)
            startActivity(intent)
        }

        MembresiaRecyclerView = findViewById(R.id.tvMembresia)
        MembresiaRecyclerView.layoutManager=LinearLayoutManager(this)
        MembresiaRecyclerView.setHasFixedSize(true)
        tvLoadingDat= findViewById(R.id.tvLoadingData)

        membresiaList= arrayListOf<MembresiaModel>()

        getMembresiaData()

    }

    // METODO PARA OBTENER TODA LA DA DATA DE FIREBASE Y MSOTRARLO EN LA VISTA
    private fun getMembresiaData (){

        MembresiaRecyclerView.visibility = View.GONE
        tvLoadingDat.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("Membresias")

        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                membresiaList.clear()
                if (snapshot.exists())
                {
                    for (memSnap in snapshot.children)
                    {
                        val memData = memSnap.getValue(MembresiaModel::class.java)
                        membresiaList.add(memData!!)
                    }
                    val mAdapter = MembresiaAdapter(membresiaList)
                    MembresiaRecyclerView.adapter=mAdapter

                    mAdapter.setOnItemClickListener(object :MembresiaAdapter.onItemnClickListener{
                        override fun onItemClick(position: Int) {
                            val  intent =Intent (this@ReporteActivity,MembresiaDetailsActivity::class.java)

                            intent.putExtra("membresiaID", membresiaList[position].membresiaID)
                            intent.putExtra("nombreCliente", membresiaList[position].nombreCliente)
                            intent.putExtra("telCliente", membresiaList[position].telCliente)
                            intent.putExtra("correoCliente", membresiaList[position].correoCliente)
                            intent.putExtra("duiCliente", membresiaList[position].duiCliente)
                            intent.putExtra("SexoCliente", membresiaList[position].SexoCliente)
                            startActivity(intent)
                        }
                    })

                    MembresiaRecyclerView.visibility = View.VISIBLE
                    tvLoadingDat.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    //=================MENU SUPERIOR=====================================
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main2_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    //=================MENU=================
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
            R.id.action_registro-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,RegistroActivity::class.java)
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

