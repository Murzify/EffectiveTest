package com.murzify.effectivetest.featre.flights.alltickets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.murzify.effectivetest.core.common.collectStarted
import com.murzify.effectivetest.featre.flights.R
import com.murzify.effectivetest.featre.flights.databinding.FragmentAllTicketsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class AllTicketsFragment : Fragment() {

    private var _binding: FragmentAllTicketsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: AllTicketsViewModel by viewModels()
    private val adapter = ListDelegationAdapter(
        allTicketsAdapterDelegate()
    )
    private val args: AllTicketsFragmentArgs by navArgs()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(inflater)
        binding.apply {
            dateAndPassenger.text = getString(
                R.string.date_and_passenger,
                formatDate(args.departureDate)
            )
            directionTitle.text = getString(
                R.string.direction_title,
                args.from,
                args.destination
            )
            allTicketsRv.adapter = adapter
            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
        }
        collectStarted(viewModel.tickets) { tickets ->
            adapter.items = tickets
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun formatDate(date: Date): String {
        val sdf = SimpleDateFormat("d MMMM", Locale.getDefault())
        return sdf.format(date)
    }
}