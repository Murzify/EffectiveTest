package com.murzify.effectivetest.core.data

import com.murzify.effectivetest.core.domain.model.Offer
import com.murzify.effectivetest.core.domain.model.Ticket
import com.murzify.effectivetest.core.domain.model.TicketOffer
import com.murzify.effectivetest.core.domain.repository.FlightsRepository
import com.murzify.effectivetest.core.network.NetworkDataSource
import com.murzify.effectivetest.core.shared_prefs.SharedPreferencesDataStore
import javax.inject.Inject

class FlightsRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val sharedPrefsDataStore: SharedPreferencesDataStore
): FlightsRepository {
    override suspend fun getOffers(): List<Offer> = networkDataSource.getOffers().toOffers()

    override suspend fun getTicketsOffers(): List<TicketOffer> =
        networkDataSource.getOffersTickets().toTicketsOffers()

    override suspend fun getTickets(): List<Ticket> = networkDataSource.getTickets().toTickets()
    override suspend fun getPreviousFrom(): String? {
        return sharedPrefsDataStore.getPreviousFrom()
    }

    override suspend fun setFrom(from: String) {
        return sharedPrefsDataStore.setFrom(from)
    }
}