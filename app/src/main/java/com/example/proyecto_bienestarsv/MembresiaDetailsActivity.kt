package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase


class MembresiaDetailsActivity : AppCompatActivity() {

    private lateinit var tvMembresiaID:TextView
    private lateinit var tvMemNameClient: TextView
    private lateinit var tvMemTel: TextView
    private lateinit var tvMemCorreo: TextView
    private lateinit var tvMemDui: TextView
    private lateinit var tvMemSexo: TextView

    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membresia_details)

        initView()
        SetValuesToView()

        btnUpdate.setOnClickListener {
            OpenUpdateDialog(
                intent.getStringExtra("membresiaID").toString(),
                intent.getStringExtra("nombreCliente").toString()
            )
        }
        btnDelete.setOnClickListener{
            DeleteRegistro(
                intent.getStringExtra("membresiaID").toString()
            )
        }
    }

   //================== Eliminar el registro metodo============
    private fun DeleteRegistro(
        id: String
    ){
        val dbref=FirebaseDatabase.getInstance().getReference("Membresias").child(id)
        val mtask = dbref.removeValue()

        mtask.addOnSuccessListener {
            Toast.makeText(this,"Registro eliminado correctamete",Toast.LENGTH_LONG).show()

            val intent = Intent(this,ReporteActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{error->
            Toast.makeText(this,"Error en en eleminar el regstro",Toast.LENGTH_LONG).show()
        }
    }





    private fun initView(){
        tvMembresiaID = findViewById(R.id.tvMembresiaID)
        tvMemNameClient = findViewById(R.id.TvMembresiNombreCLI)
        tvMemTel = findViewById(R.id.tvMembresiaTelClI)
        tvMemCorreo = findViewById(R.id.tvMembresiaCorreCLI)
        tvMemDui = findViewById(R.id.tvMembresiaDuiCLI)
        tvMemSexo = findViewById(R.id.tvMembresiaSexoCLI)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }
    private fun SetValuesToView(){
        tvMembresiaID.text = intent.getStringExtra("membresiaID")
        tvMemNameClient.text=intent.getStringExtra("nombreCliente")
        tvMemTel.text=intent.getStringExtra("telCliente")
        tvMemCorreo.text=intent.getStringExtra("correoCliente")
        tvMemDui.text=intent.getStringExtra("duiCliente")
        tvMemSexo.text=intent.getStringExtra("SexoCliente")
    }


    //================================  EDITAR REGISTRO ===============================================
    private fun OpenUpdateDialog(
        membresiaID: String,
        nombreCliente: String
    ){
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_membresia_dialog,null)

        mDialog.setView(mDialogView)

        //INSTANCIAMOS LAS VARIABLES ====================================================

        val etnombreCliente = mDialogView.findViewById<EditText>(R.id.etNombreMem)
        val ettelCliente = mDialogView.findViewById<EditText>(R.id.etTelefonoMem)
        val etcorreoCliente = mDialogView.findViewById<EditText>(R.id.etCorreoMem)
        val etduiCliente = mDialogView.findViewById<EditText>(R.id.etDuiMem)
        val etSexoCliente = mDialogView.findViewById<EditText>(R.id.etSexoMem)
        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        //asignamos los valores a LAS VARIABLES   (set-TEXT=  ===========================================

        etnombreCliente.setText(intent.getStringExtra("nombreCliente").toString())
        ettelCliente.setText(intent.getStringExtra("telCliente").toString())
        etcorreoCliente.setText(intent.getStringExtra("correoCliente").toString())
        etduiCliente.setText(intent.getStringExtra("duiCliente").toString())
        etSexoCliente.setText(intent.getStringExtra("SexoCliente").toString())

        mDialog.setTitle("Actualizando el registro con nombre ======= $nombreCliente =======" )

        val alertDialog = mDialog.create()
        alertDialog.show()


        btnUpdateData.setOnClickListener {
            updateMemData(
                membresiaID,
                etnombreCliente.text.toString(),
                ettelCliente.text.toString(),
                etcorreoCliente.text.toString(),
                etduiCliente.text.toString(),
                etSexoCliente.text.toString()
            )

            Toast.makeText(applicationContext, "Registro Actualizado", Toast.LENGTH_LONG).show()
            //se asigna nuevo valor a los text views
            tvMemNameClient.text = etnombreCliente.text.toString()
            tvMemTel.text = ettelCliente.text.toString()
            tvMemCorreo.text = etcorreoCliente.text.toString()
            tvMemDui.text = etduiCliente.text.toString()
            tvMemSexo.text = etSexoCliente.text.toString()

            alertDialog.dismiss()
        }

    }
    private fun updateMemData(
        id:String,
        name:String,
        tel:String,
        mail:String,
        dui:String,
        sexo:String
    ){
        val dfRef= FirebaseDatabase.getInstance().getReference("Membresias").child(id)
        val memInfo = MembresiaModel(id,name,tel,mail,dui,sexo)
        dfRef.setValue(memInfo)
    }

}