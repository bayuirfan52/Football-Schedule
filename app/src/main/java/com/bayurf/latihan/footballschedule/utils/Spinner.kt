package com.bayurf.latihan.footballschedule.utils

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner

fun Spinner.collectingArrayData(context: Context) {
    val list = mutableListOf<String>()
    list.add("Menerima daftar league...")

    adapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, list)
}