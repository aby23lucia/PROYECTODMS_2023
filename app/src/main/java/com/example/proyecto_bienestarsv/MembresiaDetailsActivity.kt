package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.TextView


class MembresiaDetailsActivity : AppCompatActivity() {

    private lateinit var tvMembresiaID:TextView
    private lateinit var tvMemNameClient: TextView
    private lateinit var tvMemTel: TextView
    private lateinit var tvMemCorreo: TextView
    private lateinit var tvMemDui: TextView
    private lateinit var tvMemSexo: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_membresia_details)

        initView()
        SetValuesToView()
    }

    private fun initView(){
        tvMembresiaID = findViewById(R.id.tvMembresiaID)
        tvMemNameClient = findViewById(R.id.TvMembresiNombreCLI)
        tvMemTel = findViewById(R.id.tvMembresiaTelClI)
        tvMemCorreo = findViewById(R.id.tvMembresiaCorreCLI)
        tvMemDui = findViewById(R.id.tvMembresiaDuiCLI)
        tvMemSexo = findViewById(R.id.tvMembresiaSexoCLI)
    }
    private fun SetValuesToView(){
        tvMembresiaID.text = intent.getStringExtra("membresiaID")
        tvMemNameClient.text=intent.getStringExtra("nombreCliente")
        tvMemTel.text=intent.getStringExtra("telCliente")
        tvMemCorreo.text=intent.getStringExtra("correoCliente")
        tvMemDui.text=intent.getStringExtra("duiCliente")
        tvMemSexo.text=intent.getStringExtra("SexoCliente")
    }

}