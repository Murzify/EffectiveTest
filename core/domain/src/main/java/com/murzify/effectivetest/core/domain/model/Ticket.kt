package com.murzify.effectivetest.core.domain.model

data class Ticket(
    val id: Int,
    val badge: String?,
    val price: Long,
    val providerName: String,
    val company: String,
    val departure: Point,
    val arrival: Point,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: Luggage,
    val handLuggage: HandLuggage,
    val isReturnable: Boolean,
    val isExchangable: Boolean
)

data class Point(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    val hasLuggage: Boolean,
    val price: Long?
)

data class HandLuggage(
    val hasHandLuggage: Boolean,
    val size: String?
)
