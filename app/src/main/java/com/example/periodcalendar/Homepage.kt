package com.example.periodcalendar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class Homepage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val nyeriHaid: LinearLayout = findViewById(R.id.cardArtikel_nyeriHaid)
        nyeriHaid.setOnClickListener {
            val intent = Intent(this, NyeriHaid_Article::class.java);
            startActivity(intent)
        }

        val menuCalendar: LinearLayout = findViewById(R.id.menuCalendar)
        menuCalendar.setOnClickListener {
            val intent = Intent(this, LogPeriod::class.java);
            startActivity(intent)
        }

        val menuAkun: LinearLayout = findViewById(R.id.menuAkun)
        menuAkun.setOnClickListener {
            val intent = Intent(this, Account::class.java);
            startActivity(intent)
        }
    }
}