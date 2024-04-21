package com.murzify.effectivetest.featre.flights.search

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.murzify.effectivetest.core.common.collectStarted
import com.murzify.effectivetest.featre.flights.R
import com.murzify.effectivetest.featre.flights.databinding.SearchBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchBottomSheet : Fragment() {

    private var _binding: SearchBottomSheetBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: SearchViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchBottomSheetBinding.inflate(inflater)
        binding.apply {
            arguments?.getString("from")?.let { viewModel.updateFrom(it) }
            fromInputText.setText(viewModel.from.value)
            destinationInputText.requestFocus()

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
                fromInputText.apply { setSelection(length()) }
            }
            destinationInputText.setOnEditorActionListener { _, actionId, _ ->
                return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val action = SearchBottomSheetDirections
                        .actionSearchBottomSheetToTicketsFragment(
                            viewModel.from.value,
                            viewModel.destination.value
                        )
                    findNavController().navigate(action)
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
                destinationInputText.setText(getString(R.string.sochi))
                navigateToTickets()
            }
            phuket.setOnClickListener {
                destinationInputText.setText(getString(R.string.phuket))
                navigateToTickets()
            }
            anywhereButton.setOnClickListener {
                destinationInputText.setText(getString(R.string.anywhere_dest))
                navigateToTickets()
            }
            
            routeButton.setOnClickListener {
                val action = SearchBottomSheetDirections
                    .actionSearchBottomSheetToStubFragment(R.string.difficult_route)
                findNavController().navigate(action)
            }
            weekendButton.setOnClickListener {
                val action = SearchBottomSheetDirections
                    .actionSearchBottomSheetToStubFragment(R.string.weekend)
                findNavController().navigate(action)
            }
            hotButton.setOnClickListener {
                val action = SearchBottomSheetDirections
                    .actionSearchBottomSheetToStubFragment(R.string.hot_tickets)
                findNavController().navigate(action)
            }

        }

        return binding.root
    }
    
    private fun navigateToTickets() {
        val actionToTickets = SearchBottomSheetDirections.actionSearchBottomSheetToTicketsFragment(
            viewModel.from.value,
            viewModel.destination.value
        )
        findNavController().navigate(actionToTickets)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}