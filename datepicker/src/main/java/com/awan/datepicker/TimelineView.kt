package com.awan.datepicker

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.Nullable
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awan.datepicker.adapter.TimelineAdapter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimelineView : RecyclerView {
    private var adapter: TimelineAdapter? = null
    private var monthTextColor = 0
    private var dateTextColor = 0
    private var dayTextColor = 0
    private var selectedColor = 0
    var disabledColor = 0

    //    private float monthTextSize, dateTextSize, dayTextSize;
    var year = 0
        private set
    var month = 0
        private set
    var date = 0
        private set

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, @Nullable attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    fun init() {
        year = 1970
        month = 0
        date = 1
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter = TimelineAdapter(this, -1)
        setAdapter(adapter)
    }

    @JvmName("getMonthTextColor1")
    fun getMonthTextColor(): Int {
        return monthTextColor
    }

    @JvmName("setMonthTextColor1")
    fun setMonthTextColor(color: Int) {
        monthTextColor = color
    }

    @JvmName("getDateTextColor1")
    fun getDateTextColor(): Int {
        return dateTextColor
    }

    @JvmName("setDateTextColor1")
    fun setDateTextColor(color: Int) {
        dateTextColor = color
    }

    @JvmName("getDayTextColor1")
    fun getDayTextColor(): Int {
        return dayTextColor
    }

    @JvmName("setDayTextColor1")
    fun setDayTextColor(color: Int) {
        dayTextColor = color
    }

    @JvmName("setDisabledDateColor1")
    fun setDisabledDateColor(color: Int) {
        this.disabledColor = color
    }

    @JvmName("getDisabledDateColor1")
    fun getDisabledDateColor(): Int {
        return disabledColor
    }

    @JvmName("getSelectedColor1")
    fun getSelectedColor(): Int {
        return selectedColor
    }

    @JvmName("setSelectedColor1")
    fun setSelectedColor(color: Int) {
        selectedColor = color
    }

    @JvmName("getYear1")
    fun getYear(): Int {
        return year
    }

    @JvmName("getMonth1")
    fun getMonth(): Int {
        return month
    }

    @JvmName("getDate1")
    fun getDate(): Int {
        return date
    }

    fun setOnDateSelectedListener(listener: OnDateSelectedListener?) {
        adapter!!.setDateSelectedListener(listener)
    }

    fun setInitialDate(year: Int, month: Int, date: Int) {
        this.year = year
        this.month = month
        this.date = date
        invalidate()
    }

    /**
     * Calculates the date position and set the selected background on that date
     * @param activeDate active Date
     */
    fun setActiveDate(activeDate: Calendar) {
        try {
            val initialDate = SimpleDateFormat("yyyy-MM-dd")
                .parse(year.toString() + "-" + (month + 1) + "-" + date)
            val diff = activeDate.time.time - initialDate.time
            val position = (diff / (1000 * 60 * 60 * 24)).toInt()
            adapter!!.setSelectedPosition(position)
            invalidate()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    fun deactivateDates(deactivatedDates: Array<Date?>) {
        adapter!!.disableDates(deactivatedDates)
    }
}
