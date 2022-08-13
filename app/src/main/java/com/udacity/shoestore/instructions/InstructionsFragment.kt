package com.udacity.shoestore.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionsBinding


class InstructionsFragment : Fragment() {

    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Instructions"

        binding.lifecycleOwner = viewLifecycleOwner

        binding.startButton.setOnClickListener {
            findNavController().navigate(InstructionsFragmentDirections.actionInstructionsFragmentToShoeListFragment())
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}