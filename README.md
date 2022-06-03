# DatePickerTimeline

<img src="/app/img.png" alt="My cool logo"/>

# Installation
### Step 1
Add the JitPack repository to your project level build.gradle:
```
maven { url 'https://jitpack.io' }
```

### Step 2
include the following in your app's build.gradle file:
```
dependencies {
  implementation 'com.github.User:Repo:Tag'
}
```

# Usage
Add DatePickerTimeline to your XML like any other view
```
<com.awan.datepicker.DatePickerTimeline
            android:id="@+id/date_picker"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_marginTop="14dp"
            app:dateTextColor="@color/white"
            app:dayTextColor="@color/white"
            app:monthTextColor="@color/white"
            app:disabledColor="@color/white"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent" />
```

#### Properties:
You can use the following properties in your XML to change your DatePickerTimeline colors.
* app:dateTextColor (color) -> default -> Black
* app:dayTextColor (color) -> default -> Black
* app:monthTextColor (color) -> default -> Black
* app:disabledColor (color) -> default -> Grey

### Kotlin
```
val datePickerTimeline = findViewById<DatePickerTimeline>(R.id.mydate)
        datePickerTimeline.setInitialDate(2022, 5, 3)

        val date = Calendar.getInstance()
        date.add(Calendar.DAY_OF_YEAR, 0)
        datePickerTimeline.setActiveDate(date)

        datePickerTimeline.setOnDateSelectedListener(object : OnDateSelectedListener {
            override fun onDateSelected(year: Int, month: Int, day: Int, dayOfWeek: Int) {
                Log.d("selected", "onDateSelected: $day")
            }

            override fun onDisabledDateSelected(year: Int, month: Int, day: Int, dayOfWeek: Int, isDisabled: Boolean) {
                Log.d("selected", "onDisabledDateSelected: $day")
            }
        })

        val dates: Array<Date?> = arrayOf(Calendar.getInstance().time)
        datePickerTimeline.deactivateDates(dates)
```
