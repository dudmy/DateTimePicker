package net.dudmy.datetimepicker

interface OnDateTimeChangeListener {

    fun onDateTimeChanged(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int)
}