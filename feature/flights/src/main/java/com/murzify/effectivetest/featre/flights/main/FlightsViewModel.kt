package com.murzify.effectivetest.featre.flights

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

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _offers.value = flightsRepository.getOffers()
        }
    }
}