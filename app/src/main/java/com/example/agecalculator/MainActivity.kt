package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener {
         clickDataPicker()
        }
    }

    private fun clickDataPicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd =  DatePickerDialog(this,
        { _ , selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this,
                "Year was $selectedYear , month was ${selectedMonth+1} , day of the month was $selectedDayOfMonth",
                Toast.LENGTH_SHORT).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

            theDate?.let {
                val selectedDateInMinutes = theDate.time/ 60000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                currentDate?.let {
                    val currentDateInMinutes = currentDate.time/ 60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    tvAgeInMinutes.text = differenceInMinutes.toString()
                }

            }
        },
        year,
        month,
        day)

        dpd.datePicker.maxDate = System.currentTimeMillis()
        dpd.show()
    }
}