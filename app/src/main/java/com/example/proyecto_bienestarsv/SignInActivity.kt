package com.example.proyecto_bienestarsv

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.proyecto_bienestarsv.databinding.ActivitySignInBinding
import com.example.proyecto_bienestarsv.databinding.ActivitySignUpBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class SignInActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding
    private lateinit var  googleSignInClient:GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()


        binding.txtcuenta.setOnClickListener{
            val intent= Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient= GoogleSignIn.getClient(this,gso)

        findViewById<Button>(R.id.btnGoogle).setOnClickListener {
            signInGoogle()
        }


        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()


            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

        binding.tvForgetPassword.setOnClickListener {
            val intent = Intent(this, ForgotPassword::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signInGoogle() {
        val signInIntent=googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private  val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result ->
        if(result.resultCode== Activity.RESULT_OK){
            val task =GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleResults(task)

        }
    }
    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account: GoogleSignInAccount?=task.result
            if(account != null){
                updateUI(account)
            }

        }else{
            Toast.makeText(this,task.exception.toString(),Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential= GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful){
                val intent= Intent(this, MainActivity::class.java)
                startActivity(intent)

            }else{
                Toast.makeText(this, it.exception.toString(),Toast.LENGTH_SHORT).show()
            }
        }

    }
}

