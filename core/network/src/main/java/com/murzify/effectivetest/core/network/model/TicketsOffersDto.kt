package com.murzify.effectivetest.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TicketsOffersDto(
    @SerialName("tickets_offers")
    val ticketsOffers: TicketOfferDto
)

@Serializable
data class TicketOfferDto(
    val title: String,
    @SerialName("time_range")
    val timeRange: List<String>,
    val price: PriceDto
)
