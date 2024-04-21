package com.murzify.effectivetest.core.domain.repository

import com.murzify.effectivetest.core.domain.model.Offer
import com.murzify.effectivetest.core.domain.model.Ticket
import com.murzify.effectivetest.core.domain.model.TicketOffer

interface FlightsRepository {

    suspend fun getOffers(): List<Offer>

    suspend fun getTicketsOffers(): List<TicketOffer>

    suspend fun getTickets(): List<Ticket>

    suspend fun getPreviousFrom(): String?

    suspend fun setFrom(from: String)
}