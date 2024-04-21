package com.murzify.effectivetest.core.data

import com.murzify.effectivetest.core.domain.model.HandLuggage
import com.murzify.effectivetest.core.domain.model.Luggage
import com.murzify.effectivetest.core.domain.model.Offer
import com.murzify.effectivetest.core.domain.model.Point
import com.murzify.effectivetest.core.domain.model.Ticket
import com.murzify.effectivetest.core.domain.model.TicketOffer
import com.murzify.effectivetest.core.network.model.HandLuggageDto
import com.murzify.effectivetest.core.network.model.LuggageDto
import com.murzify.effectivetest.core.network.model.OfferDto
import com.murzify.effectivetest.core.network.model.OffersDto
import com.murzify.effectivetest.core.network.model.PointDto
import com.murzify.effectivetest.core.network.model.TicketDto
import com.murzify.effectivetest.core.network.model.TicketOfferDto
import com.murzify.effectivetest.core.network.model.TicketsDto
import com.murzify.effectivetest.core.network.model.TicketsOffersDto

fun OfferDto.toOffer() = Offer(
    id, title, town, price.value
)

fun OffersDto.toOffers() = offers.map { dto -> dto.toOffer() }

fun TicketOfferDto.toTicketOffer() = TicketOffer(
    id, title, timeRange, price.value
)

fun TicketsOffersDto.toTicketsOffers() = ticketsOffers.map { dto -> dto.toTicketOffer() }

fun TicketDto.toTicket() = Ticket(
    id,
    badge,
    price.value,
    providerName,
    company,
    departure.toPoint(),
    arrival.toPoint(),
    hasTransfer,
    hasVisaTransfer,
    luggage.toLuggage(),
    handLuggage.toHandLuggage(),
    isReturnable,
    isExchangable
)

fun TicketsDto.toTickets() = tickets.map { dto -> dto.toTicket() }

private fun HandLuggageDto.toHandLuggage() = HandLuggage(
    hasHandLuggage, size
)

private fun LuggageDto.toLuggage() = Luggage(
    hasLuggage, price?.value
)

fun PointDto.toPoint() = Point(
    town, date, airport
)