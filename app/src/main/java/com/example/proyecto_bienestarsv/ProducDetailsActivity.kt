package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProducDetailsActivity : AppCompatActivity() {
    private lateinit var tvProductoID: TextView
    private lateinit var tvProductosName: TextView
    private lateinit var tvProductosCantidad: TextView
    private lateinit var tvProductosMarca: TextView
    private lateinit var tvProducto_Proveedor: TextView
    private lateinit var tvProductoDescripcion: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_produc_details)

        initView()
        setValuesToViews()
        btnUpdate.setOnClickListener {
            openUpdateDialog(
                        intent.getStringExtra("empId").toString(),
                        intent.getStringExtra("empNomProducto").toString()
                        //intent.getStringExtra("empCantidad").toString(),
                        //intent.getStringExtra("empMarca").toString(),
                        //intent.getStringExtra("empProveedor").toString(),
                        //intent.getStringExtra("empDescrpcion").toString()
            )
        }

        btnDelete.setOnClickListener {
            deleteData(
                intent.getStringExtra("empId").toString()
            )
        }
    }

    private fun deleteData(
        id:String
    ){
        dbRef= FirebaseDatabase.getInstance().getReference("Productos").child(id)
        val mTask =dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this, "Producto Eliminado", Toast.LENGTH_LONG).show()
            val intent = Intent(this, ProductActivity::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener{
            error ->
            Toast.makeText(this, "Error en la eliminacion ${error.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun initView () {
        tvProductoID=findViewById(R.id.tvProductosID)
        tvProductosName=findViewById(R.id.tvProductosName)
        tvProductosMarca=findViewById(R.id.tvProductosMarca)
        tvProductosCantidad=findViewById(R.id.tvProductosCantidad)
        tvProducto_Proveedor=findViewById(R.id.tvProducto_Proveedor)
        tvProductoDescripcion=findViewById(R.id.tvProductoDescripcion)
        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews(){

        tvProductoID.text=intent.getStringExtra("empId")
        tvProductosName.text=intent.getStringExtra("empNomProducto")
        tvProductosCantidad.text=intent.getStringExtra("empCantidad")
        tvProductosMarca.text=intent.getStringExtra("empMarca")
        tvProducto_Proveedor.text=intent.getStringExtra("empProveedor")
        tvProductoDescripcion.text=intent.getStringExtra("empDescrpcion")
    }

    private fun openUpdateDialog(
        empId: String,
        empNomProducto: String
    ) {
        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_dialog, null)

        mDialog.setView(mDialogView)

        val etProductoName = mDialogView.findViewById<EditText>(R.id.etProductoName)
        val etProductoCantidad = mDialogView.findViewById<EditText>(R.id.etProductoCantidad)
        val etProductoMarca = mDialogView.findViewById<EditText>(R.id.etProductoMarca)
        val etetProducto_proveedor = mDialogView.findViewById<EditText>(R.id.etProducto_proveedor)
        val etProductoDescripcion = mDialogView.findViewById<EditText>(R.id.etProductoDescripcion)

        val btnUpdateData = mDialogView.findViewById<Button>(R.id.btnUpdateData)

        etProductoName.setText(intent.getStringExtra("empNomProducto").toString())
        etProductoCantidad.setText(intent.getStringExtra("empCantidad").toString())
        etProductoMarca.setText(intent.getStringExtra("empMarca").toString())
        etetProducto_proveedor.setText(intent.getStringExtra("empProveedor").toString())
        etProductoDescripcion.setText(intent.getStringExtra("empDescrpcion").toString())

        mDialog.setTitle("Actualizando $empNomProducto")

        val alertDialog = mDialog.create()
        alertDialog.show()


            btnUpdateData.setOnClickListener {
                updateProductoData(
                    empId,
                    etProductoName.text.toString(),
                    etProductoCantidad.text.toString(),
                    etProductoMarca.text.toString(),
                    etetProducto_proveedor.text.toString(),
                    etProductoDescripcion.text.toString()
                )

                Toast.makeText(
                    applicationContext,
                    "Los datos fueron Actualizados",
                    Toast.LENGTH_LONG
                ).show()

                tvProductosName.text = etProductoName.text.toString()
                tvProductosCantidad.text = etProductoCantidad.text.toString()
                tvProductosMarca.text = etProductoMarca.text.toString()
                tvProducto_Proveedor.text = etetProducto_proveedor.text.toString()
                tvProductoDescripcion.text = etProductoDescripcion.text.toString()

                alertDialog.dismiss()
            }


    }

    private fun updateProductoData(
        id:String,
        nombre: String,
        cantidad: String,
        marca: String,
        proveedor: String,
        descripcion: String
    ) {
            dbRef = FirebaseDatabase.getInstance().getReference("Productos").child(id)
            val productoInfo = ProductoModel(id, nombre, cantidad, marca, proveedor, descripcion)
            dbRef.setValue(productoInfo)
    }
}