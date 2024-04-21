package com.murzify.effectivetest.featre.flights.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murzify.effectivetest.core.domain.model.TicketOffer
import com.murzify.effectivetest.core.domain.repository.FlightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class TicketsViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
) : ViewModel() {

    private val _from = MutableStateFlow("")
    val from: StateFlow<String> = _from

    private val _destination = MutableStateFlow("")
    val destination: StateFlow<String> = _destination

    private val _departureDate = MutableStateFlow(Calendar.getInstance().time)
    val departureDate: StateFlow<Date> = _departureDate

    private val _backDate = MutableStateFlow<Date?>(null)
    val backDate: StateFlow<Date?> = _backDate

    private val _ticketOffers = MutableStateFlow<List<TicketOffer>>(emptyList())
    val ticketOffers: StateFlow<List<TicketOffer>> = _ticketOffers

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _ticketOffers.value = flightsRepository.getTicketsOffers()
        }
    }

    fun setDestination(destination: String) {
        _destination.value = destination
    }

    fun setFrom(text: String) {
        if (text == from.value) return
        viewModelScope.launch(Dispatchers.IO) {
            flightsRepository.setFrom(text)
        }
        _from.value = text
    }

    fun changeDirections() {
        val oldFrom = from.value
        _from.value = destination.value
        _destination.value = oldFrom
    }

    fun changeDepartureDate(date: Date) {
        _departureDate.value = date
    }

    fun setBackDate(date: Date) {
        _backDate.value = date
    }

    fun clearDestination() {
        _destination.value = ""
    }

}