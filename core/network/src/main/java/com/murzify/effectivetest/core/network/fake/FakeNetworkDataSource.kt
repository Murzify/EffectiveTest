package com.murzify.effectivetest.core.network.fake

import com.murzify.effectivetest.core.network.NetworkDataSource
import com.murzify.effectivetest.core.network.R
import com.murzify.effectivetest.core.network.ResourceSerializer
import com.murzify.effectivetest.core.network.model.OffersDto
import com.murzify.effectivetest.core.network.model.TicketsDto
import com.murzify.effectivetest.core.network.model.TicketsOffersDto
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    private val resourceSerializer: ResourceSerializer
) : NetworkDataSource {
    override suspend fun getOffers(): OffersDto =
        resourceSerializer.deserializeResource(R.raw.offers)

    override suspend fun getOffersTickets(): TicketsOffersDto =
        resourceSerializer.deserializeResource(R.raw.offers_tickets)

    override suspend fun getTickets(): TicketsDto =
        resourceSerializer.deserializeResource(R.raw.tickets)
}