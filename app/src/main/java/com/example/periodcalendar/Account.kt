package com.example.periodcalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import com.google.firebase.auth.FirebaseAuth

class Account : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        auth = FirebaseAuth.getInstance()

        val ivBack: ImageView = findViewById(R.id.ivBack)
        val btnLogout: Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            auth.signOut()
            Intent(this@Account, Login::class.java).also {intent->
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }

        ivBack.setOnClickListener {
            val intent = Intent(this, Homepage::class.java);
            startActivity(intent)
        }
    }
}