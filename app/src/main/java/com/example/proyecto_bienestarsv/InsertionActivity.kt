package com.example.proyecto_bienestarsv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var etEmpNombre:EditText
private lateinit var etEmpCantidad:EditText
private lateinit var etEmpMarca:EditText
private lateinit var etEmpProveedor:EditText
private lateinit var etEmpDescripcion:EditText
private lateinit var btnIngresar:Button

private lateinit var dbRef: DatabaseReference


class InsertionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion)

        etEmpNombre=findViewById(R.id.etNomProducto)
        etEmpCantidad=findViewById(R.id.etCantidad)
        etEmpMarca=findViewById(R.id.etMarca)
        etEmpProveedor=findViewById(R.id.etProveedor)
        etEmpDescripcion=findViewById(R.id.etDescripcion)
        btnIngresar=findViewById(R.id.btnSave)

        dbRef=FirebaseDatabase.getInstance().getReference("Productos")

        btnIngresar.setOnClickListener {
            saveProductData()
        }
    }

    private fun saveProductData(){

        val empNombre= etEmpNombre.text.toString()
        val empCantidad= etEmpCantidad.text.toString()
        val empMarca= etEmpMarca.text.toString()
        val empProveedor= etEmpProveedor.text.toString()
        val empDescripcion= etEmpDescripcion.text.toString()

        if(empNombre.isEmpty()){
            etEmpNombre.error="Ingrese un nombre"
        }
        if(empCantidad.isEmpty()){
            etEmpCantidad.error="Ingrese una cantidad"
        }
        if(empMarca.isEmpty()){
            etEmpMarca.error="Ingrese una marca"
        }
        if(empProveedor.isEmpty()){
            etEmpProveedor.error="Ingrese un proveedor"
        }
        if(empDescripcion.isEmpty()){
            etEmpDescripcion.error="Ingrese una Descripcion"
        }

        val empId = dbRef.push().key!!

        val producto = ProductoModel(empId, empNombre,empCantidad,empMarca,empProveedor,empDescripcion)

        dbRef.child(empId).setValue(producto)
            .addOnCompleteListener{
                Toast.makeText(this,"Datos Ingresados Correctamente", Toast.LENGTH_LONG).show()

                etEmpNombre.text.clear()
                etEmpCantidad.text.clear()
                etEmpMarca.text.clear()
                etEmpProveedor.text.clear()
                etEmpDescripcion.text.clear()
            }.addOnFailureListener{ err ->
                Toast.makeText(this,"Error ${err.message}", Toast.LENGTH_LONG).show()
            }
    }
}
