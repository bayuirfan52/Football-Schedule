package com.bayurf.latihan.footballschedule.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object FormatDate {
    private fun dateOrTimeChanger(dateOrTime: String, formatWillUse: String, selectDate: Boolean): String {
        val oldDateOrTime = SimpleDateFormat(if (selectDate) "yyyy-MM-dd" else "HH:mm:ss", Locale.US)
        oldDateOrTime.timeZone = TimeZone.getTimeZone("UTC")
        var result = ""

        try {
            val oldValue = oldDateOrTime.parse(dateOrTime)
            val newValue = SimpleDateFormat(formatWillUse, Locale.US)

            result = newValue.format(oldValue)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return result
    }

    fun reformatDate(date: String?): String? {
        return date?.let { dateOrTimeChanger(it, "EEE, dd MMM yyyy", true) }
    }

    fun reformatTime(time: String?): String? {
        return time?.let { dateOrTimeChanger(it, "HH:mm", false) }
    }
}