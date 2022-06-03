package com.awan.datepickertimeline

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.awan.datepicker.DatePickerTimeline
import com.awan.datepicker.OnDateSelectedListener
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datePickerTimeline = findViewById<DatePickerTimeline>(R.id.mydate)

        datePickerTimeline.setInitialDate(2022, 5, 3)

        val date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, 0)
        datePickerTimeline.setActiveDate(date)

        datePickerTimeline.setOnDateSelectedListener(object : OnDateSelectedListener {

            override fun onDateSelected(year: Int, month: Int, day: Int, dayOfWeek: Int) {
                //Do Something
                Log.d("selected", "onDateSelected: $day")
            }

            override fun onDisabledDateSelected(
                year: Int,
                month: Int,
                day: Int,
                dayOfWeek: Int,
                isDisabled: Boolean
            ) {
                Log.d("selected", "onDisabledDateSelected: $day")
            }
        })

        val dates: Array<Date?> = arrayOf(Calendar.getInstance().time)
        //datePickerTimeline.deactivateDates(dates)
    }
}