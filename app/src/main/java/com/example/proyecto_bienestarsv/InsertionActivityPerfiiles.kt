package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


private  lateinit var etNombrePerfil:EditText
private  lateinit var etApellidosPerfil:EditText
private  lateinit var etCorreoPerfil:EditText


private  lateinit var btnSaveUser:Button
private  lateinit var dbRef:DatabaseReference

class InsertionActivityPerfiiles : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_perfiiles)

        etNombrePerfil = findViewById(R.id.etNombrePerfil)
        etApellidosPerfil = findViewById(R.id.etApellidosPerfil)
        etCorreoPerfil = findViewById(R.id.etCorreoPerfil)

        btnSaveUser = findViewById(R.id.btnSaveUser)

        dbRef= FirebaseDatabase.getInstance().getReference("Perfiles")

        btnSaveUser.setOnClickListener {
            SaveUser()
        }
    }
    private fun SaveUser(){

        val NombrePerfil= etNombrePerfil.text.toString()
        val ApellidosPerfil= etApellidosPerfil.text.toString()
        val CorreoPerfiles= etCorreoPerfil.text.toString()


        if(NombrePerfil.isEmpty()){
            etNombrePerfil.error="Ingrese un nombre"
        }
        if(ApellidosPerfil.isEmpty()){
            etApellidosPerfil.error="Ingrese una direccion"
        }
        if(CorreoPerfiles.isEmpty()){
            etCorreoPerfil.error="Ingrese un telefono"
        }



        val UserID   = dbRef.push().key!!

        val USERS = PerfilesModel(UserID,NombrePerfil, ApellidosPerfil ,CorreoPerfiles)

        if(NombrePerfil.isEmpty() ||ApellidosPerfil.isEmpty() || CorreoPerfiles.isEmpty() ){
            Toast.makeText(this,"Debera rellenar todos los datos", Toast.LENGTH_LONG).show()
        }else {

            dbRef.child(UserID).setValue(USERS)
                .addOnCompleteListener {
                    Toast.makeText(this, "Datos Ingresados Correctamente", Toast.LENGTH_LONG).show()

                    etNombrePerfil.text.clear()
                    etApellidosPerfil.text.clear()
                    etCorreoPerfil.text.clear()


                    //Redirige al crud, luego de insertar
                    val intent = Intent(this, PerfilActivity::class.java)
                    finish()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

}