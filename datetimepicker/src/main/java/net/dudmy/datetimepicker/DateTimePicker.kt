package net.dudmy.datetimepicker

import android.content.Context
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.NumberPicker
import java.text.SimpleDateFormat
import java.util.*

class DateTimePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), NumberPicker.OnValueChangeListener {

    private var minDate: Long = addWeeks(System.currentTimeMillis(), -1)
    private var maxDate: Long = addWeeks(System.currentTimeMillis(), 1)
    private var onDateTimeChangeListener: OnDateTimeChangeListener? = null

    private val datePicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.date_picker)
    }
    private val hourPicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.hour_picker)
    }
    private val minutePicker: NumberPicker by lazy {
        findViewById<NumberPicker>(R.id.minute_picker)
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.datetime_picker_layout, this)
        initDatePicker()
        initHourPicker()
        initMinutePicker()
    }

    private fun initDatePicker() {
        val daysDiff = getDaysDiff(minDate, maxDate)
        val displayedDates = mutableListOf<String>()
        var date = minDate
        for (it in 0..daysDiff) {
            displayedDates.add(DATE_FORMATTER.format(date))
            date = addDays(date, 1)
        }

        val today = getClearDate(System.currentTimeMillis())
        val currentValue =
            if (today.before(getClearDate(minDate)) || today.after(getClearDate(maxDate))) 0
            else getDaysDiff(minDate, today.time) + 1

        datePicker.apply {
            minValue = 0
            maxValue = daysDiff
            displayedValues = displayedDates.toTypedArray()
            value = currentValue
            wrapSelectorWheel = false
            setOnValueChangedListener(this@DateTimePicker)
        }
    }

    private fun initHourPicker() {
        hourPicker.apply {
            minValue = 0
            maxValue = 23
            value = getCurrentHour()
            wrapSelectorWheel = false
            setOnValueChangedListener(this@DateTimePicker)
        }
    }

    private fun initMinutePicker() {
        minutePicker.apply {
            minValue = 0
            maxValue = 59
            value = getCurrentMinute()
            wrapSelectorWheel = false
            setOnValueChangedListener(this@DateTimePicker)
        }
    }

    /**
     * Sets the minimal date supported by this in milliseconds.
     *
     * @param minDate The minimal supported date.
     */
    fun setMinDate(minDate: Long) {
        this.minDate = minDate
        initDatePicker()
    }

    /**
     * Note: The default minimal date is a week before today.
     *
     * @return The minimal supported date.
     */
    fun getMinDate(): Calendar = Calendar.getInstance().apply { timeInMillis = minDate }

    /**
     * Sets the maximal date supported by this in milliseconds.
     *
     * @param maxDate The maximal supported date.
     */
    fun setMaxDate(maxDate: Long) {
        this.maxDate = maxDate
        initDatePicker()
    }

    /**
     * Note: The default maximal date is a week from today.
     *
     * @return The maximal supported date.
     */
    fun getMaxDate(): Calendar = Calendar.getInstance().apply { timeInMillis = maxDate }

    /**
     * @return The selected year.
     */
    fun getYear(): Int = getSelectedDate().get(Calendar.YEAR)

    /**
     * @return The selected month.
     */
    fun getMonth(): Int = getSelectedDate().get(Calendar.MONTH)

    /**
     * @return The selected day of month.
     */
    fun getDayOfMonth(): Int = getSelectedDate().get(Calendar.DAY_OF_MONTH)

    /**
     * @return The selected hour, in the range (0-23)
     */
    fun getHour(): Int = hourPicker.value

    /**
     * @return The selected minute, in the range (0-59)
     */
    fun getMinute(): Int = minutePicker.value

    fun setOnDateTimeChangeListener(onDateTimeChangeListener: OnDateTimeChangeListener) {
        this.onDateTimeChangeListener = onDateTimeChangeListener
    }

    /**
     * Sets the color of the divider.
     */
    fun setDividerColor(@ColorInt color: Int) {
        datePicker.setDividerColor(color)
        hourPicker.setDividerColor(color)
        minutePicker.setDividerColor(color)
    }

    /**
     * Sets the height of the divider, in pixels.
     */
    fun setDividerHeight(height: Int) {
        datePicker.setDividerHeight(height)
        hourPicker.setDividerHeight(height)
        minutePicker.setDividerHeight(height)
    }

    /**
     * Sets the max height of the DateTimePicker, in pixels.
     */
    fun setMaxHeight(height: Int) {
        datePicker.setMaxHeight(height)
        hourPicker.setMaxHeight(height)
        minutePicker.setMaxHeight(height)
    }

    private fun getSelectedDate(): Calendar = Calendar.getInstance().apply {
        val selectedValue = datePicker.value
        val displayedValue = datePicker.displayedValues[selectedValue]
        time = DATE_FORMATTER.parse(displayedValue)
    }

    override fun onValueChange(picker: NumberPicker?, oldVal: Int, newVal: Int) {
        if (picker == null) return

        onDateTimeChangeListener?.onDateTimeChanged(
            getYear(),
            getMonth(),
            getDayOfMonth(),
            getHour(),
            getMinute()
        )
    }

    companion object {
        private val DATE_FORMATTER = SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG)
    }
}