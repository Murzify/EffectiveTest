package com.murzify.effectivetest.featre.flights

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.murzify.effectivetest.core.domain.model.Offer
import com.murzify.effectivetest.featre.flights.databinding.FragmentFlightsBinding
import com.murzify.effectivetest.featre.flights.databinding.OffersItemBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Currency

@AndroidEntryPoint
class FlightsFragment : Fragment() {

    private var _binding: FragmentFlightsBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: FlightsViewModel by viewModels()

    private val adapter = ListDelegationAdapter(
        offersAdapterDelegate()
    )
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFlightsBinding.inflate(inflater)
        edge2edgeSetup()


        binding.apply {
            offersList.adapter = adapter
        }

        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.offers.collect { offers ->
                    adapter.items = offers
                    adapter.notifyDataSetChanged()
                }
            }
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


}