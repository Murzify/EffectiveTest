package com.murzify.effectivetest.featre.flights.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.murzify.effectivetest.core.common.collectStarted
import com.murzify.effectivetest.featre.flights.databinding.FragmentFlightsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlightsFragment : Fragment() {

    private var _binding: FragmentFlightsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: FlightsViewModel by viewModels()

    private val adapter = ListDelegationAdapter(
        offersAdapterDelegate()
    )

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightsBinding.inflate(inflater)
        edge2edgeSetup()

        binding.apply {
            offersList.adapter = adapter
            setupListeners()
            setupViewModelData()
        }

        return binding.root
    }

    private fun FragmentFlightsBinding.setupListeners() {
        fromEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.updateFrom(text.toString())
        }

        destinationText.setOnClickListener {
            val action = FlightsFragmentDirections
                .actionFlightsFragmentToHostBottomSheet(viewModel.from.value)
            findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun FragmentFlightsBinding.setupViewModelData() {
        collectStarted(viewModel.from) {
            if (it != fromEditText.text.toString()) {
                fromEditText.setText(it)
            }
            fromEditText.apply { setSelection(length()) }
        }

        collectStarted(viewModel.offers) { offers ->
            adapter.items = offers
            adapter.notifyDataSetChanged()
        }
    }

    private fun edge2edgeSetup() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.flightsTitle) { v, insets ->
            val statusBar = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.setPadding(
                statusBar.left,
                statusBar.top,
                statusBar.right,
                statusBar.bottom
            )
            insets
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}