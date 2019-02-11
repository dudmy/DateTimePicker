package net.dudmy.datetimepicker

import java.util.*
import java.util.concurrent.TimeUnit

private const val TAG = "DateUtil"

internal fun getDaysDiff(from: Long, to: Long): Int {
    val msDiff = to - from
    val daysDiff = TimeUnit.MILLISECONDS.toDays(msDiff)
    return daysDiff.toInt()
}

internal fun addWeeks(date: Long, amount: Int): Long = add(date, Calendar.WEEK_OF_YEAR, amount)

internal fun addDays(date: Long, amount: Int): Long = add(date, Calendar.DATE, amount)

private fun add(date: Long, field: Int, amount: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    calendar.add(field, amount)
    return calendar.timeInMillis
}

internal fun getClearDate(date: Long): Date {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = date
    calendar.clear(Calendar.HOUR_OF_DAY)
    calendar.clear(Calendar.MINUTE)
    calendar.clear(Calendar.SECOND)
    calendar.clear(Calendar.MILLISECOND)
    return calendar.time
}

internal fun getCurrentHour(): Int = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

internal fun getCurrentMinute(): Int = Calendar.getInstance().get(Calendar.MINUTE)