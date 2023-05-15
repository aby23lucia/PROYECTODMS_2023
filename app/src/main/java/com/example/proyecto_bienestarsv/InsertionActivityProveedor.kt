package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

        private lateinit var etNomProveedor:EditText
        private lateinit var etDireccion: EditText
        private lateinit var etCorreo: EditText
        private lateinit var etetTelefono: EditText

        private lateinit var btnSaveProveedor: Button
        private lateinit var dbRef: DatabaseReference

class InsertionActivityProveedor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_proveedor)

        etNomProveedor=findViewById(R.id.etNomProveedor)
        etDireccion=findViewById(R.id.etDireccion)
        etetTelefono=findViewById(R.id.etTelefono)
        etCorreo=findViewById(R.id.etCorreo)
        btnSaveProveedor=findViewById(R.id.btnSaveProveedor)

        dbRef= FirebaseDatabase.getInstance().getReference("Proveedores")

        btnSaveProveedor.setOnClickListener {
            saveProductData()
        }
    }

    private fun saveProductData(){

        val proveedor_Nom= etNomProveedor.text.toString()
        val proveedor_Dir= etDireccion.text.toString()
        val proveedor_tel= etetTelefono.text.toString()
        val proveedor_corr= etCorreo.text.toString()

        if(proveedor_Nom.isEmpty()){
            etNomProveedor.error="Ingrese un nombre"
        }
        if(proveedor_Dir.isEmpty()){
            etDireccion.error="Ingrese una direccion"
        }
        if(proveedor_tel.isEmpty()){
            etetTelefono.error="Ingrese un telefono"
        }
        if(proveedor_corr.isEmpty()){
            etCorreo.error="Ingrese un correo"
        }


        val proveedorid = dbRef.push().key!!

        val proveedor = ProveedorModel(proveedorid, proveedor_Nom,proveedor_Dir,proveedor_tel,proveedor_corr)

        if(proveedor_Nom.isEmpty() ||proveedor_Dir.isEmpty() || proveedor_tel.isEmpty() ||proveedor_corr.isEmpty()){
            Toast.makeText(this,"Debere rellenar todos los datos",Toast.LENGTH_LONG).show()
        }else {

            dbRef.child(proveedorid).setValue(proveedor)
                .addOnCompleteListener {
                    Toast.makeText(this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show()

                    etNomProveedor.text.clear()
                    etDireccion.text.clear()
                    etetTelefono.text.clear()
                    etCorreo.text.clear()

                    //Redirige al crud, luego de insertar
                    val intent = Intent(this, ProveedorActivity::class.java)
                    finish()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}