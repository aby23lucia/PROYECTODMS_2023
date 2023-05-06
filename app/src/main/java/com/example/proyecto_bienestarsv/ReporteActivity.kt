package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ReporteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reporte)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.main2_menu, menu)
        return super.onCreateOptionsMenu(menu)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_product-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Producto", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProductActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_proveedor-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Proveedor", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProveedorActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_registro-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,RegistroActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_user-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "User", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,PerfilActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
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