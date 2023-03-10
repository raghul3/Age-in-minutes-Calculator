package com.example.lifecalcy

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btndatepicker : Button = findViewById(R.id.btndatepicker)

        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btndatepicker.setOnClickListener {
            clickDatepicker()

        }


    }
    private fun clickDatepicker(){
        val mycalendar = Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this,
            { view, selectedYear, selectedMonth, selectedDayofmonth ->
                Toast.makeText(
                    this, "Year was $selectedYear,Month was ${selectedMonth + 1}" +
                            ",Day of month was $selectedDayofmonth", Toast.LENGTH_LONG
                ).show()

                val selectedDate = "$selectedDayofmonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text=selectedDate


                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let { val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    currentDate?.let {   val currentDateInMinutes = currentDate.time/ 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes


                        tvAgeInMinutes?.text = differenceInMinutes.toString() }


                   }






            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()


    }

}
