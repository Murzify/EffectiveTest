package com.murzify.effectivetest.feature.stub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.murzify.effectivetest.feature.search.databinding.FragmentBottomBarStubBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BottomBarStubFragment : Fragment() {

    private var _binding: FragmentBottomBarStubBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomBarStubBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}