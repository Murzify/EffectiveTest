package com.murzify.effectivetest.featre.flights.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murzify.effectivetest.core.domain.model.Offer
import com.murzify.effectivetest.core.domain.repository.FlightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FlightsViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
): ViewModel() {
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    private val _from = MutableStateFlow("")
    val from: StateFlow<String> = _from

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _offers.value = flightsRepository.getOffers()
        }
        viewModelScope.launch(Dispatchers.IO) {
            val prev = flightsRepository.getPreviousFrom()
            _from.value = prev ?: ""
        }
    }

    fun updateFrom(text: String) {
        if (text == from.value) return
        viewModelScope.launch(Dispatchers.IO) {
            flightsRepository.setFrom(text)
        }
        _from.value = text
    }
}