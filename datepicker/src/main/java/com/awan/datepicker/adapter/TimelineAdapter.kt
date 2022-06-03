package com.awan.datepicker.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.awan.datepicker.OnDateSelectedListener
import com.awan.datepicker.R
import com.awan.datepicker.TimelineView
import java.text.DateFormatSymbols
import java.util.*


class TimelineAdapter(private val timelineView: TimelineView,
                      private var selectedPosition: Int) :
    RecyclerView.Adapter<TimelineAdapter.ViewHolder>() {
    private val calendar: Calendar = Calendar.getInstance()
    private var deactivatedDates: Array<Date?>? = null
    private var listener: OnDateSelectedListener? = null
    private var selectedView: View? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.timeline_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        resetCalendar()
        calendar.add(Calendar.DAY_OF_YEAR, position)
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val dayOfWeek: Int = calendar.get(Calendar.DAY_OF_WEEK)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val isDisabled = holder.bind(month, day, dayOfWeek, year, position)
        holder.rootView.setOnClickListener { v ->
            selectedView?.background = null
            if (!isDisabled) {
                v.background = timelineView.resources.getDrawable(R.drawable.background_shape)
                selectedPosition = position
                selectedView = v
                listener?.onDateSelected(year, month, day, dayOfWeek)
            } else {
                listener?.onDisabledDateSelected(
                    year,
                    month,
                    day,
                    dayOfWeek,
                    isDisabled
                )
            }
        }
    }

    private fun resetCalendar() {
        calendar.set(
            timelineView.getYear(), timelineView.getMonth(), timelineView.getDate(),
            1, 0, 0
        )
    }

    /**
     * Set the position of selected date
     * @param selectedPosition active date Position
     */
    fun setSelectedPosition(selectedPosition: Int) {
        this.selectedPosition = selectedPosition
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    fun disableDates(dates: Array<Date?>?) {
        dates?.let{ deactivatedDates = it }
        notifyDataSetChanged()
    }

    fun setDateSelectedListener(listener: OnDateSelectedListener?) {
        this.listener = listener
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val monthView: TextView
        private val dateView: TextView
        private val dayView: TextView
        val rootView: View
        fun bind(month: Int, day: Int, dayOfWeek: Int, year: Int, position: Int): Boolean {
            monthView.setTextColor(timelineView.getMonthTextColor())
            dateView.setTextColor(timelineView.getDateTextColor())
            dayView.setTextColor(timelineView.getDayTextColor())
            dayView.text = WEEK_DAYS[dayOfWeek].toUpperCase(Locale.US)
            monthView.text = MONTH_NAME[month].toUpperCase(Locale.US)
            dateView.text = day.toString()
            if (selectedPosition == position) {
                rootView.background = timelineView.resources.getDrawable(R.drawable.background_shape)
                selectedView = rootView
            } else {
                rootView.background = null
            }
            for (date in deactivatedDates!!) {
                val tempCalendar: Calendar = Calendar.getInstance()
                tempCalendar.time = date
                if (tempCalendar.get(Calendar.DAY_OF_MONTH) === day && tempCalendar.get(Calendar.MONTH) === month && tempCalendar.get(
                        Calendar.YEAR
                    ) === year
                ) {
                    monthView.setTextColor(timelineView.getDisabledDateColor())
                    dateView.setTextColor(timelineView.getDisabledDateColor())
                    dayView.setTextColor(timelineView.getDisabledDateColor())
                    rootView.setBackground(null)
                    return true
                }
            }
            return false
        }

        init {
            monthView = itemView.findViewById(R.id.monthView)
            dateView = itemView.findViewById(R.id.dateView)
            dayView = itemView.findViewById(R.id.dayView)
            rootView = itemView.findViewById(R.id.rootView)
        }
    }

    companion object {
        private const val TAG = "TimelineAdapter"
        private val WEEK_DAYS: Array<String> = DateFormatSymbols.getInstance().getShortWeekdays()
        private val MONTH_NAME: Array<String> = DateFormatSymbols.getInstance().getShortMonths()
    }
}