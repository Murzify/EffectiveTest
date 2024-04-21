package com.murzify.effectivetest.core.network.model

import kotlinx.serialization.Serializable

@Serializable
data class OffersDto(
    val offers: List<OfferDto>
)

@Serializable
data class OfferDto(
    val id: Int,
    val title: String,
    val town: String,
    val price: PriceDto
)
