package net.dudmy.datetimepickersample

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.dudmy.datetimepicker.DateTimePicker
import net.dudmy.datetimepicker.OnDateTimeChangeListener
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), DialogInterface.OnClickListener,
    OnDateTimeChangeListener {

    private var dateTimePicker: DateTimePicker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_sample.setOnClickListener {
            val pickerView = DateTimePicker(it.context)
            showDateTimePickerDialog(pickerView)
        }

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val minDate = calendar.timeInMillis
        calendar.add(Calendar.YEAR, 1)
        val maxDate = calendar.timeInMillis

        view_sample.apply {
            setMinDate(minDate)
            setMaxDate(maxDate)
            setHourInterval(2)
            setMinuteInterval(15)
            setDividerColor(Color.BLUE)
            setDividerHeight(10)
            setOnDateTimeChangeListener(this@MainActivity)
        }
    }

    private fun showDateTimePickerDialog(pickerView: DateTimePicker) {
        dateTimePicker = pickerView

        AlertDialog.Builder(pickerView.context)
            .setTitle("DateTimePickerDialog")
            .setView(pickerView)
            .setPositiveButton(android.R.string.ok, this)
            .setNegativeButton(android.R.string.cancel, this)
            .create()
            .show()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        val pickerView = dateTimePicker ?: return

        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {
                val text = formatDate(
                    pickerView.getYear(),
                    pickerView.getMonth(),
                    pickerView.getDayOfMonth(),
                    pickerView.getHour(),
                    pickerView.getMinute()
                )
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
            DialogInterface.BUTTON_NEGATIVE -> dialog?.dismiss()
        }
    }

    override fun onDateTimeChanged(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int) {
        preview.text = formatDate(year, month, dayOfMonth, hour, minute)
    }

    private fun formatDate(year: Int, month: Int, dayOfMonth: Int, hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth, hour, minute, 0)
        return SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.LONG, SimpleDateFormat.DEFAULT)
            .format(calendar.time)
    }
}
