package com.murzify.effectivetest.core.domain.model

data class TicketOffer(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Long
)
