package com.mario.omiechallenge.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.formatedDate(pattern: String = "dd/MM/yyyy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("pt", "BR"))
    return dateFormat.format(this)
}

fun Double.formatedToReal(): String {
    val format = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return format.format(this)
}