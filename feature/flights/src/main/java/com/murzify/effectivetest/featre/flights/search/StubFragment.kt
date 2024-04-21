package com.murzify.effectivetest.featre.flights.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.murzify.effectivetest.featre.flights.databinding.FragmentStubBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StubFragment : Fragment() {

    private var _binding: FragmentStubBinding? = null
    private val binding
        get() = _binding!!

    private val args: StubFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStubBinding.inflate(inflater)
        binding.title.setText(args.title)
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}