package com.example.proyecto_bienestarsv

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.proyecto_bienestarsv.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignUpActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    private lateinit var binding:ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set view Binding
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.textView2.setOnClickListener{
            val intent= Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSign.setOnClickListener {
            val email=binding.etEmail.text.toString()
            val password=binding.etPassword.text.toString()
            val confirmPass=binding.etconfirmPassword.text.toString()

            if(email.isNotEmpty()&& password.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (password == confirmPass) {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent= Intent(this,MainActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                        }
                    }

                } else {
                    Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"Empty fields are not allowed!!",Toast.LENGTH_SHORT).show()
            }
           /* if(checkAllField()){
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    //if succesful account is created
                    if(it.isSuccessful){
                        auth.signOut()
                        Toast.makeText(this,"Account created successfully!", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        //account not create
                        Log.e("error", it.exception.toString())
                    }
                }
            }*/

        }

    }

    private fun checkAllField():Boolean{
        val email=binding.etEmail.text.toString()
        if(binding.etEmail.text.toString()==""){
            binding.textInputLayoutEmail.error="This is requiered field"
            return false
            }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.textInputLayoutEmail.error="Check email format"
            return false
        }
        if(binding.etPassword.text.toString()==""){
            binding.textInputLayoutPassword.error="This is requiered field"
            binding.textInputLayoutPassword.errorIconDrawable=null
            return false
        }
        if(binding.etPassword.length() <= 6){
            binding.textInputLayoutPassword.error="Password should at least"
            binding.textInputLayoutPassword.errorIconDrawable=null
            return false
        }
        if(binding.etconfirmPassword.text.toString()==""){
            binding.textInputLayoutConfirmPassword.error="This is requiered field"
            binding.textInputLayoutConfirmPassword.errorIconDrawable=null
            return false
        }
        if(binding.etPassword.text.toString()!=binding.etconfirmPassword.text.toString()){
            binding.textInputLayoutPassword.error="Password do not match"
            return false
        }
        return false
    }
}