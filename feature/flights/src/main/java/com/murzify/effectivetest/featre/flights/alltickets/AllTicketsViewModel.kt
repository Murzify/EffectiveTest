package com.murzify.effectivetest.featre.flights.alltickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murzify.effectivetest.core.domain.model.Ticket
import com.murzify.effectivetest.core.domain.repository.FlightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTicketsViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
) : ViewModel() {

    private val _tickets = MutableStateFlow<List<Ticket>>(emptyList())
    val tickets: StateFlow<List<Ticket>> = _tickets

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _tickets.value = flightsRepository.getTickets()
        }
    }

}