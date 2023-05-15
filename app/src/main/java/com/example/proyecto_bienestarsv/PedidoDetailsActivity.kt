package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class PedidoDetailsActivity : AppCompatActivity() {

    private lateinit var tvPedidoID: TextView
    private lateinit var tvClienteName: TextView
    private lateinit var tvTelefonoCliente: TextView
    private lateinit var tvDireccionCli: TextView
    private lateinit var tvCorreoCliente: TextView
    private lateinit var tvFecha: TextView



    private lateinit var btnDelete: Button
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pedidos_details)

        initView()
        setValuesToViews()

        btnDelete.setOnClickListener {
            deleteData(
                intent.getStringExtra("pedidoid").toString()
            )
        }
    }

    private fun deleteData(
        id:String
    ){
        dbRef= FirebaseDatabase.getInstance().getReference("Pedidos").child(id)
        val mTask =dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Registro Eliminado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, RegistroActivity::class.java)
            finish()

        }.addOnFailureListener{
                error ->
            Toast.makeText(this, "Error al eliminar ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun initView() {
        tvPedidoID = findViewById(R.id.tvPedidoID)
        tvClienteName = findViewById(R.id.tvClienteName)
        tvTelefonoCliente = findViewById(R.id.tvTelefonoCliente)
        tvDireccionCli = findViewById(R.id.tvDireccionCli)
        tvCorreoCliente = findViewById(R.id.tvCorreoCliente)
        tvFecha = findViewById(R.id.tvFecha)



        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {

        tvPedidoID.text = intent.getStringExtra("pedidoid")
        tvClienteName.text = intent.getStringExtra("NombreCLiente")
        tvTelefonoCliente.text = intent.getStringExtra("TelefonoCliente")
        tvDireccionCli.text = intent.getStringExtra("DireccionCliente")
        tvCorreoCliente.text = intent.getStringExtra("CorreoCliente")
        tvFecha.text = intent.getStringExtra("FechaPedido")
    }


}

