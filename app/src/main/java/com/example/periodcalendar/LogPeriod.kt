package com.example.periodcalendar

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*

class LogPeriod : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    var day = 0
    var month = 0
    var year = 0

    var savedday = 0
    var savedmonth = 0
    var savedyear = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_period)

        pickdate()
        val btnLogPeriod: Button = findViewById(R.id.btnLogPeriod)
        btnLogPeriod.setOnClickListener {
            val intent = Intent(this, Homepage::class.java);
            startActivity(intent)
        }
    }

    private fun getDateTimeCalendar(){
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickdate(){
        val datelinearyout: LinearLayout = findViewById(R.id.datelinearyout)
        datelinearyout.setOnClickListener{
            getDateTimeCalendar()

            DatePickerDialog(this, this, year, month, day).show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedday = dayOfMonth
        savedmonth = month
        savedyear = year

        getDateTimeCalendar()
        val tvDate: TextView = findViewById(R.id.tvDate)
        tvDate.text = "$savedday-$savedmonth-$savedyear"
    }
}