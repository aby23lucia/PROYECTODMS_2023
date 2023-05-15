package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clickListener()

    }

     private fun clickListener(){
        val Product=findViewById<ImageView>(R.id.cardproduct)
        val proveedor=findViewById<ImageView>(R.id.cardinventario)
        val registro=findViewById<ImageView>(R.id.cardregistro)
        val reporte=findViewById<ImageView>(R.id.cardreporte)
        val perfil=findViewById<ImageView>(R.id.cardperfil)
        val salir=findViewById<ImageView>(R.id.cardsesion)

        Product.setOnClickListener{
            openProductActivity()
        }

        proveedor.setOnClickListener{
            openProveedorActivity()
        }

        registro.setOnClickListener {
            openRegistroActivity()
        }

        reporte.setOnClickListener {
            openReporteActivity()
        }

        perfil.setOnClickListener {
            openPerfilActivity()
        }

        salir.setOnClickListener {
            openSalirActivity()
        }


    }

    private fun openProductActivity(){

        startActivity(Intent(this@MainActivity, ProductActivity::class.java))
    }

    private fun openProveedorActivity(){
        startActivity(Intent(this@MainActivity,ProveedorActivity::class.java))

    }

    private fun openRegistroActivity(){
        startActivity(Intent(this@MainActivity,RegistroActivity::class.java))

    }

    private fun openReporteActivity(){
        startActivity(Intent(this@MainActivity,ReporteActivity::class.java))
    }

    private fun openPerfilActivity(){
        startActivity(Intent(this@MainActivity,PerfilActivity::class.java))
    }

    private fun openSalirActivity(){
        startActivity(Intent(this@MainActivity,SignUpActivity::class.java))
    }

}