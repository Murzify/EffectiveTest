package com.murzify.effectivetest.featre.flights.tickets

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.datepicker.MaterialDatePicker
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.murzify.effectivetest.core.common.collectStarted
import com.murzify.effectivetest.featre.flights.R
import com.murzify.effectivetest.featre.flights.databinding.FragmentTicketsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class TicketsFragment : Fragment() {

    private var _binding: FragmentTicketsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: TicketsViewModel by viewModels()
    private val adapter = ListDelegationAdapter(
        ticketsAdapterDelegate()
    )

    private val args: TicketsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTicketsBinding.inflate(inflater)
        binding.apply {
            ticketsRv.adapter = adapter
            setupViewModelData()
            setupListeners()
        }

        return binding.root
    }

    private fun FragmentTicketsBinding.setupViewModelData() {
        viewModel.apply {
            setFrom(args.from)
            setDestination(args.destintaion)

            collectStarted(departureDate) { departureDate ->
                departureDateText.text = departureDate.formatToHtml()
            }
            collectStarted(from) { from ->
                fromInputText.setText(from)
            }
            collectStarted(destination) { destination ->
                destinationInputText.setText(destination)
            }
            collectStarted(backDate) { backDate ->
                if (backDate != null) {
                    backDateText.text = backDate.formatToHtml()
                    backDateText.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            }
        }
    }

    private fun FragmentTicketsBinding.setupListeners() {
        change.setOnClickListener {
            viewModel.changeDirections()
        }
        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        destinationInputText.doOnTextChanged { text, _, _, _ ->
            viewModel.setDestination(text.toString())
        }
        fromInputText.doOnTextChanged { text, _, _, _ ->
            viewModel.setFrom(text.toString())
        }
        departureDateChip.setOnClickListener {
            datePicker { date ->
                viewModel.changeDepartureDate(Date(date))
            }
        }
        backDateChip.setOnClickListener {
            datePicker { date ->
                viewModel.setBackDate(Date(date))
            }
        }
        showAllTicketsButton.setOnClickListener {
            navigateToAllTicketsFragment()
        }
        clearButton.setOnClickListener {
            viewModel.clearDestination()
        }
    }

    private fun navigateToAllTicketsFragment() {
        val action = TicketsFragmentDirections.actionTicketsFragmentToAllTicketsFragment(
            viewModel.from.value,
            viewModel.destination.value,
            viewModel.departureDate.value
        )
        findNavController().navigate(action)
    }

    private fun datePicker(onSelect : (Long) -> Unit) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.select_date))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
        datePicker.addOnPositiveButtonClickListener(onSelect)
        datePicker.show(parentFragmentManager, "picker")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectStarted(viewModel.ticketOffers) { ticketOffers ->
            adapter.items = ticketOffers
            adapter.notifyDataSetChanged()
        }
    }

    private fun Date.formatToHtml(): Spanned {
        val formatted = formatDate(this)
        return HtmlCompat.fromHtml(
            getString(
                R.string.chip_date,
                formatted.first,
                formatted.second
            ),
            HtmlCompat.FROM_HTML_MODE_COMPACT
        )
    }

    private fun formatDate(date: Date): Pair<String, String> {
        val sdfDayMonth = SimpleDateFormat("d MMM", Locale.getDefault())
        val sdfWeekday = SimpleDateFormat("EE", Locale.getDefault())
        return sdfDayMonth.format(date) to sdfWeekday.format(date)
    }

}