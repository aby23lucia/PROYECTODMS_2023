package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var etEmpNameClient:EditText   //Nombre
private lateinit var etEmpContact: EditText     //telefono
private lateinit var etEmpDirecClient: EditText //Direccion
private lateinit var etEmpMail: EditText        //Email
private lateinit var etEmpFeIngres: EditText   //fecha
//private lateinit var etEmpFeIngres2: EditText   //fecha

private lateinit var btnSavePedido: Button
private lateinit var dbRef: DatabaseReference


class InsertionActivityPedidos : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_pedidos)

         etEmpNameClient = findViewById(R.id.etEmpNameClient)
        etEmpContact = findViewById(R.id.etEmpContact)
        etEmpDirecClient = findViewById(R.id.etEmpDirecClient)
        etEmpMail = findViewById(R.id.etEmpMail)
        etEmpFeIngres = findViewById(R.id.etEmpFeIngres)
        //etEmpFeIngres2 = findViewById(R.id.etEmpFeIngres2)
        btnSavePedido=findViewById(R.id.btnSavePedido)

        dbRef= FirebaseDatabase.getInstance().getReference("Pedidos")

        btnSavePedido.setOnClickListener{
            SavePedidosData()
        }
    }
    private fun SavePedidosData(){

        //Obtenemos los datos desde los Edidtext

        val NombreCliente = etEmpNameClient.text.toString()
        val ContactoCliente = etEmpContact.text.toString()
        val DireccionCliente = etEmpDirecClient.text.toString()
        val CorreoCliente = etEmpMail.text.toString()
        val Pedido_Fecha = etEmpFeIngres.text.toString()
       // val Pedido_Fecha2 = etEmpFeIngres2.text.toString()

        //Validamos los Editext-para que no esten vacios

        if (NombreCliente.isEmpty()){
            etEmpNameClient.error="Ingrese un nombre"
        }
        if (ContactoCliente.isEmpty()){
            etEmpContact.error="Ingrese un contacto celular"
        }
        if (DireccionCliente.isEmpty()){
            etEmpDirecClient.error="Ingrese una Direccion"
        }
        if (CorreoCliente.isEmpty()){
            etEmpMail.error="Ingrese un correo"
        }
        if (Pedido_Fecha.isEmpty()){
            etEmpFeIngres.error="Ingrese una Fecha"
        }
       // if (Pedido_Fecha2.isEmpty()){
         //   etEmpFeIngres2.error="Ingrese una Fecha"


        val pedidoid = dbRef.push().key!!

        val pedidos = PedidoModel(pedidoid,NombreCliente,ContactoCliente,DireccionCliente,CorreoCliente,Pedido_Fecha)

        if(NombreCliente.isEmpty() ||ContactoCliente.isEmpty() || DireccionCliente.isEmpty() ||CorreoCliente.isEmpty()||Pedido_Fecha.isEmpty())
        {
            Toast.makeText(this,"Debere rellenar todos los datos",Toast.LENGTH_LONG).show()
        }else
        {
            dbRef.child(pedidoid).setValue(pedidos)
                .addOnCompleteListener{
                    Toast.makeText(this,"Datos regirstrados",Toast.LENGTH_LONG).show()

                    etEmpNameClient.text.clear()
                    etEmpContact.text.clear()
                    etEmpDirecClient.text.clear()
                    etEmpMail.text.clear()
                    etEmpFeIngres.text.clear()
                   // etEmpFeIngres2.text.clear()

                    val intent = Intent(this, RegistroActivity::class.java)
                    finish()

                }.addOnFailureListener{ err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }
    }

}



