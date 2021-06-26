package com.example.periodcalendar

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val etPassword: EditText = findViewById(R.id.etPassword)
        val etEmail: EditText = findViewById(R.id.etEmail)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val email : String = etEmail.text.toString().trim()
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
            loginUser(email, password)
        }

        val signup: TextView = findViewById(R.id.signup)
        signup.setOnClickListener {
            val intent = Intent(this, Signup::class.java);
            startActivity(intent)
        }
    }
    private fun loginUser(email :String, password :String){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    Intent(this@Login, Homepage::class.java).also {intent->
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)
                    }
                }else{
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }

    override fun onStart() {
        super.onStart()
        if (auth.currentUser != null){
            Intent(this@Login, Homepage::class.java).also {intent->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}