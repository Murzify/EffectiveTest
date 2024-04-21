package com.murzify.effectivetest.featre.flights.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.murzify.effectivetest.core.common.collectStarted
import com.murzify.effectivetest.featre.flights.R
import com.murzify.effectivetest.featre.flights.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater)
        binding.apply {
            setupInitialValues()
            setupTextChangeListeners()
            setupButtonClickListeners()
        }

        return binding.root
    }

    private fun FragmentSearchBinding.setupInitialValues() {
        arguments?.getString("from")?.let { viewModel.updateFrom(it) }
        fromInputText.setText(viewModel.from.value)
        destinationInputText.requestFocus()
    }

    private fun FragmentSearchBinding.setupTextChangeListeners() {
        destinationInputText.doOnTextChanged { text, _, _, _ ->
            viewModel.setDestination(text.toString())
        }

        fromInputText.doOnTextChanged { text, _, _, _ ->
            viewModel.updateFrom(text.toString())
        }

        collectStarted(viewModel.from) {
            if (it != fromInputText.text.toString()) {
                fromInputText.setText(it)
            }
            fromInputText.setSelection(fromInputText.length())
        }

        collectStarted(viewModel.destination) {
            if (it != destinationInputText.text.toString()) {
                destinationInputText.setText(it)
            }
            destinationInputText.setSelection(destinationInputText.length())
        }
    }

    private fun FragmentSearchBinding.setupButtonClickListeners() {
        clearButton.setOnClickListener {
            viewModel.clearDestination()
        }

        destinationInputText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                navigateToTickets()
                true
            } else {
                false
            }
        }

        istanbul.setOnClickListener {
            destinationInputText.setText(R.string.istanbul)
            navigateToTickets()
        }
        sochi.setOnClickListener {
            destinationInputText.setText(R.string.sochi)
            navigateToTickets()
        }
        phuket.setOnClickListener {
            destinationInputText.setText(R.string.phuket)
            navigateToTickets()
        }
        anywhereButton.setOnClickListener {
            destinationInputText.setText(R.string.anywhere_dest)
            navigateToTickets()
        }

        routeButton.setOnClickListener {
            navigateToStubFragment(R.string.difficult_route)
        }
        weekendButton.setOnClickListener {
            navigateToStubFragment(R.string.weekend)
        }
        hotButton.setOnClickListener {
            navigateToStubFragment(R.string.hot_tickets)
        }
    }

    private fun navigateToStubFragment(stringResId: Int) {
        val action = SearchFragmentDirections
            .actionSearchBottomSheetToStubFragment(stringResId)
        findNavController().navigate(action)
    }

    private fun navigateToTickets() {
        val action = SearchFragmentDirections
            .actionSearchBottomSheetToTicketsFragment(
                viewModel.from.value,
                viewModel.destination.value
            )
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}