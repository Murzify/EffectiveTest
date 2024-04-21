package com.murzify.effectivetest.featre.flights.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.murzify.effectivetest.core.domain.repository.FlightsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val flightsRepository: FlightsRepository
) : ViewModel() {
    private val _from = MutableStateFlow("")
    val from: StateFlow<String> = _from

    private val _destination = MutableStateFlow("")
    val destination: StateFlow<String> = _destination

    fun setDestination(destination: String) {
        _destination.value = destination
    }

    fun updateFrom(text: String) {
        if (text == from.value) return
        viewModelScope.launch(Dispatchers.IO) {
            flightsRepository.setFrom(text)
        }
        _from.value = text
    }

    fun clearDestination() {
        _destination.value = ""
    }

}