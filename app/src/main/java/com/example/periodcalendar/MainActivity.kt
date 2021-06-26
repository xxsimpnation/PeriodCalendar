package com.example.periodcalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val createAccount: TextView = findViewById(R.id.landing)
        createAccount.setOnClickListener {
            val intent = Intent(this, Login::class.java);
            startActivity(intent)
        }
    }
}