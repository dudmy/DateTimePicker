package net.dudmy.datetimepicker

import android.graphics.drawable.ColorDrawable
import android.support.annotation.ColorInt
import android.util.Log
import android.widget.NumberPicker

private const val TAG = "WidgetUtil"

internal fun NumberPicker.setDividerColor(@ColorInt color: Int) {
    try {
        NumberPicker::class.java.getDeclaredField("mSelectionDivider").apply {
            isAccessible = true
        }.set(this, ColorDrawable(color))
        invalidate()
    } catch (e: Exception) {
        Log.e(TAG, "setDividerColor failed. " + e.message)
    }
}

internal fun NumberPicker.setDividerHeight(height: Int) {
    try {
        NumberPicker::class.java.getDeclaredField("mSelectionDividerHeight").apply {
            isAccessible = true
        }.set(this, height)
        invalidate()
    } catch (e: Exception) {
        Log.e(TAG, "setDividerHeight failed. " + e.message)
    }
}

internal fun NumberPicker.setMaxHeight(height: Int) {
    try {
        NumberPicker::class.java.getDeclaredField("mMaxHeight").apply {
            isAccessible = true
        }.set(this, height)
        invalidate()
    } catch (e: Exception) {
        Log.e(TAG, "setMaxHeight failed. " + e.message)
    }
}