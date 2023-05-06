package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ProveedorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proveedor)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.main2_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //pantalla producto
            R.id.action_product-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Productos", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProductActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            //pantalla registro
            R.id.action_registro-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,RegistroActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            //pantalla reporte
            R.id.action_reporte-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Reporte", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ReporteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            //pantalla perfil
            R.id.action_user-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "User", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,PerfilActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            //pantalla retorno menu
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