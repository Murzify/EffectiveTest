package com.murzify.effectivetest.core.network

import com.murzify.effectivetest.core.network.model.OffersDto
import com.murzify.effectivetest.core.network.model.TicketsDto
import com.murzify.effectivetest.core.network.model.TicketsOffersDto

interface NetworkDataSource {

    suspend fun getOffers(): OffersDto

    suspend fun getOffersTickets(): TicketsOffersDto

    suspend fun getTickets(): TicketsDto

}