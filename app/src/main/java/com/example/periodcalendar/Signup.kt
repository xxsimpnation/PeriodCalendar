package com.example.periodcalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Signup : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val etPassword: EditText = findViewById(R.id.etPassword)
        val etUsername: EditText = findViewById(R.id.etUsername)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val signUp: Button = findViewById(R.id.btnSignUp)
        signUp.setOnClickListener {
            val email : String = etEmail.text.toString().trim()
            val username : String = etUsername.text.toString().trim()
            val password : String = etPassword.text.toString().trim()

            if(email.isEmpty()){
                etEmail.error = "Email harus diisi"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                etEmail.error = "Email tidak valid"
                etEmail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length < 6){
                etPassword.error = "Password harus lebih dari 6 karakter"
                etPassword.requestFocus()
                return@setOnClickListener
            }

            if(username.isEmpty()){
                etUsername.error = "Username harus diisi"
                etUsername.requestFocus()
                return@setOnClickListener
            }

            registerUser(email, password)
        }
    }
    private fun registerUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@Signup, Homepage::class.java).also {intent->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }

    }
}