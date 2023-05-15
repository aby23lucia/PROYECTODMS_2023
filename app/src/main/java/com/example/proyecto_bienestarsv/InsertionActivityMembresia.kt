package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


private lateinit var etMemNameClient: EditText
private lateinit var etMemTel: EditText
private lateinit var etMemCorreo: EditText
private lateinit var etMemDui: EditText
private lateinit var etMemSexo: EditText


private lateinit var btnSaveMembresia: Button
private lateinit var dbRef: DatabaseReference



class InsertionActivityMembresia : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_membresia)

        etMemNameClient  = findViewById(R.id.etMemNameClient)
        etMemTel  = findViewById(R.id.etMemTel)
        etMemCorreo  = findViewById(R.id.etMemCorreo)
        etMemDui  = findViewById(R.id.etMemDui)
        etMemSexo  = findViewById(R.id.etMemSexo)

        btnSaveMembresia=findViewById(R.id.btnSaveMembresia)

        dbRef= FirebaseDatabase.getInstance().getReference("Membresias")

        btnSaveMembresia.setOnClickListener {
            SaveMembresiaData()
        }
    }

    private fun SaveMembresiaData(){

        val nombreCLiente= etMemNameClient.text.toString()
        val telCliente= etMemTel.text.toString()
        val CorreoCliente= etMemCorreo.text.toString()
        val duiCliente= etMemDui.text.toString()
        val sexoCliente= etMemSexo.text.toString()

        if(nombreCLiente.isEmpty()){
            etMemNameClient.error="Ingrese un nombre"
        }
        if(telCliente.isEmpty()){
            etMemTel.error="Ingrese una direccion"
        }
        if(CorreoCliente.isEmpty()){
            etMemCorreo.error="Ingrese un telefono"
        }
        if(duiCliente.isEmpty()){
            etMemDui.error="Ingrese un correo"
        }
        if(sexoCliente.isEmpty()){
            etMemSexo.error="Ingrese un correo"
        }


        val membresiaID = dbRef.push().key!!

        val membresia = MembresiaModel(membresiaID, nombreCLiente,telCliente,CorreoCliente,duiCliente,sexoCliente)

        if(nombreCLiente.isEmpty() ||telCliente.isEmpty() || CorreoCliente.isEmpty() ||duiCliente.isEmpty()||sexoCliente.isEmpty()){
            Toast.makeText(this,"Debera rellenar todos los datos",Toast.LENGTH_LONG).show()
        }else {

            dbRef.child(membresiaID).setValue(membresia)
                .addOnCompleteListener {
                    Toast.makeText(this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show()

                    etMemNameClient.text.clear()
                    etMemTel.text.clear()
                    etMemCorreo.text.clear()
                    etMemDui.text.clear()
                    etMemSexo.text.clear()

                    //Redirige al crud, luego de insertar
                    val intent = Intent(this, ReporteActivity::class.java)
                    finish()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }







}

