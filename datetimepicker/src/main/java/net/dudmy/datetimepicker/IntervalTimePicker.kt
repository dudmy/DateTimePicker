package net.dudmy.datetimepicker

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.widget.NumberPicker
import android.widget.TimePicker

class IntervalTimePicker @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : TimePicker(context, attrs, defStyleAttr, defStyleRes) {

    private val hourPicker: NumberPicker by lazy {
        val identifier = Resources.getSystem().getIdentifier("hour", "id", "android")
        findViewById<NumberPicker>(identifier)
    }
    private val minutePicker: NumberPicker by lazy {
        val identifier = Resources.getSystem().getIdentifier("minute", "id", "android")
        findViewById<NumberPicker>(identifier)
    }

    private var hourInterval: Int = 1
    private var minuteInterval: Int = 1

    init {
        setIs24HourView(true)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val displayedHours = (0..23 step hourInterval).map { it.toString() }
        hourPicker.minValue = 0
        hourPicker.maxValue = displayedHours.size - 1
        hourPicker.displayedValues = displayedHours.toTypedArray()

        val displayedMinutes = (0..59 step minuteInterval).map { it.toString() }
        minutePicker.minValue = 0
        minutePicker.maxValue = displayedMinutes.size - 1
        minutePicker.displayedValues = displayedMinutes.toTypedArray()
    }

    fun setHourInterval(interval: Int) {
        this.hourInterval = interval
    }

    fun setMinuteInterval(interval: Int) {
        this.minuteInterval = interval
    }

    fun setDividerColor(@ColorInt color: Int) {
        hourPicker.setDividerColor(color)
        minutePicker.setDividerColor(color)
    }

    fun setDividerHeight(height: Int) {
        hourPicker.setDividerHeight(height)
        minutePicker.setDividerHeight(height)
    }

    fun setMaxHeight(height: Int) {
        hourPicker.setMaxHeight(height)
        minutePicker.setMaxHeight(height)
    }

    override fun setHour(hour: Int) {
        super.setHour(hour / hourInterval)
    }

    override fun getHour(): Int = super.getHour() * hourInterval

    override fun setCurrentHour(currentHour: Int) {
        super.setCurrentHour(currentHour / hourInterval)
    }

    override fun getCurrentHour(): Int = super.getCurrentHour() * hourInterval

    override fun setMinute(minute: Int) {
        super.setMinute(minute / minuteInterval)
    }

    override fun getMinute(): Int = super.getMinute() * minuteInterval

    override fun setCurrentMinute(currentMinute: Int) {
        super.setCurrentMinute(currentMinute / minuteInterval)
    }

    override fun getCurrentMinute(): Int = super.getCurrentMinute() * minuteInterval
}