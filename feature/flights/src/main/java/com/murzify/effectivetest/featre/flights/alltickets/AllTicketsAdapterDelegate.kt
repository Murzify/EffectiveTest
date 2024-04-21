package com.murzify.effectivetest.featre.flights.alltickets

import android.view.View
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.murzify.effectivetest.core.common.formatToRub
import com.murzify.effectivetest.core.domain.model.Ticket
import com.murzify.effectivetest.featre.flights.R
import com.murzify.effectivetest.featre.flights.databinding.AllTicketsItemBinding
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun allTicketsAdapterDelegate() = adapterDelegateViewBinding<Ticket, Ticket , AllTicketsItemBinding>(
    { layoutInflater, root -> AllTicketsItemBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.apply {
            if (item.badge != null) {
                badgeText.text = item.badge
            } else {
                badge.visibility = View.GONE
            }
            price.text = item.price.formatToRub()
            startTime.text = formatTime(item.departure.date)
            endTime.text = formatTime(item.arrival.date)
            airportFromText.text = item.departure.airport
            airportDestText.text = item.arrival.airport
            travelTimeText.text = getString(
                R.string.travel_time,
                calculateHourDifference(item.departure.date, item.arrival.date)
            )
            if (!item.hasTransfer) {
                slash.visibility = View.GONE
                directText.visibility = View.GONE
            }
        }
    }
}

private fun formatTime(time: String): String {
    val inputFormat = DateTimeFormatter.ISO_DATE_TIME
    val outputFormat = DateTimeFormatter.ofPattern("HH:mm")

    val localDateTime = LocalDateTime.parse(time, inputFormat)
    return localDateTime.format(outputFormat)
}

fun calculateHourDifference(startTime: String, endTime: String): Double {
    val formatter = DateTimeFormatter.ISO_DATE_TIME
    val start = LocalDateTime.parse(startTime, formatter)
    val end = LocalDateTime.parse(endTime, formatter)

    val duration = Duration.between(start, end)
    return duration.toMinutes() / 60.0
}