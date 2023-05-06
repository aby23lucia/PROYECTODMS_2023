package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


        menuInflater.inflate(R.menu.main2_menu, menu)
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sing_out -> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Sesion Cerrada", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            R.id.action_product-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Proveedor", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ProveedorActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_reporte-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "Reporte", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,ReporteActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            R.id.action_user-> {
                FirebaseAuth.getInstance().signOut().also {
                    Toast.makeText(this, "User", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this,PerfilAcitivty::class.java)
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