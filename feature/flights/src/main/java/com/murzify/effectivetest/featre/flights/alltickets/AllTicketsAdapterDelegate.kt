package com.murzify.effectivetest.featre.flights.main

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.murzify.effectivetest.core.common.formatToRub
import com.murzify.effectivetest.core.domain.model.Offer
import com.murzify.effectivetest.featre.flights.R
import com.murzify.effectivetest.featre.flights.databinding.OffersItemBinding
import java.text.NumberFormat
import java.util.Currency

fun offersAdapterDelegate() = adapterDelegateViewBinding<Offer, Offer , OffersItemBinding>(
    { layoutInflater, root -> OffersItemBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.apply {
            val imageRes = mapOf(
                1 to R.drawable.die_antwoord,
                2 to R.drawable.socrat_lera,
                3 to R.drawable.lampabikt
            )

            image.setImageDrawable(imageRes[item.id]?.let { getDrawable(it) })
            title.text = item.title
            city.text = item.town

            val formattedPrice = item.price.formatToRub()
            price.text = getString(R.string.offer_price, formattedPrice)
        }
    }
}