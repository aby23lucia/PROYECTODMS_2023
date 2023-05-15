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


class ProveedorDetailsActivity : AppCompatActivity() {

    private lateinit var tvProveedorID: TextView
    private lateinit var tvProveeedorName: TextView
    private lateinit var tvProveedorDireccion: TextView
    private lateinit var tvProveedorTelefono: TextView
    private lateinit var tvProveedorCorreo: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor_details)

        initView()
        setValuesToViews()

        btnUpdate.setOnClickListener {
            openUpdateDialog(
                intent.getStringExtra("proveedorid").toString(),
                intent.getStringExtra("nombre").toString()
                //intent.getStringExtra("empCantidad").toString(),
                //intent.getStringExtra("empMarca").toString(),
                //intent.getStringExtra("empProveedor").toString(),
                //intent.getStringExtra("empDescrpcion").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteData(
                intent.getStringExtra("proveedorid").toString()
            )
        }

    }

    private fun deleteData(
        id:String
    ){
        dbRef= FirebaseDatabase.getInstance().getReference("Proveedores").child(id)
        val mTask =dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Proveedor Eliminado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ProveedorActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{
                error ->
            Toast.makeText(this, "Error en la eliminacion ${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    private fun initView() {
        tvProveedorID = findViewById(R.id.tvProveedorID)
        tvProveeedorName = findViewById(R.id.tvProveeedorName)
        tvProveedorDireccion = findViewById(R.id.tvProveedorDireccion)
        tvProveedorTelefono = findViewById(R.id.tvProveedorTelefono)
        tvProveedorCorreo = findViewById(R.id.tvProveedorCorreo)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {

        tvProveedorID.text = intent.getStringExtra("proveedorid")
        tvProveeedorName.text = intent.getStringExtra("nombre")
        tvProveedorDireccion.text = intent.getStringExtra("dirrecion")
        tvProveedorTelefono.text = intent.getStringExtra("telefono")
        tvProveedorCorreo.text = intent.getStringExtra("correo")
    }

    private fun openUpdateDialog(
        proveedorid: String,
        nombre: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_proveedor_dialog, null)

        mDialog.setView(mDialogView)

        val etProveedorName = mDialogView.findViewById<EditText>(R.id.etProveedorName)
        val etProveedorDireccion = mDialogView.findViewById<EditText>(R.id.etProveedorDireccion)
        val etProveedorTelefono = mDialogView.findViewById<EditText>(R.id.etProveedorTelefono)
        val etProveedorCorreo = mDialogView.findViewById<EditText>(R.id.etProveeedorCorreo)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etProveedorName.setText(intent.getStringExtra("nombre").toString())
        etProveedorDireccion.setText(intent.getStringExtra("dirrecion").toString())
        etProveedorTelefono.setText(intent.getStringExtra("telefono").toString())
        etProveedorCorreo.setText(intent.getStringExtra("correo").toString())

        mDialog.setTitle("Actualizando $nombre")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnUpdateData.setOnClickListener {
            updateProveedorData(
                proveedorid,
                etProveedorName.text.toString(),
                etProveedorDireccion.text.toString(),
                etProveedorTelefono.text.toString(),
                etProveedorCorreo.text.toString()
            )

            Toast.makeText(
                applicationContext,
                "Los datos fueron Actualizados",
                Toast.LENGTH_LONG
            ).show()

            tvProveeedorName.text = etProveedorName.text.toString()
            tvProveedorDireccion.text = etProveedorDireccion.text.toString()
            tvProveedorTelefono.text = etProveedorTelefono.text.toString()
            tvProveedorCorreo.text = etProveedorCorreo.text.toString()

            alertDialog.dismiss()
        }
}
    private fun updateProveedorData(
        id:String,
        nombre: String,
        direccion: String,
        telefono: String,
        correo: String,
    ) {
        dbRef = FirebaseDatabase.getInstance().getReference("Proveedores").child(id)
        val proveedorInfo = ProductoModel(id, nombre, direccion, telefono, correo)
        dbRef.setValue(proveedorInfo)
    }

}