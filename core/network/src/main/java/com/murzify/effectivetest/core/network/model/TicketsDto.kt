package com.murzify.effectivetest.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsDto(
    val tickets: List<TicketDto>
)

@Serializable
data class TicketDto(
    val id: Int,
    val badge: String? = null,
    val price: PriceDto,
    @SerialName("provider_name")
    val providerName: String,
    val company: String,
    val departure: PointDto,
    val arrival: PointDto,
    @SerialName("has_transfer")
    val hasTransfer: Boolean,
    @SerialName("has_visa_transfer")
    val hasVisaTransfer: Boolean,
    val luggage: LuggageDto,
    @SerialName("hand_luggage")
    val handLuggage: HandLuggageDto,
    @SerialName("is_returnable")
    val isReturnable: Boolean,
    @SerialName("is_exchangable")
    val isExchangable: Boolean
)

@Serializable
data class PointDto(
    val town: String,
    val date: String,
    val airport: String
)

@Serializable
data class LuggageDto(
    @SerialName("has_luggage")
    val hasLuggage: Boolean,
    val price: PriceDto? = null
)

@Serializable
data class HandLuggageDto(
    @SerialName("has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String? = null
)