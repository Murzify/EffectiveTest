package com.murzify.effectivetest.featre.flights.tickets

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.murzify.effectivetest.core.common.formatToRub
import com.murzify.effectivetest.core.domain.model.TicketOffer
import com.murzify.effectivetest.core.ui.R
import com.murzify.effectivetest.featre.flights.databinding.TicketsItemBinding

fun ticketsAdapterDelegate() = adapterDelegateViewBinding<TicketOffer, TicketOffer , TicketsItemBinding>(
    { layoutInflater, root -> TicketsItemBinding.inflate(layoutInflater, root, false) }
) {
    bind {
        binding.apply {
            when (item.id) {
                1 -> circle.backgroundTintList = getColorStateList(R.color.red)
                10 -> circle.backgroundTintList = getColorStateList(R.color.blue)
                30 -> circle.backgroundTintList = getColorStateList(R.color.white)
            }
            title.text = item.title
            timeRange.text = item.timeRange.joinToString(" ")
            price.text = item.price.formatToRub()
        }
    }
}