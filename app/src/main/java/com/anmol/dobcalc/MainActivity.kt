package com.anmol.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
//    set text view as nullable
    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes :TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)


        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
//            This is like pass the parameter
//            '_' for view
            { _,selectedYear, selectedMonth,selectedDayOfMonth ->

                Toast.makeText(this,
                    "Year was $selectedYear, month was ${selectedMonth+1}" + ", day of month was $selectedDayOfMonth",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

//                for calculation day
                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
//                using to calculate how much time tp pass

                val theDate = sdf.parse(selectedDate)
//                for selected date in minutes
                theDate?.let{
                    //                selectedDate in minutes
                    val selectedDateInMinutes = theDate.time / 60000

//                for current time
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

//                    for current date
                    currentDate?.let{
                        val currentDateInMinutes = currentDate.time/60000

//                this gives the b/w the times
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

//                convert long to string
                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                    }


                }


            },
            year,
            month,
            day
        )

//        there are 3.6 milion milisec in one hour multiply that by 24 so get result
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }


}