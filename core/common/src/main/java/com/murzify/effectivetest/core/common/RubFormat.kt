package com.murzify.effectivetest.core.common

import java.text.NumberFormat
import java.util.Currency

fun Long.formatToRub(): String {
    val rub = Currency.getInstance("RUB")
    val priceFormat = NumberFormat.getCurrencyInstance()
    priceFormat.apply {
        maximumFractionDigits = 0
        currency = rub
    }
    return priceFormat.format(this)
}